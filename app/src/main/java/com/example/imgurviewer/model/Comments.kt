package com.example.imgurviewer.model

import com.google.gson.annotations.SerializedName

data class Comments(
    @field:SerializedName("data")
    val data: List<DataItem?>? = null,

    @field:SerializedName("success")
    val success: Boolean? = null,

    @field:SerializedName("status")
    val status: Int? = null
) {
    data class DataItem(
        @field:SerializedName("image_id")
        val imageId: String? = null,
        @field:SerializedName("comment")
        val comment: String? = null,
        @field:SerializedName("author")
        val author: String? = null,


        )
}
