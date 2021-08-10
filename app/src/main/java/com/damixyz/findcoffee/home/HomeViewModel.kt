package com.damixyz.findcoffee.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.damixyz.usecases.GetCoffeeShopInfoUseCase
import com.damixyz.usecases.data.HomeScreen
import com.damixyz.usecases.data.ScreenState

class HomeViewModel(
    private val useCase: GetCoffeeShopInfoUseCase
) : ViewModel() {
    private val _homeScreenState = MutableLiveData<ScreenState>()
    val homeScreenState: LiveData<ScreenState>
        get() = _homeScreenState

    fun launchVenuesFragment() {
        _homeScreenState.value = HomeScreen.LaunchVenues
    }

    fun onFabSelected() {
        launchVenuesFragment()
    }
}