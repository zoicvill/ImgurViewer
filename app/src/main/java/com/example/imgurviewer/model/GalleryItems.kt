package com.example.imgurviewer.model

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

        @field:SerializedName("id")
        val id: String? = null,

        @field:SerializedName("title")
        val title: String? = null,

        @field:SerializedName("type")
        val type: String? = null,

        @field:SerializedName("link")
        val link: String? = null

    )

    val imgList: List<ImagesItem> by lazy {
        getNewImageList()
    }

    private fun getNewImageList(): List<ImagesItem> {
        val newList = ArrayList<ImagesItem>()
        data?.filter {
            it?.link?.endsWith(".jpg") == true || it?.images?.getOrNull(0)?.link?.endsWith(".jpg") == true
        }?.forEach { dataItem ->

            var link = ""
            if (dataItem?.link?.endsWith(".jpg") == true) {
                dataItem.id?.let { link = it }
            } else {
                dataItem?.images?.getOrNull(0)?.id?.let { link = it }
            }
            newList.add(
                ImagesItem(
                    id = dataItem?.id,
                    title = dataItem?.title,
                    link = link
                )
            )
        }

        return newList
    }


}
