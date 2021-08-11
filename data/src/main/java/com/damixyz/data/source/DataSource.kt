package com.damixyz.data.source

import com.damixyz.data.data.FourSquareResponse

interface DataSource {
    suspend fun getCoffeeVenuesInfo(latLng: String): FourSquareResponse
}