package com.example.travelapp.presentation.core

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), CoroutineScope {

    var isLoadingRequest = MutableLiveData<Boolean>()
    var isErrorRequest = MutableLiveData<String>()

    private var job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + job



    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}