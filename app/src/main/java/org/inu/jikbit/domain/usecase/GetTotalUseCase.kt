package org.inu.jikbit.domain.usecase

import org.inu.jikbit.domain.model.AccountEntity
import org.inu.jikbit.domain.model.TotalEntity

class GetTotalUseCase {
    operator fun invoke(coinList:List<AccountEntity>):TotalEntity{
        val result  = TotalEntity()

        for (i in coinList.indices){
            result.run {
                this.totalProperty += coinList[i].propertyNow.toDouble()
                this.totalPurchaseAmount += coinList[i].property.toDouble()
                this.totalEvaluationAmount += coinList[i].propertyNow.toDouble()
                this.totalYieldAmount += coinList[i].yield.toDouble()
                this.totalValuationAmount += coinList[i].income.toDouble()
            }
        }
        result.totalYieldAmount /= coinList.size
        return result
    }
}