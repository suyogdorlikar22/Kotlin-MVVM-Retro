package com.assignment.kotlinmvvmKetlus.view.main

import android.app.Dialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.assignment.kotlinmvvmKetlus.Model.Users
import com.assignment.kotlinmvvmKetlus.R
import com.assignment.kotlinmvvmKetlus.app.GridItemDecoration
import com.assignment.kotlinmvvmKetlus.app.Utils
import com.assignment.kotlinmvvmKetlus.comunicator.ItemClickListener
import com.assignment.kotlinmvvmKetlus.view.showimage.ShowImageActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener,ItemClickListener {
    private lateinit var viewModel: MainViewModel
    private lateinit var photosAdapter: PhotosAdapter
    // Initializing an empty ArrayList to be filled with animals
    val photoList: ArrayList<Users> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this@MainActivity).get(MainViewModel::class.java)

        recyclerView.layoutManager = GridLayoutManager(
                this@MainActivity,
                2,
                RecyclerView.VERTICAL,
                true
        )

        //This will for default android divider
        recyclerView.addItemDecoration(GridItemDecoration(10, 2))
        photosAdapter= PhotosAdapter(this,photoList,this)
        recyclerView!!.adapter = photosAdapter

        if (Utils.checkInternetConnection(this@MainActivity)) {

            getUpdating()
            // Its Available...
            getPhotos()

            floatAdd.setOnClickListener(this)


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
        Log.e("getPhotos","yes")

        viewModel.getPhotosData()?.observe(this, Observer<List<Users>> { list ->

            photoList.clear()
            Log.e("list",list?.size.toString())
            photoList.addAll(list)
            photosAdapter.notifyDataSetChanged()

        })

    }

    override fun onClick(v: View?) {
        if (v==floatAdd){
            showDialog()
        }
    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog .requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog .setCancelable(false)
        dialog .setContentView(R.layout.cutsom_layout_add)
        val edUsername = dialog .findViewById(R.id.edUsername) as EditText
        val edUsertype = dialog .findViewById(R.id.edUsertype) as EditText
        val btnAdd = dialog .findViewById(R.id.btnAdd) as Button

        btnAdd.setOnClickListener {
            if (!edUsername.text.toString().equals("")&&(!edUsertype.text.toString().equals(""))){
                val rnds = (15887..152224545454).random()
                var usrs=Users("https://avatars0.githubusercontent.com/u/18?v=4",
                        "","","","","","", rnds.toInt(),edUsername.text.toString(),
                "","","","",true,"","",edUsertype.text.toString(),"");
                photoList.add(usrs)
                photosAdapter.notifyDataSetChanged()
                Toast.makeText(this,"User add",Toast.LENGTH_SHORT).show()

                dialog.dismiss()
            }

        }
        dialog.setCancelable(true)
//        noBtn.setOnClickListener { dialog .dismiss() }
        dialog .show()

    }

    override fun onItemClick(pos: Int) {

        val intent = Intent(this@MainActivity, ShowImageActivity::class.java)
        intent.putExtra("image", photoList.get(pos).avatar_url)
        intent.putExtra("username", photoList.get(pos).login)
        intent.putExtra("usertype", photoList.get(pos).type)
        startActivity(intent)
    }


}

