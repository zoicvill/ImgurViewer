package com.example.imgurviewer

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

fun String.toImgurUrl(): String {
    return "https://i.imgur.com/${this}m.jpg"
    }
fun ImageView.loadImageUtils(link: String?){
    Glide.with(context)
        .asBitmap()
        .load(link?.toImgurUrl())
        .placeholder(R.drawable.my_gradient)
        .into(this)
}