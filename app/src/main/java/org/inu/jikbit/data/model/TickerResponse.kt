package org.inu.jikbit.data.model

import com.google.gson.annotations.SerializedName

data class TickerResponse(
    @SerializedName("market") val market: String,
    @SerializedName("trade_price") val trade_price:String,
    @SerializedName("high_price") val high_price:String,
    @SerializedName("low_price") val low_price:String,
)