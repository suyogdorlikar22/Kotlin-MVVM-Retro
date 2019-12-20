package com.assignment.kotlinmvvmKetlus.repository

import androidx.lifecycle.MutableLiveData
import android.util.Log
import com.assignment.kotlinmvvmKetlus.Model.Users
import com.assignment.kotlinmvvmKetlus.network.RetrofitService.Factory.create
import retrofit2.Call
import retrofit2.Callback

class PhotosRepository {

    val liveUserResponse: MutableLiveData<List<Users>> = MutableLiveData()

     val mIsUpdating = MutableLiveData<Boolean>()

    fun getIsUpdating(): MutableLiveData<Boolean> {
        return mIsUpdating
    }

    fun loadPhotosData(): MutableLiveData<List<Users>>? {

        mIsUpdating.value=true;
        Log.e("loadPhotosData","yes")

        val retrofitCall  = create().getPhotos()

        retrofitCall.enqueue(object : Callback<List<Users>> {
            override fun onFailure(call: Call<List<Users>>, t: Throwable?) {
                Log.e("on Failure :", "retrofit error")
                mIsUpdating.value=false;

            }

            override fun onResponse(call: Call<List<Users>>, response: retrofit2.Response<List<Users>>) {
                mIsUpdating.value=false;

                val list  = response.body()
                for (i in list.orEmpty()){
                }

                liveUserResponse.value = list

                Log.e("hasActiveObservers 1", liveUserResponse.hasActiveObservers().toString()+" check")

                Log.e("on response 2 :", liveUserResponse.toString()+" check")

            }

        })

        return liveUserResponse
    }
}
