package com.damixyz.data.repositories

import com.damixyz.data.data.Item
import com.damixyz.data.mapper.Mapper
import com.damixyz.data.source.RemoteDataSource
import com.damixyz.domain.CoffeeShopInfo
import javax.inject.Inject

class FourSquareRepositoryImp @Inject constructor(
    private val dataSource: RemoteDataSource,
    private val mapper: Mapper<Item, CoffeeShopInfo>
) : FourSquareRepository {
    override suspend fun getCoffeeVenuesInfo(latLng: String): List<CoffeeShopInfo> {
        return dataSource.getCoffeeVenuesInfo(latLng).response.groups[0].let {
            mapper.mapList(items = it.items)
        }
    }
}