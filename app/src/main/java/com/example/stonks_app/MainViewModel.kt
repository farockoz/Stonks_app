package com.example.stonks_app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel:ViewModel() {
    val LiveDataCurrent = MutableLiveData<String>()
}