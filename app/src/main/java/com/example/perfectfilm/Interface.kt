package com.example.perfectfilm


import com.example.perfectfilm.Model.ViewModel
import retrofit2.Call
import retrofit2.http.GET

interface Interface {
    @GET("top_rated?&api_key=${BuildConfig.API_KEY}")
    fun getTop_Movie(): Call<ViewModel>
}