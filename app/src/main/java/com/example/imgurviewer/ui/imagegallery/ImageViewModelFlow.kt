package com.example.imgurviewer.ui.imagegallery

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.imgurviewer.APIREPOSITRETROFIT
import com.example.imgurviewer.model.GalleryItems
import com.example.imgurviewer.network.ResponseData
import com.example.imgurviewer.pager.ImagePagingSource
import kotlinx.coroutines.flow.Flow

class ImageViewModelFlow: ViewModel() {

    init {
        Log.d("Lol", "init")
       APIREPOSITRETROFIT = ResponseData()
    }

    val movies: Flow<PagingData<GalleryItems.ImagesItem>> = Pager(PagingConfig(pageSize = 10)) {
       ImagePagingSource(APIREPOSITRETROFIT)
    }.flow.cachedIn(viewModelScope)
}