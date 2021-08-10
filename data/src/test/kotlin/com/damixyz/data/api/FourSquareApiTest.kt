package com.damixyz.data.api

import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.net.HttpURLConnection

class FourSquareApiTest {
    private lateinit var service: FourSquareApi
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(FourSquareApi::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun `Http method used is GET`() {

        val inputStream = javaClass.classLoader!!
            .getResourceAsStream("four_square.json")
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()

        mockWebServer.enqueue(
            mockResponse
                .setBody(source.readString(Charsets.UTF_8))
                .setResponseCode(HttpURLConnection.HTTP_OK)
        )

        runBlocking {
            service.getCoffeeVenuesInfo(
                v = 20180323,
                limit = 50,
                ll = "51.290730,1.047460",
                query = "coffee"
            )
        }
        val request = mockWebServer.takeRequest()
        val expected = "GET"
        assertEquals(expected, request.method)
    }
}