package org.inu.jikbit.data.model

import com.google.gson.annotations.SerializedName

// api 통신으로 받게 되는 응답요소들

data class AccountResponse(
    @SerializedName("currency") val currency:String,
    @SerializedName("balance") val balance:String,
    @SerializedName("locked") val locked:String,
    @SerializedName("avg_buy_price") val avg_buy_price:String,   // 매수가
    @SerializedName("avg_buy_price_modified") val avg_buy_price_modified: Boolean,
    @SerializedName("unit_currency") val unit_currency:String,
)