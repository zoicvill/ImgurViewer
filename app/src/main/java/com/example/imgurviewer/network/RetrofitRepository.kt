package com.example.imgurviewer.network

import com.example.imgurviewer.model.Comments
import com.example.imgurviewer.model.GalleryItems
import com.example.imgurviewer.model.ImageResponse
import retrofit2.Response

interface RetrofitRepository {

    suspend fun getPopularImgOrNull(page: Int): GalleryItems?
    suspend fun getCommentsOrNull(postId: String): Comments?
    suspend fun getInfoImgOrNull(postId: String): ImageResponse?
}