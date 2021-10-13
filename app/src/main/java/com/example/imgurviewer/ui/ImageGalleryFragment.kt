package com.example.imgurviewer.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.imgurviewer.adapters.ImageAdapter
import com.example.imgurviewer.databinding.FragmentImagesBinding
import com.example.imgurviewer.model.GalleryItems
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ImageGalleryFragment : Fragment() {

    private var _binding: FragmentImagesBinding? = null
    private val viewModel by viewModels<ImageGalleryViewModel>()
    private lateinit var mObserverList: Observer<Response<GalleryItems>>
    lateinit var mRecyclerView: RecyclerView
    private var mImageAdapter: ImageAdapter? = null
    private val binding get() = _binding!!

    override fun onStart() {
        super.onStart()
        Log.d("Lol", "start")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentImagesBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getObserver()
    }

    private fun getObserver() {
        viewModel.getImageGal().observe(viewLifecycleOwner, Observer {
            Log.d("Lol", "Observer")
            initialization(it)
        })
    }

    private fun initialization(answer: Response<GalleryItems>?) {
        CoroutineScope(Dispatchers.Main).launch {
            when {
                answer == null -> {
                    Log.d("Lol", "answer == null")
                }
                answer.body() != null -> {
                    binding.list.apply {
                        mRecyclerView = binding.list
                        mRecyclerView.layoutManager =
                            StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
                        mImageAdapter = ImageAdapter()
                        mImageAdapter!!.setList(answer)
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
        _binding = null
    }
}