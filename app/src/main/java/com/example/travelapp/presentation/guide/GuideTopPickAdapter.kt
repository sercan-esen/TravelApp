package com.example.travelapp.presentation.guide

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.travelapp.data.entity.Travel
import com.example.travelapp.databinding.GuideTopPickRecyclerRowBinding

class GuideTopPickAdapter(private val context: Context) :
RecyclerView.Adapter<GuideTopPickAdapter.ViewHolder>(){
    private var guideAllList : List<Travel> = emptyList()
    private lateinit var listener: (Travel) -> Unit



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(GuideTopPickRecyclerRowBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun onBindViewHolder(holder: GuideTopPickAdapter.ViewHolder, position: Int) {
        val guideAll = guideAllList.get(position)

        holder.rootView.apply {
            ivGuideTopPickRecycler.loadWithGlide(guideAll.images.get(0).url)
            tvGuideTopPickExpRecycler.text = guideAll.title
            tvGuideTopPickDescRecycler.text = guideAll.description
            topPickCardView.setOnClickListener {
                listener.invoke(guideAll)
            }
        }


    }

    override fun getItemCount(): Int = guideAllList.size

    inner class ViewHolder(view: GuideTopPickRecyclerRowBinding): RecyclerView.ViewHolder(view.root){
        val rootView = view
    }

    private fun ImageView.loadWithGlide(imageUrl: String){
        Glide
            .with(this)
            .load(imageUrl)
            .into(this)
    }
    fun updateList(list : List<Travel>){
        guideAllList = list
        notifyDataSetChanged()
    }
    fun onGuideTopPickItemItemSelected (callback: (Travel) -> Unit){
        listener = callback
    }
}