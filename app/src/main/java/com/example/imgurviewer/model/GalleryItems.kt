package com.example.imgurviewer.model

import ImagesItem
import com.google.gson.annotations.SerializedName

data class GalleryItems(
    @field:SerializedName("data")
    val data: List<DataItem?>? = null,

    @field:SerializedName("success")
    val success: Boolean? = null,

    @field:SerializedName("status")
    val status: Int? = null
) {
    data class DataItem(

        @field:SerializedName("link")
        val link: String? = null,

        @field:SerializedName("description")
        val description: String? = null,

        @field:SerializedName("title")
        val title: String? = null,

        @field:SerializedName("id")
        val id: String? = null,

        @field:SerializedName("views")
        val views: Int? = null,

        @field:SerializedName("images")
        val images: List<ImagesItem?>? = null,
    )

    data class ImagesItem(

        @field:SerializedName("link")
        val link: String? = null,

        @field:SerializedName("description")
        val description: String? = null,

        @field:SerializedName("section")
        val section: Any? = null,

        @field:SerializedName("title")
        val title: String? = null,

        @field:SerializedName("id")
        val id: String? = null,

        )
}
