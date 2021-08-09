package com.damixyz.data.mapper

import com.damixyz.data.data.FourSquareResponse
import com.damixyz.data.data.Item
import com.damixyz.data.data.Venue
import com.damixyz.domain.CoffeeShopInfo

class CoffeeVenueMapper : Mapper<Item, CoffeeShopInfo> {
    override fun map(item: Item): CoffeeShopInfo {
        return with(item) {
            CoffeeShopInfo(
                name = venue.name,
                lat = venue.location.lat,
                lng = venue.location.lng,
                address = venue.location.address
            )
        }
    }

}