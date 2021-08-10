package com.damixyz.usecases.data

import com.damixyz.domain.CoffeeShopInfo

sealed class ScreenState {
    data class Error(val errorMessages: String) : ScreenState()
    object Empty : ScreenState()
}

sealed class HomeScreen : ScreenState() {
    data class Content(val payload: List<CoffeeShopInfo>) : HomeScreen()
}