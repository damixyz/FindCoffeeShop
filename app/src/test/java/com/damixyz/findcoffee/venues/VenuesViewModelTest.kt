package com.damixyz.findcoffee.venues

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.damixyz.domain.CoffeeShopInfo
import com.damixyz.findcoffee.TestCoroutineRule
import com.damixyz.usecases.GetCoffeeShopInfoUseCase
import com.damixyz.usecases.data.ScreenState
import com.damixyz.usecases.data.VenuesScreen
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class VenuesViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val getCoffeeShopInfoUseCase: GetCoffeeShopInfoUseCase = mock()
    private lateinit var venuesViewModel: VenuesViewModel

    private val observer: Observer<List<CoffeeShopInfo>> = mock()
    private val observerError: Observer<String> = mock()

    @Before
    fun setUp() {
        venuesViewModel = VenuesViewModel(useCase = getCoffeeShopInfoUseCase)
    }

    @Test
    fun `data is successfully retrieved`() {
        val list = listOf(
            CoffeeShopInfo(
                name = "Costa Coffee",
                lat = 0.0,
                lng = 0.0,
                address = "1 Costa Coffee"
            )
        )
        val screenState = VenuesScreen.Content(list)

        testCoroutineRule.runBlockingTest {
            given(getCoffeeShopInfoUseCase.execute("")).willReturn(screenState)
            venuesViewModel.venueInfo.observeForever(observer)
            venuesViewModel.getVenues("")
            verify(observer).onChanged(screenState.payload)
        }

    }

    @Test
    fun `error message is propagated successfully`() {
        val screenState = ScreenState.Error("Connection Timeout")
        testCoroutineRule.runBlockingTest {
            given(getCoffeeShopInfoUseCase.execute("")).willReturn(screenState)

            venuesViewModel.errorMessage.observeForever(observerError)

            venuesViewModel.getVenues("")

            verify(observerError).onChanged(screenState.errorMessages)
        }


    }
}