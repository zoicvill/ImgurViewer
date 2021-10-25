package com.example.imgurviewer.network

import com.example.imgurviewer.CLIENT_ID
import com.example.imgurviewer.model.Comments
import com.example.imgurviewer.model.GalleryItems
import com.example.imgurviewer.model.ImageResponse
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

    @GET("gallery/{postId}/comments/best")
    @Headers("Authorization: Client-ID $CLIENT_ID")
    suspend fun getComments(@Path("postId", encoded = true) postId: String): Response<Comments>

    @GET("gallery/{image_id}")
    @Headers("Authorization: Client-ID $CLIENT_ID")
    suspend fun getImageInfo(@Path(value="image_id", encoded = true) imageId: String) : Response<ImageResponse>

    companion object {
        val client by lazy { retrofit.create<Api>() }
    }
}