package com.example.travelapp.domain.usecase

import com.example.travelapp.data.entity.GuideCategories
import com.example.travelapp.domain.repository.GuideRepository
import javax.inject.Inject

class GetGuideCategoriesUseCase @Inject constructor(private val guideRepository: GuideRepository):UseCase<ArrayList<GuideCategories>,Any?>() {
    override suspend fun run(params: Any?): ArrayList<GuideCategories> {
        return guideRepository.getGuideCategories()
    }
}