package com.example.imgurviewer.network

import android.util.Log
import com.example.imgurviewer.model.Comments
import com.example.imgurviewer.model.GalleryItems
import com.example.imgurviewer.model.ImageResponse
import retrofit2.Response

class ResponseData : RetrofitRepository {


    private suspend fun getPopularImg(page: Int): Response<GalleryItems> =
        Api.client.getPopularImgs(page)


    override suspend fun getPopularImgOrNull(page: Int): GalleryItems? {
        return try {
            Log.d("Lol", "getPopularImgOrNull ${getPopularImg(page).body()?.data?.get(1)}")
            getPopularImg(page).body()
        } catch (e: Exception) {
            null
        }
    }

    private suspend fun getComments(postId: String): Response<Comments> =
        Api.client.getComments(postId)

    override suspend fun getCommentsOrNull(postId: String): Comments? {
        return try {
            Log.d("Lol", "getCommentsOrNull ${getComments(postId).body()?.data?.get(0)?.imageId}")
            getComments(postId).body()
        } catch (e: Exception) {
            null
        }
    }

    private suspend fun getInfoImg(postId: String): Response<ImageResponse> =
        Api.client.getImageInfo(postId)

    override suspend fun getInfoImgOrNull(postId: String): ImageResponse? {
        return try {
//            Log.d("Lol", "getCommentsOrNull ${getComments(postId).body()?.data?.get(0)?.imageId}")
            getInfoImg(postId).body()
        } catch (e: Exception) {
            null
        }
    }



}