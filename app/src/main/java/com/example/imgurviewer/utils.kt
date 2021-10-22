package com.example.imgurviewer

import android.content.Context
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide

fun String.toImgurUrl(): String {
    return "https://i.imgur.com/${this}m.jpg"
}

fun String.toFullLink(): String {
    return "https://imgur.com/a/${this}"
}

fun ImageView.loadImageUtils(link: String?, sw: Boolean) {
    when (sw) {
        true ->
            GlideImg(context, link?.toImgurUrl(), this)

        false -> {
            GlideImg(context, link, this)
            Log.d("Lol", "link utils $link")
        }

    }

}

private fun GlideImg(context: Context, link: String?, img: ImageView) {
    Glide.with(context)
        .asBitmap()
        .load(link)
        .placeholder(R.drawable.my_gradient)
        .into(img)
}




