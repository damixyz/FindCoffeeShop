package com.damixyz.findcoffee.venues

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.damixyz.domain.CoffeeShopInfo
import com.damixyz.usecases.GetCoffeeShopInfoUseCase
import com.damixyz.usecases.data.ScreenState
import com.damixyz.usecases.data.VenuesScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class VenuesViewModel @Inject constructor(
    private val useCase: GetCoffeeShopInfoUseCase
) : ViewModel() {
    private val _venueInfo = MutableLiveData<List<CoffeeShopInfo>>()
    val venueInfo: LiveData<List<CoffeeShopInfo>>
        get() = _venueInfo

    private val _errorMessage: MutableLiveData<String> = MutableLiveData()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private val _loadingObservable: MutableLiveData<Boolean> = MutableLiveData()
    val loadingObservable: LiveData<Boolean>
        get() = _loadingObservable

    private val _activeError: MutableLiveData<Boolean> = MutableLiveData()
    val activeError: LiveData<Boolean>
        get() = _activeError

    fun getVenues(latLng: String) {
        viewModelScope.launch {
            showLoading()
            val result = useCase.execute(latLng)
            processScreenState(result)
        }
    }

    private fun processScreenState(screenState: ScreenState) {
        when (screenState) {
            ScreenState.Empty -> {
                setActiveError()
                _errorMessage.value = "Sorry your produced no result"
                hideLoading()
            }
            is ScreenState.Error -> {
                setActiveError()
                _errorMessage.value = screenState.errorMessages
                hideLoading()
            }
            is ScreenState.Loading -> {
                if (screenState.isLoading) {
                    unSetActiveError()
                    showLoading()
                } else {
                    hideLoading()
                }
            }
            is VenuesScreen.Content -> {
                _venueInfo.value = screenState.payload
                hideLoading()
            }
        }
    }

    private fun hideLoading() {
        _loadingObservable.value = false
    }

    private fun showLoading() {
        _loadingObservable.value = true
    }

    private fun unSetActiveError() {
        _activeError.value = false
    }

    private fun setActiveError() {
        _activeError.value = true
    }
}