package com.assignment.kotlinmvvmKetlus.view.showimage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.assignment.kotlinmvvmKetlus.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_show_image.*
import uk.co.senab.photoview.PhotoViewAttacher


class ShowImageActivity : AppCompatActivity() {
    var image = "";
    var username = "";
    var usertype = "";


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_image)
        val pAttacher: PhotoViewAttacher
        pAttacher = PhotoViewAttacher(ivFullImage)
        pAttacher.update()
        image = intent.getStringExtra("image")
        username = intent.getStringExtra("username")
        usertype = intent.getStringExtra("usertype")

        tvUsertype?.text = usertype
        tvUsername?.text = username

        Glide.with(this)
                .load(image)
                .into(ivFullImage)




    }

}
