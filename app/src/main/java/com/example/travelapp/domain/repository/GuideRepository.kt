package com.example.travelapp.domain.repository

import com.example.travelapp.data.entity.GuideCategories

interface GuideRepository {
    suspend fun getGuideCategories(): ArrayList<GuideCategories>
}