package com.example.travelapp.data.repository

import com.example.travelapp.data.entity.Travel
import com.example.travelapp.data.remote.ApiService
import com.example.travelapp.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImp @Inject constructor(private val apiService: ApiService) : HomeRepository {
    override suspend fun getAllTravels(): ArrayList<Travel> = apiService.getAllTravels()
    override suspend fun getTravelsByCategory(category: String): ArrayList<Travel> = apiService.getTravelsByCategory(category)


}