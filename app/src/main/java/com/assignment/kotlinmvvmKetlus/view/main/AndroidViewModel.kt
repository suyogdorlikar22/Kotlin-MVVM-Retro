package com.assignment.kotlinmvvmKetlus.view.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.assignment.kotlinmvvmKetlus.DataModel.Photos
import com.assignment.kotlinmvvmKetlus.repository.PhotosRepository

class AndroidViewModel: ViewModel() {

    private val mRepository  =  PhotosRepository()

    fun getPhotosData() : MutableLiveData<List<Photos>>? {
        Log.e("getAndroidData","yes")
        return mRepository.loadPhotosData()
    }

    fun getIsUpdating(): MutableLiveData<Boolean> {
        return mRepository.getIsUpdating()
    }
}
