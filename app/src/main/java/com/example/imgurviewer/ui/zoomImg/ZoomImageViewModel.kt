package com.example.imgurviewer.ui.zoomImg

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.imgurviewer.APIREPOSITRETROFIT
import com.example.imgurviewer.model.Comments
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ZoomImageViewModel(application: Application) : AndroidViewModel(application) {

    private var commentsItems: MutableLiveData<List<Comments.DataItem?>> = MutableLiveData()
    fun getComments() = commentsItems

    fun comments(str: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (APIREPOSITRETROFIT.getComments(str).isSuccessful) {
                commentsItems.postValue(APIREPOSITRETROFIT.getComments(str).body()?.data)
                Log.d("Lol", "getComments if ${APIREPOSITRETROFIT.getComments(str).body()?.data}")
            } else {
                Log.d("Lol", "getComments ${APIREPOSITRETROFIT.getPopularImgs(0).code()}")
            }
        }
    }

}