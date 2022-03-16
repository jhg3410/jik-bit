package org.inu.jikbit.data.model

data class Account(
    val currency:String,
    val balance:String,
    val locked:String,
    val avg_buy_price:String,   // 매수가
    val avg_buy_price_modified: Boolean,
    val unit_currency:String,
    var trade_price:String,     // 현재가
    val yield:String,   // 수익률
    val income: String,     // 평가손익
    val propertyNow: String,    // 평가금액
    val property: String,   // 매수금액
)