package com.example.travelapp.presentation.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.travelapp.data.entity.Travel
import com.example.travelapp.domain.model.ApiError
import com.example.travelapp.domain.usecase.GetTravelsByCategoryUseCase
import com.example.travelapp.domain.usecase.UseCaseResponse
import com.example.travelapp.presentation.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getTravelsByCategoryUseCase: GetTravelsByCategoryUseCase
) : BaseViewModel() {
    val nearbyData = MutableLiveData<ArrayList<Travel>>()

    fun getTravelsByCategoryNearby() {
        isLoadingRequest.postValue(true)
        getTravelsByCategoryUseCase.invoke(
            viewModelScope,
            "nearby",
            object : UseCaseResponse<ArrayList<Travel>> {
                override fun onSuccess(result: ArrayList<Travel>) {
                    nearbyData.postValue(result)
                    isLoadingRequest.postValue(false)
                }

                override fun onError(apiError: ApiError?) {
                    isErrorRequest.postValue(apiError?.getErrorMessage())
                    isLoadingRequest.postValue(false)
                }
            })

    }

    val topDestinationData = MutableLiveData<ArrayList<Travel>>()

    fun getTravelsByCategoryTopDestination() {
        isLoadingRequest.postValue(true)
        getTravelsByCategoryUseCase.invoke(
            viewModelScope,
            "topdestination",
            object : UseCaseResponse<ArrayList<Travel>> {
                override fun onSuccess(result: ArrayList<Travel>) {
                    topDestinationData.postValue(result)
                    isLoadingRequest.postValue(false)
                }

                override fun onError(apiError: ApiError?) {
                    isErrorRequest.postValue(apiError?.getErrorMessage())
                    isLoadingRequest.postValue(false)
                }
            })

    }
}