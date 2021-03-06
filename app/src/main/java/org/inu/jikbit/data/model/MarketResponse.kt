package org.inu.jikbit.data.model

import com.google.gson.annotations.SerializedName

data class MarketResponse(
    @SerializedName("market") val market:String,
    @SerializedName("korean_name") val korean_name:String,
    @SerializedName("english_name") val english_name:String
)