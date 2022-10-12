package com.example.travelapp.domain.usecase

import com.example.travelapp.data.entity.Travel
import com.example.travelapp.domain.repository.HomeRepository
import javax.inject.Inject

class GetTravelsUseCase @Inject constructor(private val homeRepository: HomeRepository): UseCase<ArrayList<Travel>, Any?>(){
    override suspend fun run(params: Any?): ArrayList<Travel> {
        return homeRepository.getAllTravels()
    }
}