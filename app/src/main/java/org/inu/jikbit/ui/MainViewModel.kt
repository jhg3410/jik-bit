package org.inu.jikbit.ui

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import org.inu.jikbit.base.BaseViewModel
import org.inu.jikbit.data.model.Account
import org.inu.jikbit.data.repository.AccountRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainViewModel : BaseViewModel(), KoinComponent {
    private val accountRepository: AccountRepository by inject()

    val accountList = MutableLiveData<List<Account>>()
    private val tmpList = MutableLiveData<List<Account>>()

    fun getAccounts() {
        CoroutineScope(Dispatchers.IO).launch {
            val accountsDeferred = async { accountRepository.getAccounts() }
            with(accountsDeferred.await()) {
                when {
                    this[0].currency.isNotEmpty() -> {
                        accountList.postValue(this)
                        tmpList.postValue(this)
                    }
                    else -> accountList.postValue(tmpList.value)
                }
            }
        }
    }

    companion object {
        const val EVENT_GET_ACCOUNTS = 1000
    }
}