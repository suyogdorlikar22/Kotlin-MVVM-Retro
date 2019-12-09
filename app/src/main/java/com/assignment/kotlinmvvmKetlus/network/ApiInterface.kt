package com.assignment.kotlinmvvmKetlus.network

import com.assignment.kotlinmvvmKetlus.DataModel.Photos
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("photos")
    fun getPhotos(): Call<List<Photos>>

}