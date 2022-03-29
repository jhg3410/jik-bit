package org.inu.jikbit.domain.model

data class MarketEntity(
    val market:String,
    val korean_name:String,
    val english_name:String,
    var trade_price:String
)