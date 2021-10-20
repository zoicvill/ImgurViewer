package com.example.imgurviewer.model

import java.io.Serializable

data class ImageAndComments(

    val imageId: String? = null,

    val imageLink: String? = null,

    val imageTitle: String? = null,

    val commentList: List<Comments>? = listOf()


) : Serializable
