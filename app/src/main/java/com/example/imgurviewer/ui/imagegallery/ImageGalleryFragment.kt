package com.example.imgurviewer.ui.imagegallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.imgurviewer.adapters.ImageFlowAdapter
import com.example.imgurviewer.databinding.FragmentImagesBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ImageGalleryFragment : Fragment() {

    private var binding: FragmentImagesBinding? = null
    private val viewModel by viewModels<ImageViewModelFlow>()
    private var mImageFlowAdapter: ImageFlowAdapter ? = null
    private val mBinding get() = binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImagesBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialization()
        initAdapter()
    }


    /*инциализация RecyclerView*/
    private fun initialization() {
        mImageFlowAdapter = ImageFlowAdapter()
        mBinding.list.adapter = mImageFlowAdapter
                mBinding.list.layoutManager =
                    StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                Log.d("Lol", "answer.body()")





    }

    private fun initAdapter() {
        lifecycleScope.launch {
            viewModel.movies.collectLatest {
                Log.d("Lol", " ImageGalleryFragment initAdapter")
                    mImageFlowAdapter?.submitData(it)

//                (mBinding.list.adapter as? ImageFlowAdapter)?.submitData(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mImageFlowAdapter = null
        binding = null
    }

}


