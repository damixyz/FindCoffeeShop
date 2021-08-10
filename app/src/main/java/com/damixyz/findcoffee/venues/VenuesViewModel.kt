package com.damixyz.findcoffee.venues

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.damixyz.usecases.GetCoffeeShopInfoUseCase
import com.damixyz.usecases.data.HomeScreen
import com.damixyz.usecases.data.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VenuesViewModel @Inject constructor(
    private val useCase: GetCoffeeShopInfoUseCase
) : ViewModel() {
    private val _venuesScreenState = MutableLiveData<ScreenState?>()
    val venuesScreenState: LiveData<ScreenState?>
        get() = _venuesScreenState

    fun getVenues() {
        viewModelScope.launch {
            val result = useCase.execute()
            _venuesScreenState.value = result
        }
    }
}