package com.example.imgurviewer.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imgurviewer.databinding.ViewItemCommetBinding
import com.example.imgurviewer.model.Comments

class CommentAdapter : RecyclerView.Adapter<CommentAdapter.Holder>() {

    private var commentList: List<Comments.DataItem?> = emptyList()

    fun setList(list: List<Comments.DataItem?>) {
        commentList = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
        return Holder(ViewItemCommetBinding.inflate(view, parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int =
        commentList.size


    inner class Holder(private val view: ViewItemCommetBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind(position: Int) {
            commentList[position]?.apply {
                this.let {
                    view.autor.text = it.author
                    view.tvComment.text = it.comment
                }
            }
        }
    }

}