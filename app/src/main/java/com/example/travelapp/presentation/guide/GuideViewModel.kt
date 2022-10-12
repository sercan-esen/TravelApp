package com.example.travelapp.presentation.guide

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.travelapp.data.entity.GuideCategories
import com.example.travelapp.data.entity.Travel
import com.example.travelapp.domain.model.ApiError
import com.example.travelapp.domain.usecase.GetGuideCategoriesUseCase
import com.example.travelapp.domain.usecase.GetTravelsByCategoryUseCase
import com.example.travelapp.domain.usecase.UseCaseResponse
import com.example.travelapp.presentation.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GuideViewModel @Inject constructor(
    private val getTravelsByCategoryUseCase: GetTravelsByCategoryUseCase,
    private val getGuideCategoriesUseCase: GetGuideCategoriesUseCase
) : BaseViewModel() {

    val mightNeedData = MutableLiveData<ArrayList<Travel>>()

    fun getTravelsByCategoryMightNeed() {
        isLoadingRequest.postValue(true)
        getTravelsByCategoryUseCase.invoke(
            viewModelScope,
            "mightneed",
            object : UseCaseResponse<ArrayList<Travel>> {
                override fun onSuccess(result: ArrayList<Travel>) {
                    mightNeedData.postValue(result)
                    isLoadingRequest.postValue(false)
                }

                override fun onError(apiError: ApiError?) {
                    isErrorRequest.postValue(apiError?.getErrorMessage())
                    isLoadingRequest.postValue(false)
                }
            })

    }

    val topPickData = MutableLiveData<ArrayList<Travel>>()

    fun getTravelsByCategoryTopPick() {
        isLoadingRequest.postValue(true)
        getTravelsByCategoryUseCase.invoke(
            viewModelScope,
            "toppick",
            object : UseCaseResponse<ArrayList<Travel>> {
                override fun onSuccess(result: ArrayList<Travel>) {
                    topPickData.postValue(result)
                    isLoadingRequest.postValue(false)
                }

                override fun onError(apiError: ApiError?) {
                    isErrorRequest.postValue(apiError?.getErrorMessage())
                    isLoadingRequest.postValue(false)
                }
            })

    }
    val guideCategoriesData = MutableLiveData<ArrayList<GuideCategories>>()

    fun getGuideCategories(){
        isLoadingRequest.postValue(true)
        getGuideCategoriesUseCase.invoke(
            viewModelScope,null,object : UseCaseResponse<ArrayList<GuideCategories>> {
                override fun onSuccess(result: ArrayList<GuideCategories>) {
                    guideCategoriesData.postValue(result)
                    isLoadingRequest.postValue(false)
                }

                override fun onError(apiError: ApiError?) {
                    isErrorRequest.postValue(apiError?.getErrorMessage())
                    isLoadingRequest.postValue(false)
                }

            })


    }

}