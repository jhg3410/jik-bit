package org.inu.jikbit.data.model

data class Account(
    val currency:String,
    val balance:String,
    val locked:String,
    val avg_buy_price:String,
    val avg_buy_price_modified: Boolean,
    val unit_currency:String,
    var trade_price:String
)