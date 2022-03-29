package org.inu.jikbit.domain.model

data class AccountEntity(
    val currency:String,
    val balance:String,
    val locked:String,
    val avg_buy_price:String,   // 매수가
    val avg_buy_price_modified: Boolean,
    val unit_currency:String,
    var trade_price:String,     // 현재가
    var yield:String,   // 수익률
    var income: String,     // 평가손익
    var propertyNow: String,    // 평가금액
    var property: String,   // 매수금액
)