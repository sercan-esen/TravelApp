package com.example.travelapp.data.remote

import com.example.travelapp.data.entity.GuideCategories
import com.example.travelapp.data.entity.Travel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/AllTravelList")
    suspend fun getAllTravels(): ArrayList<Travel>

    @GET("/AllTravelList")
    suspend fun getTravelsByCategory(
        @Query("category") category: String
    ) : ArrayList<Travel>

    @GET("/GuideCategories")
    suspend fun getGuideCategories(): ArrayList<GuideCategories>


}