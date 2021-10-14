package com.example.imgurviewer.ui.imagegallery

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.imgurviewer.APIREPOSITRETROFIT
import com.example.imgurviewer.model.Comments
import com.example.imgurviewer.model.GalleryItems
import com.example.imgurviewer.network.ResponseData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImageGalleryViewModel(application: Application) : AndroidViewModel(application) {

    private var galleryItems: MutableLiveData<List<GalleryItems.DataItem?>> = MutableLiveData()
    fun getGalleryItems() = galleryItems

    private var pag: Int = 0

    init {
        Log.d("Lol", "init")
        APIREPOSITRETROFIT = ResponseData()
        pagination(pag)
    }

    /*пагинация */
    fun pagination(pg: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            if (APIREPOSITRETROFIT.getPopularImgs(pg).isSuccessful) {
                galleryItems.postValue(APIREPOSITRETROFIT.getPopularImgs(pg)
                    .body()?.data?.filter { x -> x?.images.isNullOrEmpty().not() })
                pag = pg
            } else {
                Toast.makeText(
                    getApplication(), "No ${
                        APIREPOSITRETROFIT.getPopularImgs(0)
                            .code()
                    }", Toast.LENGTH_LONG
                ).show()
            }
        }
    }

}