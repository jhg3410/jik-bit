package org.inu.jikbit.domain.model

data class TotalEntity(
    val totalKRW: Double = 0.0,
    var totalProperty: Double = 0.0,
    var totalPurchaseAmount: Double = 0.0,
    var totalEvaluationAmount: Double = 0.0,
    var totalValuationAmount: Double = 0.0,
    var totalYieldAmount: Double = 0.0,
)