package com.example.imgurviewer.pager

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.imgurviewer.model.GalleryItems
import com.example.imgurviewer.network.ResponseData
import java.lang.Exception
import java.lang.NullPointerException

class ImagePagingSource(private val responseData: ResponseData): PagingSource<Int, GalleryItems.ImagesItem>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GalleryItems.ImagesItem> {
        try {
            val nextPage = params.key?: 1
            val response = responseData.getPopularImgOrNull(nextPage)
            Log.d("Lol", "ImagePagingSource")
            val resultData = response?.imgList
            return if(!resultData.isNullOrEmpty()) {
                Log.d("Lol", "ImagePagingSource if $resultData")
                LoadResult.Page(
                    data = resultData,
                    prevKey = if(nextPage == 0) null else nextPage - 1,
                    nextKey = nextPage + 1
                )
            } else {
                LoadResult.Error(NullPointerException())
            }
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GalleryItems.ImagesItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }


}