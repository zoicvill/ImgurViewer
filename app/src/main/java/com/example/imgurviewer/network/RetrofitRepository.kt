package com.example.imgurviewer.network

import com.example.imgurviewer.model.Comments
import com.example.imgurviewer.model.GalleryItems
import com.example.imgurviewer.model.ImageDataResp
import retrofit2.Response

interface RetrofitRepository {
    suspend fun getPopularImgs(page: Int): Response<GalleryItems>
    fun getComments(postId: String): Response<Comments>
}