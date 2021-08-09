package com.damixyz.data.mapper

import com.damixyz.data.data.Item
import com.damixyz.data.data.Location
import com.damixyz.data.data.Venue
import com.damixyz.domain.CoffeeShopInfo
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CoffeeVenueMapperTest {
    private lateinit var mapper: Mapper<Item, CoffeeShopInfo>

    @Before
    fun setUp() {
        mapper = CoffeeVenueMapper()
    }

    @Test
    fun `FourSquare response get mapped correctly`() {
        val list = listOf(
            Item(
                venue = Venue(
                    id = "000",
                    location = Location(
                        address = "Costa Coffee House London Bridge",
                        lat = 0.0,
                        lng = 1.1
                    ),
                    name = "Costa Coffee House"
                )
            )
        )

        val actual = mapper.mapList(list)[0]
        val expected = CoffeeShopInfo(
            name = "Costa Coffee House",
            lat = 0.0,
            lng = 1.1,
            address = "Costa Coffee House London Bridge"
        )

        assertEquals(expected, actual)
    }
}