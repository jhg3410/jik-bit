package org.inu.jikbit.data.model

import com.google.gson.annotations.SerializedName

data class TickerResponse(
    @SerializedName("market") val market: String,
    @SerializedName("trade_price") val trade_price:String,
)