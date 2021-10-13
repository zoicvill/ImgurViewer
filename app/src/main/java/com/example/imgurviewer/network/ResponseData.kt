package com.example.imgurviewer.network

import com.example.imgurviewer.model.Comments
import com.example.imgurviewer.model.GalleryItems
import com.example.imgurviewer.model.ImageDataResp
import retrofit2.Response

class ResponseData: RetrofitRepository {
    override suspend fun getPopularImgs(page: Int): Response<GalleryItems> =
        Api.client.getPopularImgs(page)

    override fun getComments(postId: String): Response<Comments> =
        Api.client.getComments(postId)
}