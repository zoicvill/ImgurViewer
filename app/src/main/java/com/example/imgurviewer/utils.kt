package com.example.imgurviewer

fun String.toImgurUrl(big: Boolean): String {
    return if (big) {
        "https://i.imgur.com/$this.jpeg"
    } else {
        "https://i.imgur.com/$this.jpg"
    }
}