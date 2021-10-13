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
        @field:SerializedName("author")
        val author: String? = null,

        @field:SerializedName("comment")
        val comment: String? = null,

        @field:SerializedName("id")
        val id: Int? = null,

        @field:SerializedName("author_id")
        val authorId: Int? = null,
    )
}
