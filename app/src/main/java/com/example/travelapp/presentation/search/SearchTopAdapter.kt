package com.example.travelapp.presentation.search

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.travelapp.data.entity.Travel
import com.example.travelapp.databinding.SearchTopRecyclerRowBinding

class SearchTopAdapter (private val context: Context) :
RecyclerView.Adapter<SearchTopAdapter.ViewHolder>(){
    private var searchAllList : List<Travel> = emptyList()
    private lateinit var listener: (Travel) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(SearchTopRecyclerRowBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun onBindViewHolder(holder: SearchTopAdapter.ViewHolder, position: Int) {
        val searchAll = searchAllList.get(position)

        holder.rootView.apply {
            ivSearchTopRecycler.apply {
                loadWithGlide(searchAll.images.get(0).url)
                setOnClickListener {
                    listener.invoke(searchAll)
                }
            }
            tvSearchTopCountry.text=searchAll.country
            tvSearchTopCity.text=searchAll.city
        }

    }
    override fun getItemCount(): Int =searchAllList.size

    inner class ViewHolder(view: SearchTopRecyclerRowBinding) : RecyclerView.ViewHolder(view.root) {
        val rootView = view
    }


    private fun ImageView.loadWithGlide(imageUrl: String) {
        Glide
            .with(this)
            .load(imageUrl)
            .into(this)
    }
    fun updateList(list : List<Travel>){
        searchAllList = list
        notifyDataSetChanged()
    }
    fun onSearchTopItemItemSelected (callback: (Travel) -> Unit){
        listener = callback
    }


}