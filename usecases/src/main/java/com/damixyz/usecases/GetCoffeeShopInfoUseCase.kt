package com.damixyz.usecases

import com.damixyz.usecases.data.ScreenState

interface GetCoffeeShopInfoUseCase {
    suspend fun execute(): ScreenState
}