package com.example.imgurviewer.network

import com.example.imgurviewer.CLIENT_ID
import com.example.imgurviewer.model.Comments
import com.example.imgurviewer.model.GalleryItems
import com.example.imgurviewer.model.ImageDataResp
import com.example.imgurviewer.retrofit
import retrofit2.Response
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface Api {

    @GET("gallery/top/viral/day/{page}?showViral=true&mature=true&album_previews=true")
    @Headers("Authorization: Client-ID $CLIENT_ID")
    suspend fun getPopularImgs(@Path(value = "page", encoded = true) page: Int): Response<GalleryItems>

    @GET("/3/gallery/{postId}/comments")
    @Headers("Authorization: Client-ID $CLIENT_ID")
    fun getComments(@Path("postId") postId: String): Response<Comments>

    companion object {
        val client by lazy { retrofit.create<Api>() }
    }
}