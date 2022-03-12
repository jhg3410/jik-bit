package org.inu.jikbit.data.httpservice

import org.inu.jikbit.data.model.Market
import retrofit2.Call
import retrofit2.http.GET

interface MarketHttpService {
    @GET("/v1/market/all")
    fun getMarkets(): Call<List<Market>>
}