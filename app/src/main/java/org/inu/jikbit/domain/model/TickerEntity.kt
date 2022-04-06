package org.inu.jikbit.domain.model

data class TickerEntity(
    var market: String,
    var trade_price:String,
    var high_price:String,
    var low_price:String,
)