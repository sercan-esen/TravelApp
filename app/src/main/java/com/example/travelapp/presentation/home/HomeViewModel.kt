package com.example.travelapp.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.travelapp.data.entity.Travel
import com.example.travelapp.domain.model.ApiError
import com.example.travelapp.domain.usecase.GetTravelsByCategoryUseCase
import com.example.travelapp.domain.usecase.GetTravelsUseCase
import com.example.travelapp.domain.usecase.UseCaseResponse
import com.example.travelapp.presentation.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTravelsUseCase: GetTravelsUseCase,
    private val getTravelsByCategoryUseCase: GetTravelsByCategoryUseCase
) :
    BaseViewModel() {

    val travelsData = MutableLiveData<ArrayList<Travel>>()

    fun getAllTravels() {
        isLoadingRequest.postValue(true)
        getTravelsUseCase.invoke(viewModelScope, null, object : UseCaseResponse<ArrayList<Travel>> {
            override fun onSuccess(result: ArrayList<Travel>) {
                travelsData.postValue(result)
                isLoadingRequest.postValue(false)
            }

            override fun onError(apiError: ApiError?) {
                isErrorRequest.postValue(apiError?.getErrorMessage())
                isLoadingRequest.postValue(false)
            }
        })
    }

    fun getTravelsByCategory(category: String) {
        isLoadingRequest.postValue(true)
        getTravelsByCategoryUseCase.invoke(
            viewModelScope,
            category,
            object : UseCaseResponse<ArrayList<Travel>> {
                override fun onSuccess(result: ArrayList<Travel>) {
                    travelsData.postValue(result)
                    isLoadingRequest.postValue(false)
                }

                override fun onError(apiError: ApiError?) {
                    isErrorRequest.postValue(apiError?.getErrorMessage())
                    isLoadingRequest.postValue(false)
                }
            })

    }
}