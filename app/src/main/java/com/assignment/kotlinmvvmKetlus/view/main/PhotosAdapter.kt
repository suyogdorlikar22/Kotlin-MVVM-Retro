package com.assignment.kotlinmvvmKetlus.view.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.assignment.kotlinmvvmKetlus.DataModel.Photos
import com.assignment.kotlinmvvmKetlus.R
import com.assignment.kotlinmvvmKetlus.comunicator.ItemClickListener
import com.bumptech.glide.Glide
import com.jakewharton.rxbinding.view.RxView
import kotlinx.android.synthetic.main.items_photos.view.*

class PhotosAdapter(var context: MainActivity, var mEmpList: ArrayList<Photos>, private val itemClick: ItemClickListener): RecyclerView.Adapter<PhotosAdapter.EmpHolder>()  {

    companion object {
        var mItemClickListener : ItemClickListener? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmpHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.items_photos, parent, false)
        return EmpHolder(view)
    }

    override fun getItemCount(): Int {
        return mEmpList.size
    }

    override fun onBindViewHolder(holder: EmpHolder, position: Int) {
        mItemClickListener = itemClick


//        holder.tvFname?.text = mEmpList[position].title
//        holder.tvLname?.text = mEmpList[position].url

        Glide.with(context)
                .load(mEmpList[position].url)
                .into(holder.ivPhotos)


        RxView.clicks(holder.mView).subscribe {
            mItemClickListener!!.onItemClick(position)
        }
    }


    class EmpHolder (view: View) : RecyclerView.ViewHolder(view) {
        val ivPhotos = view.ivPhotos

        val mView = view
    }

}