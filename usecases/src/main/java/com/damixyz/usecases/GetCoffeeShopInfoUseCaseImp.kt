package com.damixyz.usecases

import com.damixyz.data.repositories.FourSquareRepository
import com.damixyz.usecases.data.ScreenState
import com.damixyz.usecases.data.VenuesScreen
import javax.inject.Inject

class GetCoffeeShopInfoUseCaseImp @Inject constructor(
    private val repository: FourSquareRepository
) : GetCoffeeShopInfoUseCase {
    override suspend fun execute(latLng: String): ScreenState {
        return try {
            val list = repository.getCoffeeVenuesInfo(latLng)
            if (list.isNotEmpty()) {
                VenuesScreen.Content(payload = list)
            } else {
                ScreenState.Empty
            }
        } catch (cause: Throwable) {
            ScreenState.Error(errorMessages = cause.message ?: "Unknown Error")
        }
    }
}