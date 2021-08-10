package com.damixyz.usecases

import com.damixyz.data.repositories.FourSquareRepository
import com.damixyz.usecases.data.HomeScreen
import com.damixyz.usecases.data.ScreenState
import javax.inject.Inject

class GetCoffeeShopInfoUseCaseImp @Inject constructor(
    private val repository: FourSquareRepository
) : GetCoffeeShopInfoUseCase {
    override suspend fun execute(): ScreenState {
        return try {
            val list = repository.getCoffeeVenuesInfo()
            if (list.isNotEmpty()) {
                HomeScreen.Content(payload = list)
            } else {
                ScreenState.Empty
            }
        } catch (cause: Throwable) {
            ScreenState.Error(errorMessages = cause.message ?: "Unknown Error")
        }
    }
}