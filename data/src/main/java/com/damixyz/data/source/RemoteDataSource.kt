package com.damixyz.data.source

import com.damixyz.data.api.FourSquareApi
import com.damixyz.data.data.FourSquareResponse
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val service: FourSquareApi) : DataSource {
    override suspend fun getCoffeeVenuesInfo(): FourSquareResponse {
        return service.getCoffeeVenuesInfo(
            v = 20180323,
            limit = 50,
            ll = "51.290730, 1.047460",
            query = "coffee"
        )
    }
}