package org.inu.jikbit.data.api

import org.inu.jikbit.data.model.TickerResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TickerHttpService {
    @GET("/v1/ticker")
    fun getTickers(
        @Query("markets") markets: String,
    ): Response<List<TickerResponse>>
}