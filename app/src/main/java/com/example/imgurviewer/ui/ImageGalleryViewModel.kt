package com.example.imgurviewer.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.imgurviewer.APIREPOSITRETROFIT
import com.example.imgurviewer.model.GalleryItems
import com.example.imgurviewer.network.ResponseData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import retrofit2.Response

class ImageGalleryViewModel(application: Application): AndroidViewModel(application) {

    private var galleryItems: MutableLiveData<Response<GalleryItems>> = MutableLiveData()

    init {
        Log.d("Lol", "init")
        APIREPOSITRETROFIT = ResponseData()
        viewModelScope.launch(Dispatchers.IO){
            galleryItems.postValue(APIREPOSITRETROFIT.getPopularImgs(0))
            Log.d("Lol", "init ${APIREPOSITRETROFIT.getPopularImgs(0).body()?.data?.get(0)}")
        }
    }
    fun getImageGal() = galleryItems
}