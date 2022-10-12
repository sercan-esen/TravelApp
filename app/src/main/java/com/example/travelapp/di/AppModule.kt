package com.example.travelapp.di

import android.content.Context
import com.example.travelapp.BaseApp
import com.example.travelapp.data.remote.ApiService
import com.example.travelapp.data.repository.GuideRepositoryImp
import com.example.travelapp.data.repository.HomeRepositoryImp
import com.example.travelapp.domain.repository.GuideRepository
import com.example.travelapp.domain.repository.HomeRepository
import com.example.travelapp.domain.usecase.GetGuideCategoriesUseCase
import com.example.travelapp.domain.usecase.GetTravelsByCategoryUseCase
import com.example.travelapp.domain.usecase.GetTravelsUseCase
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val SHARED_PREFERENCES_FILE_NAME = "SharedPref"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideSharedPreferences() = BaseApp.applicationContext().getSharedPreferences(
        SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE
    )

    @Singleton
    @Provides
    fun provideGson() = Gson()

    @Singleton
    @Provides
    fun provideHomeRepository(apiService: ApiService): HomeRepository =
        HomeRepositoryImp(apiService)

    @Singleton
    @Provides
    fun provideGetTravelsUseCase(homeRepository: HomeRepository) = GetTravelsUseCase(homeRepository)

    @Singleton
    @Provides
    fun provideGetTravelsByCategoryUseCase(homeRepository: HomeRepository) = GetTravelsByCategoryUseCase(homeRepository)

    @Singleton
    @Provides
    fun provideGuideRepository(apiService: ApiService): GuideRepository = GuideRepositoryImp(apiService)

    @Singleton
    @Provides
    fun provideGetGuideCategoriesUseCase(guideRepository: GuideRepository) = GetGuideCategoriesUseCase(guideRepository)
}