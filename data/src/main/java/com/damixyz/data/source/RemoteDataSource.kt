package com.damixyz.data.source

import com.damixyz.data.api.FourSquareApi
import com.damixyz.data.data.FourSquareResponse

class RemoteDataSource(private val service: FourSquareApi) : DataSource {
    override fun getCoffeeVenuesInfo(): FourSquareResponse {
        return service.getCoffeeVenuesInfo()
    }
}