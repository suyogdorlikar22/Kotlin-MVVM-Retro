package com.assignment.kotlinmvvmKetlus.view.main

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.assignment.kotlinmvvmKetlus.Model.Users
import com.assignment.kotlinmvvmKetlus.R
import com.assignment.kotlinmvvmKetlus.comunicator.ItemClickListener
import com.bumptech.glide.Glide
import com.jakewharton.rxbinding.view.RxView
import kotlinx.android.synthetic.main.items_photos.view.*

class PhotosAdapter(var context: MainActivity, var mEmpList: ArrayList<Users>, private val itemClick: ItemClickListener): RecyclerView.Adapter<PhotosAdapter.PhotoHolder>()  {

    companion object {
        var mItemClickListener : ItemClickListener? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.items_photos, parent, false)
        return PhotoHolder(view)
    }

    override fun getItemCount(): Int {
        return mEmpList.size
    }

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        mItemClickListener = itemClick


        holder.tvUsertype?.text = mEmpList[position].login
        holder.tvUsername?.text = mEmpList[position].type

        Glide.with(context)
                .load(mEmpList[position].avatar_url)
                .into(holder.ivPhotos)


        RxView.clicks(holder.mView).subscribe {
            mItemClickListener!!.onItemClick(position)
        }

        RxView.clicks(holder.btnDelete).subscribe {
            mEmpList.removeAt(position)
            notifyDataSetChanged()

        }


    }


    class PhotoHolder (view: View) : RecyclerView.ViewHolder(view) {
        val ivPhotos = view.ivPhotos
        val tvUsername = view.tvUsername
        val tvUsertype = view.tvUsertype
        val btnDelete = view.btnDelete

        val mView = view
    }

}