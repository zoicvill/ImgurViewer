package com.example.imgurviewer

import com.example.imgurviewer.network.ResponseData
import com.example.imgurviewer.network.RetrofitRepository

const val BASE_URL = "https://api.imgur.com/3/"
const val CLIENT_ID = "5938d4667f5670f"
const val REGEX = "^https?://\\S+(?:jpg|jpeg|png|gif)\$"
lateinit var APIREPOSITRETROFIT: ResponseData