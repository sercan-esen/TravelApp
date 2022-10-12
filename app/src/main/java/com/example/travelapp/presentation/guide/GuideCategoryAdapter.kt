package com.example.travelapp.presentation.guide

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travelapp.R
import com.example.travelapp.data.entity.GuideCategories
import com.example.travelapp.databinding.GuideCategoryRecyclerRowBinding

class GuideCategoryAdapter(private val context: Context) :
    RecyclerView.Adapter<GuideCategoryAdapter.ViewHolder>() {
    private var guideCategoryList: List<GuideCategories> = emptyList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GuideCategoryAdapter.ViewHolder {
        return ViewHolder(
            GuideCategoryRecyclerRowBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GuideCategoryAdapter.ViewHolder, position: Int) {
        val guideCategory = guideCategoryList.get(position)

        holder.rootView.apply {
            tvGuideCategory.text = guideCategory.title
            val icon = guideCategory.icon
            val changedIcon =
                when (icon) {
                    "mall" -> R.drawable.mall
                    "museum" -> R.drawable.museum
                    "rentcar" -> R.drawable.rentcar
                    "resort" -> R.drawable.resort
                    "restaurant" -> R.drawable.restaurant
                    "sightseeing" -> R.drawable.sightseeing
                    else -> R.drawable.taxi
                }
            ivGuideIcon.setImageResource(changedIcon)

        }

    }


    override fun getItemCount(): Int = guideCategoryList.size


    inner class ViewHolder(view: GuideCategoryRecyclerRowBinding) :
        RecyclerView.ViewHolder(view.root) {
        val rootView = view
    }

    fun updateList(list: List<GuideCategories>) {
        guideCategoryList = list
        notifyDataSetChanged()
    }


}