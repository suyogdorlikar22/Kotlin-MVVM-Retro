package com.assignment.kotlinmvvmKetlus.network

import com.assignment.kotlinmvvmKetlus.Model.Users
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("users")
    fun getPhotos(): Call<List<Users>>

}