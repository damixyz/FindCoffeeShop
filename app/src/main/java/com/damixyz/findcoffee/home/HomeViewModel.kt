package com.damixyz.findcoffee.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.damixyz.usecases.data.HomeScreen
import com.damixyz.usecases.data.ScreenState

class HomeViewModel
    : ViewModel() {
    private val _homeScreenState = MutableLiveData<ScreenState?>()
    val homeScreenState: LiveData<ScreenState?>
        get() = _homeScreenState

    fun launchVenuesFragment() {
        _homeScreenState.value = HomeScreen.LaunchVenues
    }

    fun onFabSelected() {
        launchVenuesFragment()
    }

    fun start() {
        if ((_homeScreenState.value is HomeScreen.Content).not()) {
            _homeScreenState.value = null
        }
    }

    fun getSavedVenues() {
        _homeScreenState.value = ScreenState.Empty
    }
}