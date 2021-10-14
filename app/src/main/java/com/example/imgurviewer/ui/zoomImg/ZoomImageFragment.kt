package com.example.imgurviewer.ui.zoomImg

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.imgurviewer.adapters.CommentAdapter
import com.example.imgurviewer.databinding.FragmentZoomImageBinding
import com.example.imgurviewer.loadImageUtils
import com.example.imgurviewer.model.Comments
import com.example.imgurviewer.ui.imagegallery.ImageGalleryViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ZoomImageFragment : Fragment() {

    private lateinit var viewModel: ImageGalleryViewModel
    private var _binding: FragmentZoomImageBinding? = null
    private val mBinding get() = _binding!!
    private var id: String? = null
    private var img: String? = null
    private var tit: String? = null
    private var link: String? = null
    lateinit var mRecyclerView: RecyclerView
    private var mImageAdapter: CommentAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentZoomImageBinding.inflate(layoutInflater, container, false)
        id = arguments?.getString("key")
        tit = arguments?.getString("tit")
        img = arguments?.getString("img")
        link = arguments?.getString("link")
        Log.d("Lol", "onCreateView $id $tit")

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ImageGalleryViewModel::class.java)
        id?.let { viewModel.comments(it) }
        viewModel.getComments().observe(viewLifecycleOwner, {
            init(it)
        })

        imgDsr()
    }

    /*Загрузка картинки в окне с комментами*/
    fun imgDsr() {
        mBinding.title.text = tit ?: "Название остутствует"
        mBinding.link.text = link
        CoroutineScope(Dispatchers.Main).launch {
            mBinding.image.loadImageUtils(img)
        }
    }

    /*инциализация RecyclerView*/
    fun init(resp: List<Comments.DataItem?>) {
        CoroutineScope(Dispatchers.Main).launch {
            when {
                resp.isEmpty() -> {
                    Log.d("Lol", "answer == null")
                }
                resp.isNotEmpty() -> {
                    mBinding.commentsList.apply {
                        mRecyclerView = mBinding.commentsList
                        mRecyclerView.setHasFixedSize(true)
                        mRecyclerView.layoutManager =
                            StaggeredGridLayoutManager(1, RecyclerView.VERTICAL)
                        mImageAdapter = CommentAdapter()
                        mImageAdapter?.run { setList(resp) }
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