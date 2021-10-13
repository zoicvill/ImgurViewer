package com.example.imgurviewer

fun String.toImgurUrl(): String {
    return "https://i.imgur.com/${this}m.jpg"
    }