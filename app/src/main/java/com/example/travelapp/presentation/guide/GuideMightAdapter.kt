package com.example.travelapp.presentation.guide

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.travelapp.data.entity.Travel
import com.example.travelapp.databinding.GuideMightRecyclerRowBinding


class GuideMightAdapter(private val context: Context) :
    RecyclerView.Adapter<GuideMightAdapter.ViewHolder>() {
    private var guideAllList: List<Travel> = emptyList()
    private lateinit var listener: (Travel) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            GuideMightRecyclerRowBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GuideMightAdapter.ViewHolder, position: Int) {
        val guideAll = guideAllList.get(position)

        holder.rootView.apply {
            ivGuideMightRecycler.apply {
                loadWithGlide(guideAll.images.get(0).url)
                setOnClickListener {
                    listener.invoke(guideAll)
                }
            }
            tvGuideMightTitle.text = guideAll.title
        }
    }

    override fun getItemCount(): Int = guideAllList.size

    inner class ViewHolder(view: GuideMightRecyclerRowBinding) :
        RecyclerView.ViewHolder(view.root) {
        val rootView = view
    }

    private fun ImageView.loadWithGlide(imageUrl: String) {
        Glide
            .with(this)
            .load(imageUrl)
            .into(this)
    }

    fun updateList(list: List<Travel>) {
        guideAllList = list
        notifyDataSetChanged()
    }

    fun onGuideMightItemItemSelected(callback: (Travel) -> Unit) {
        listener = callback
    }
}