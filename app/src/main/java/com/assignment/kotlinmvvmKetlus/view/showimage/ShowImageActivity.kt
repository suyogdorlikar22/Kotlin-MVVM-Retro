package com.assignment.kotlinmvvmKetlus.view.showimage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.assignment.kotlinmvvmKetlus.R
import com.bumptech.glide.Glide

import kotlinx.android.synthetic.main.activity_show_image.*

class ShowImageActivity : AppCompatActivity() {
    var image = "";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_image)

        image = intent.getStringExtra("image")
        Glide.with(this)
                .load(image)
                .into(ivFullImage)

    }

}
