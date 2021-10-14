package com.example.imgurviewer.ui.imagegallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.imgurviewer.adapters.ImageAdapter
import com.example.imgurviewer.databinding.FragmentImagesBinding
import com.example.imgurviewer.model.GalleryItems
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ImageGalleryFragment : Fragment() {

    private var binding: FragmentImagesBinding? = null
    private val viewModel by viewModels<ImageGalleryViewModel>()
    lateinit var mRecyclerView: RecyclerView
    private var mImageAdapter: ImageAdapter? = null
    private val mBinding get() = binding!!
    private var page: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImagesBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getObserver()
        onClick()
    }


    /*метод обработки действий кнопок, который выглядит ужасно*/
    private fun onClick() {
        // кнопка next
        mBinding.inklButton.next.setOnClickListener {
            page++
            Log.d("Lol", "page $page")
            viewModel.pagination(page)
            onClick()
        }
        // кнопка perv
        when (page == 0) {
            true -> mBinding.inklButton.perv.isEnabled = false
            false -> {
                mBinding.inklButton.perv.isEnabled = true
                mBinding.inklButton.perv.setOnClickListener {
                    page--
                    viewModel.pagination(page)
                    Log.d("Lol", "page $page")
                    onClick()
                }
            }
        }
    }

    /* Подписка на данные и из viewModel */
    private fun getObserver() {
        viewModel.getGalleryItems().observe(viewLifecycleOwner, {
            Log.d("Lol", "Observer")
            initialization(it)
        })
    }

    /*инциализация RecyclerView*/
    private fun initialization(answer: List<GalleryItems.DataItem?>) {
        CoroutineScope(Dispatchers.Main).launch {
            when {
                answer.isEmpty() -> {
                    Log.d("Lol", "answer == null")
                }
                answer.isNotEmpty() -> {
                    mBinding.list.apply {
                        mRecyclerView = mBinding.list
                        mRecyclerView.setHasFixedSize(true)
                        val sGridLayoutManager =
                            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                        sGridLayoutManager.gapStrategy =
                            StaggeredGridLayoutManager.GAP_HANDLING_NONE
                        mRecyclerView.layoutManager = sGridLayoutManager
                        mImageAdapter = ImageAdapter()
                        mImageAdapter?.run { setList(answer) }
                        mRecyclerView.adapter = mImageAdapter
                        Log.d("Lol", "answer.body()")
                    }
                }
                else -> {

                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("Lol", "Observer onDestroyView")
        binding = null
    }


}


