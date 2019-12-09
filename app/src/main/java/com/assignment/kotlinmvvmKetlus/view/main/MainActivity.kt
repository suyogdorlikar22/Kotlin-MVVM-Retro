package com.assignment.kotlinmvvmKetlus.view.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.assignment.kotlinmvvmKetlus.DataModel.Photos
import com.assignment.kotlinmvvmKetlus.R
import com.assignment.kotlinmvvmKetlus.app.GridItemDecoration
import com.assignment.kotlinmvvmKetlus.app.Utils
import com.assignment.kotlinmvvmKetlus.comunicator.ItemClickListener
import com.assignment.kotlinmvvmKetlus.view.showimage.ShowImageActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var viewModel: AndroidViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this@MainActivity).get(AndroidViewModel::class.java)

        recyclerView.layoutManager = GridLayoutManager(this@MainActivity,2)
        //This will for default android divider
        recyclerView.addItemDecoration(GridItemDecoration(10, 2))
        if (Utils.checkInternetConnection(this@MainActivity)) {

            getUpdating()
            // Its Available...
            getPhotos()


        } else {
            Toast.makeText(applicationContext, "Check Internet connection", Toast.LENGTH_LONG).show()

            // Not Available...
        }



    }

    private fun getUpdating() {

        viewModel.getIsUpdating().observe(this, Observer { t ->
            if (t==true){
                progress_circular.visibility;View.VISIBLE
            }else{
                progress_circular.visibility=View.GONE
            }
        })
    }


    private fun getPhotos() {
        Log.e("getAndroidVersion","yes")

        viewModel.getPhotosData()?.observe(this, Observer<List<Photos>> { androidList ->

            Log.e("list",androidList?.size.toString())
            recyclerView.adapter = PhotosAdapter(this@MainActivity, androidList as ArrayList<Photos>, object : ItemClickListener {
                override fun onItemClick(pos: Int) {
                    androidList.get(pos).url
                    Log.d("getPhotoVersion", androidList.get(pos).url)

                    val intent = Intent(this@MainActivity, ShowImageActivity::class.java)
                    intent.putExtra("image", androidList.get(pos).url)
                    startActivity(intent)
                }
            })
        })

    }
}

