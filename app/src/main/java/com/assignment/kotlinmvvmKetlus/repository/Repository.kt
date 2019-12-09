package com.assignment.kotlinmvvmKetlus.repository

import androidx.lifecycle.MutableLiveData
import android.util.Log
import com.assignment.kotlinmvvmKetlus.network.RetrofitService.Factory.create
import com.assignment.kotlinmvvmKetlus.Model.Photos
import retrofit2.Call
import retrofit2.Callback

class PhotosRepository {

    val liveUserResponse: MutableLiveData<List<Photos>> = MutableLiveData()

     val mIsUpdating = MutableLiveData<Boolean>()

    fun getIsUpdating(): MutableLiveData<Boolean> {
        return mIsUpdating
    }

    fun loadPhotosData(): MutableLiveData<List<Photos>>? {

        mIsUpdating.value=true;
        Log.e("loadAndroidData","yes")

        val retrofitCall  = create().getPhotos()

        retrofitCall.enqueue(object : Callback<List<Photos>> {
            override fun onFailure(call: Call<List<Photos>>, t: Throwable?) {
                Log.e("on Failure :", "retrofit error")
                mIsUpdating.value=false;

            }

            override fun onResponse(call: Call<List<Photos>>, response: retrofit2.Response<List<Photos>>) {
                mIsUpdating.value=false;

                val list  = response.body()
                for (i in list.orEmpty()){
                    Log.e("on response 1:", i.url)
                }

                liveUserResponse.value = list

                Log.e("hasActiveObservers 1", liveUserResponse.hasActiveObservers().toString()+" check")

                Log.e("on response 2 :", liveUserResponse.toString()+" check")

            }

        })

        return liveUserResponse
    }
}
