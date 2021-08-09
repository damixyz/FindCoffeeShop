package com.damixyz.data.source

import com.damixyz.data.data.FourSquareResponse

interface DataSource {
    fun getCoffeeVenuesInfo(): FourSquareResponse
}