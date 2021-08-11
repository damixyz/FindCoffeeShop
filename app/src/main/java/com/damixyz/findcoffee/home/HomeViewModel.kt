package com.damixyz.findcoffee.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.damixyz.findcoffee.BuildConfig
import com.damixyz.usecases.data.HomeScreen
import com.damixyz.usecases.data.ScreenState

class HomeViewModel
    : ViewModel() {
    private val _homeScreenState = MutableLiveData<ScreenState?>()
    val homeScreenState: LiveData<ScreenState?>
        get() = _homeScreenState
    val clientId: String = BuildConfig.CLIENT_ID
    val clientSecret: String = BuildConfig.CLIENT_SECRET
    val versionParam: Int = BuildConfig.VERSION_PARAMETER
    val limit: Int = BuildConfig.LIMIT_PARAMETER
    val query: String = BuildConfig.QUERY_PARAMETER


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

}