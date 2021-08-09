package com.damixyz.data.api

import com.damixyz.data.data.FourSquareResponse
import retrofit2.http.GET

interface FourSquareApi {
    @GET("v2/venues/explore/")
    fun getCoffeeVenuesInfo(): FourSquareResponse
}