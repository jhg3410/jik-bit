package org.inu.jikbit.presentation.ui.market

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import org.inu.jikbit.domain.model.MarketEntity
import org.inu.jikbit.domain.usecase.GetMarketsUseCase
import org.inu.jikbit.global.base.BaseViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MarketViewModel : BaseViewModel(), KoinComponent {
    private val getMarketsUseCase: GetMarketsUseCase by inject()

    val marketList = MutableLiveData<List<MarketEntity>>()

    private var unfilteredList = listOf<MarketEntity>()

    fun getMarkets(){
        viewModelScope.launch {
            marketList.value = getMarketsUseCase()!!
            viewEvent(NETWORK_END)
            unfilteredList = marketList.value!!
        }
    }


    private fun filter(inputText: String) {
        val filteredList = mutableListOf<MarketEntity>()
        unfilteredList.forEach {
            if (it.korean_name.contains(inputText)) {
                filteredList.add(it)
            }
        }
        marketList.value = filteredList
    }

    fun coinTextChanged(text: CharSequence) {
        filter(text.toString())
    }

    companion object {
        const val NETWORK_END = 1000
    }
}