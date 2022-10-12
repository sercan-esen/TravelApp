package com.example.travelapp.presentation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.travelapp.data.entity.Travel
import com.example.travelapp.databinding.HomeRecyclerRowBinding

class HomeAllAdapter (private val context:Context) :
RecyclerView.Adapter<HomeAllAdapter.ViewHolder>(){
    private var homeAllList: List<Travel> = emptyList()
    private lateinit var listener: (Travel) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(HomeRecyclerRowBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun onBindViewHolder(holder: HomeAllAdapter.ViewHolder, position: Int) {
        val homeAll = homeAllList.get(position)

        holder.rootView.ivHomeRecycler.apply {
            loadWithGlide(homeAll.images.get(0).url)
            setOnClickListener {
                listener.invoke(homeAll) //callback triggered
            }
        }
    }

    override fun getItemCount(): Int  = homeAllList.size



    inner class ViewHolder(view: HomeRecyclerRowBinding) : RecyclerView.ViewHolder(view.root) {
        val rootView = view
    }

    fun updateList(list : List<Travel>){
        homeAllList = list
        notifyDataSetChanged()
    }
    private fun ImageView.loadWithGlide(imageUrl: String) {
        Glide
            .with(this)
            .load(imageUrl)
            .into(this)
    }

    fun onHomeItemSelected (callback: (Travel) -> Unit){
        listener = callback
    }
}