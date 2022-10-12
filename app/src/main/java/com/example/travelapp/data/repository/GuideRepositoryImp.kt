package com.example.travelapp.data.repository

import com.example.travelapp.data.entity.GuideCategories
import com.example.travelapp.data.remote.ApiService
import com.example.travelapp.domain.repository.GuideRepository
import javax.inject.Inject

class GuideRepositoryImp @Inject constructor(private val apiService: ApiService): GuideRepository {
    override suspend fun getGuideCategories(): ArrayList<GuideCategories> =apiService.getGuideCategories()
}