package com.damixyz.data.api

import com.damixyz.data.data.FourSquareResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FourSquareApi {
    @GET("v2/venues/explore")
    suspend fun getCoffeeVenuesInfo(
        @Query("v") v: Int,
        @Query("limit") limit: Int,
        @Query("ll") ll: String,
        @Query("query") query: String
    ): FourSquareResponse
}

data class FourSquareParams(
    val v: Int,
    val limit: Int,
    val query: String
)