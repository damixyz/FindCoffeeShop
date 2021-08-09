package com.damixyz.data.repositories

import com.damixyz.domain.CoffeeShopInfo

interface FourSquareRepository {
   suspend fun getCoffeeVenuesInfo(): List<CoffeeShopInfo>
}