package com.example.imgurviewer.adapters

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.imgurviewer.R
import com.example.imgurviewer.databinding.ViewItemImgBinding
import com.example.imgurviewer.model.GalleryItems
import com.example.imgurviewer.toImgurUrl
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class ImageAdapter :
    RecyclerView.Adapter<ImageAdapter.Holder>() {

    private var categoryList: List<GalleryItems.DataItem?>? = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<GalleryItems.DataItem?>) {
        this.categoryList = list
        categoryList?.forEach {
            Log.d("Lol", "List $it")
        }

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
        return Holder(ViewItemImgBinding.inflate(view, parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int =
        categoryList?.size ?: 0

    private fun loadImage(position: Int, viewBinding: ViewItemImgBinding) {
        CoroutineScope(Dispatchers.Main).launch {
//                Log.d("Lol", "loadImage $position ${categoryList?.get(position)?.images?.get(0)?.id?.toImgurUrl()}")
                Picasso.get()
                    .load(categoryList?.get(position)?.images?.get(0)?.id?.toImgurUrl())
                    .fit()
                    .error(R.drawable.my_gradient)
                    .into(viewBinding.itemImg)
        }

    }

    inner class Holder(private val view: ViewItemImgBinding) : RecyclerView.ViewHolder(view.root) {

        fun bind(position: Int) {
            categoryList?.get(position)?.apply {
                loadImage(position, view)
                view.root.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putString("key", this.id)
                    bundle.putString("tit", this.title)
                    bundle.putString("img", this.images?.get(0)?.id?.toImgurUrl())
                    bundle.putString("link", this.link)
                    Log.d("Lol", "bundle ${this.title}")

                    view.root.findNavController()
                        .navigate(R.id.action_imageGalleryFragment_to_zoomImageFragment, bundle)
                }
            }
        }
    }
}