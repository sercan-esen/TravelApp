package com.example.travelapp.domain.usecase

import com.example.travelapp.domain.model.ApiError

interface UseCaseResponse<Type> {

    fun onSuccess(result: Type)

    fun onError(apiError: ApiError?)
}