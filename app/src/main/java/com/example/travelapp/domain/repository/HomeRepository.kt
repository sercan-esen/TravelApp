package com.example.travelapp.domain.repository

import com.example.travelapp.data.entity.Travel

interface HomeRepository {

    suspend fun getAllTravels(): ArrayList<Travel>
    suspend fun getTravelsByCategory(category: String): ArrayList<Travel>


}