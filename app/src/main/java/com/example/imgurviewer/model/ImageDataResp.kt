package com.example.imgurviewer.model

import com.google.gson.annotations.SerializedName

data class ImageDataResp(
    @field:SerializedName("data")
    val data: GalleryItems.ImagesItem? = null,

    @field:SerializedName("success")
    val success: Boolean? = null,

    @field:SerializedName("status")
    val status: Int? = null
)
