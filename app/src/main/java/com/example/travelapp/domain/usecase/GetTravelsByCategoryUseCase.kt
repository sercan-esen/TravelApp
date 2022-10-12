package com.example.travelapp.domain.usecase

import com.example.travelapp.data.entity.Travel
import com.example.travelapp.domain.repository.HomeRepository
import javax.inject.Inject

class GetTravelsByCategoryUseCase @Inject constructor(private val homeRepository: HomeRepository): UseCase<ArrayList<Travel>,String>() {
    override suspend fun run(params: String?): ArrayList<Travel> {
        return homeRepository.getTravelsByCategory(category = params!!)
    }
}