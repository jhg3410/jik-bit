package org.inu.jikbit.data.httpservice

import org.inu.jikbit.data.model.Ticker
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TickerHttpService {
    @GET("/v1/ticker")
    fun getTickers(
        @Query("markets") markets: String,
    ): Call<List<Ticker>>
}