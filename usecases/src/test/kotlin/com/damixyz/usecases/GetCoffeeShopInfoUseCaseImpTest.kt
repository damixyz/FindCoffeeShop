package com.damixyz.usecases

import com.damixyz.data.repositories.FourSquareRepository
import com.damixyz.domain.CoffeeShopInfo
import com.damixyz.usecases.data.ScreenState
import com.damixyz.usecases.data.VenuesScreen
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetCoffeeShopInfoUseCaseImpTest {

    private val latlng = "0.0, 0.0"
    private val fourSquareRepository: FourSquareRepository = mock()
    private lateinit var getCoffeeShopInfoUseCase: GetCoffeeShopInfoUseCase

    @Before
    fun setUp() {
        getCoffeeShopInfoUseCase = GetCoffeeShopInfoUseCaseImp(fourSquareRepository)
    }

    @Test
    fun `attempts to retrieve Coffee Venues list`() {
        runBlockingTest {
            given(fourSquareRepository.getCoffeeVenuesInfo(latlng)).willReturn(mock())
            getCoffeeShopInfoUseCase.execute(latlng)
            verify(fourSquareRepository).getCoffeeVenuesInfo(latlng)
        }

    }

    @Test
    fun `empty screen state is dispatched when list is empty`() {
        val list: List<CoffeeShopInfo> = emptyList()
        runBlockingTest {
            given(fourSquareRepository.getCoffeeVenuesInfo(latlng)).willReturn(list)

            val actual = getCoffeeShopInfoUseCase.execute(latlng)
            assertEquals(ScreenState.Empty, actual)
        }
    }

    @Test
    fun `content screen state is dispatched when retrieve is successfull`() {
        val list = listOf(
            CoffeeShopInfo(
                name = "Costa Coffee",
                lat = 0.0,
                lng = 0.0,
                address = "1 Costa Coffee"
            )
        )
        runBlockingTest {
            given(fourSquareRepository.getCoffeeVenuesInfo(latlng))
                .willReturn(list)

            val actual = getCoffeeShopInfoUseCase.execute(latlng) as VenuesScreen.Content
            assertEquals(list, actual.payload)
        }
    }

    @Test
    fun `error screen state is dispatched when operation fails`() {
        val errorMessage = "Operation timed out"
        runBlockingTest {
            given(fourSquareRepository.getCoffeeVenuesInfo(latlng))
                .willAnswer {
                    throw Throwable(errorMessage)
                }
            val actual = getCoffeeShopInfoUseCase.execute(latlng) as ScreenState.Error

            assertEquals(errorMessage, actual.errorMessages)
        }

    }
}