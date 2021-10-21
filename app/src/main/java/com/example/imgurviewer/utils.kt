package com.example.imgurviewer

import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

fun String.toImgurUrl(): String {
    return "https://i.imgur.com/${this}m.jpg"
    }

fun ImageView.loadImageUtils(link: String?, sw: Boolean){
    when (sw){
        true ->
            Glide.with(context)
                .asBitmap()
                .load(link?.toImgurUrl())
                .placeholder(R.drawable.my_gradient)
                .into(this)

        false -> {
            Glide.with(context)
                .asBitmap()
                .load(link)
                .placeholder(R.drawable.my_gradient)
                .into(this)
            Log.d("Lol", "link utils $link")
        }

    }

}

fun String.toFullLink(): String{
    return "https://imgur.com/a/${this}"
}


