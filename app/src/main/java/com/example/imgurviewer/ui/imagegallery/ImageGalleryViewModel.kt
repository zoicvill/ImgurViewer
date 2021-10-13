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

class ImageGalleryViewModel(application: Application): AndroidViewModel(application) {

    private var galleryItems: MutableLiveData<List<GalleryItems.DataItem?>> = MutableLiveData()
    fun getGalleryItems() = galleryItems

    private var commentsItems: MutableLiveData<List<Comments.DataItem?>> = MutableLiveData()
    fun getComments() = commentsItems
    private var pag: Int = 0

    init {
        APIREPOSITRETROFIT = ResponseData()
      pagination(pag)
    }

    fun pagination(pg: Int){
        viewModelScope.launch(Dispatchers.IO){
            if (APIREPOSITRETROFIT.getPopularImgs(pg).isSuccessful){
                galleryItems.postValue(APIREPOSITRETROFIT.getPopularImgs(pg)
                    .body()?.data?.filter { x -> x?.images.isNullOrEmpty().not()})
                pag = pg
            }
            else{
                Toast.makeText(getApplication(), "No ${APIREPOSITRETROFIT.getPopularImgs(0)
                    .code()}", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun comments(str: String){
        viewModelScope.launch(Dispatchers.IO){
            if (APIREPOSITRETROFIT.getComments(str).isSuccessful){
                commentsItems.postValue(APIREPOSITRETROFIT.getComments(str).body()?.data)
                Log.d("Lol", "getComments if ${APIREPOSITRETROFIT.getComments(str).body()?.data}")
            }
            else{
                Log.d("Lol", "getComments ${APIREPOSITRETROFIT.getPopularImgs(0).code()}")
            }
        }
    }


}