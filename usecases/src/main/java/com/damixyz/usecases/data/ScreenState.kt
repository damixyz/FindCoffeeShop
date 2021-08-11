package com.damixyz.usecases.data

import com.damixyz.domain.CoffeeShopInfo

sealed class ScreenState {
    data class Loading(val isLoading: Boolean) : ScreenState()
    data class Error(val errorMessages: String) : ScreenState()
    object Empty : ScreenState()
}

sealed class HomeScreen : ScreenState() {
    object LaunchVenues : HomeScreen()
}

sealed class VenuesScreen : ScreenState() {
    data class Content(val payload: List<CoffeeShopInfo>) : VenuesScreen()
}
