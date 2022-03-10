package org.inu.jikbit.ui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.inu.jikbit.base.BaseViewModel
import org.inu.jikbit.data.repository.AccountRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainViewModel : BaseViewModel(), KoinComponent {
    private val accountRepository : AccountRepository by inject()

    fun btnClick() = viewEvent(EVENT_GET_ACCOUNTS)

    fun getAccounts() {
        CoroutineScope(Dispatchers.IO).launch{
            accountRepository.getAccounts()
        }

    }

    companion object {
        const val EVENT_GET_ACCOUNTS = 1000
    }
}