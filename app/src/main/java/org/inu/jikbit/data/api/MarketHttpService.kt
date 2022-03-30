package org.inu.jikbit.data.api

import org.inu.jikbit.data.model.MarketResponse
import retrofit2.Response
import retrofit2.http.GET

interface MarketHttpService {
    @GET("/v1/market/all")
    suspend fun getMarkets(): Response<List<MarketResponse>>
}