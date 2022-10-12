package com.example.travelapp.presentation.util.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

fun <T> LifecycleOwner.observe(liveData: MutableLiveData<T>?, observer: (T) -> Unit) {
    liveData?.observe(this, Observer(observer))
}