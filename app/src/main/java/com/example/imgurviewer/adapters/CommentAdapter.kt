package com.example.imgurviewer.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.imgurviewer.REGEX
import com.example.imgurviewer.databinding.ViewItemCommetBinding
import com.example.imgurviewer.loadImageUtils
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
                let {
                    //// поправить
                   var reg = ""
                    comment.let {
                        it?.split(" ")?.forEach {
                            fr -> if (fr.matches(Regex(REGEX))){
                                reg = fr
                        }
                        }
                    }
                    Log.d("Lol", "reg $reg")
                    view.autor.text = author

                    when (comment?.matches(Regex(REGEX))) {
                        true -> {
                            view.imageCom.loadImageUtils(reg, false)
                        }

                        false -> {
                            view.tvComment.text = comment
                            view.imageCom.isVisible = false
                        }
                    }

                }

            }
        }
    }

}