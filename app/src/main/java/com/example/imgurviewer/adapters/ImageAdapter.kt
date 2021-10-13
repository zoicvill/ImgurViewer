package com.example.imgurviewer.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
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
    fun setList(list: Response<GalleryItems>) {
        this.categoryList = list.body()?.data
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
            if (categoryList?.get(position)?.images?.get(0)?.id != null){
                Picasso.get()
                    .load(categoryList?.get(position)?.images?.get(0)?.id?.toImgurUrl(false))
                    .fit()
                    .error(R.drawable.my_gradient)
                    .into(viewBinding.itemImg)
            }
        }

    }

    inner class Holder(private val view: ViewItemImgBinding) : RecyclerView.ViewHolder(view.root) {

        fun bind(position: Int) {
            categoryList?.get(position)?.apply {
                loadImage(position, view)
                view.root.setOnClickListener {
                    Toast.makeText(it.context, " HUi", Toast.LENGTH_LONG ).show()
                }
            }
        }
    }
}