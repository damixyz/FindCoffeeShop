package com.damixyz.data.source

import com.damixyz.data.api.FourSquareApi
import com.damixyz.data.api.FourSquareParams
import com.damixyz.data.data.FourSquareResponse
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val service: FourSquareApi,
    private val fourSquareParams: FourSquareParams
) : DataSource {

    override suspend fun getCoffeeVenuesInfo(): FourSquareResponse {
        return service.getCoffeeVenuesInfo(
            v = fourSquareParams.v,
            limit = fourSquareParams.limit,
            ll = "51.290730, 1.047460",
            query = fourSquareParams.query
        )
    }
}