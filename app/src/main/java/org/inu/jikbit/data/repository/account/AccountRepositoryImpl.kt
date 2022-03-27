package org.inu.jikbit.data.repository.account

import org.inu.jikbit.data.api.AccountHttpService
import org.inu.jikbit.data.model.Account

class AccountRepositoryImpl(
    private val httpService: AccountHttpService
): AccountRepository {

    override fun getAccounts(): List<Account> {
        return httpService.getAccounts().execute().body()?.reversed()
            ?: listOf(Account("","","","",false,"","","","","",""))
    }
}
