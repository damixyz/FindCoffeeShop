package com.damixyz.data.repositories

import com.damixyz.domain.CoffeeShopInfo

interface FourSquareRepository {
   suspend fun getCoffeeVenuesInfo(latLng: String): List<CoffeeShopInfo>
}