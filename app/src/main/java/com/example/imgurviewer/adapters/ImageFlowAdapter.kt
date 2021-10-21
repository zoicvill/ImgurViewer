package com.example.imgurviewer.adapters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.imgurviewer.R
import com.example.imgurviewer.databinding.ViewItemImgBinding
import com.example.imgurviewer.loadImageUtils
import com.example.imgurviewer.model.GalleryItems

class ImageFlowAdapter :
    PagingDataAdapter<GalleryItems.ImagesItem, ImageFlowAdapter.ImageAdapterVH>(ImageComparator) {

    override fun onBindViewHolder(holder: ImageAdapterVH, position: Int) {
        Log.d("Lol", "ImageFlowAdapter onBindViewHolder")
        getItem(position)?.let { holder.bind(it)}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageAdapterVH {
        Log.d("Lol", "ImageFlowAdapter onCreateViewHolder")
        val view = LayoutInflater.from(parent.context)
        return ImageAdapterVH(ViewItemImgBinding.inflate(view, parent, false))
    }

    inner class ImageAdapterVH(private val view: ViewItemImgBinding): RecyclerView.ViewHolder(view.root){
        fun bind(item: GalleryItems.ImagesItem) {
            val itemLink: String = item.link ?: ""
            Log.d("Lol", "ImageFlowAdapter ImageAdapterVH $itemLink  id ${item.id} title ${item.title} ")
            view.itemImg.apply {
                loadImageUtils(itemLink, true)
            }
            view.itemImg.rootView.setOnClickListener {
                val bundle = bundleOf("id" to item.id, "link" to item.link, "title" to item.title)
                view.root.findNavController()
                    .navigate(R.id.action_imageGalleryFragment_to_zoomImageFragment, bundle)
            }

        }
    }
    object ImageComparator : DiffUtil.ItemCallback<GalleryItems.ImagesItem>() {
        override fun areItemsTheSame(
            oldItem: GalleryItems.ImagesItem,
            newItem: GalleryItems.ImagesItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: GalleryItems.ImagesItem,
            newItem: GalleryItems.ImagesItem
        ): Boolean {
            return oldItem == newItem
        }


    }
}

