package org.inu.jikbit.data.repository

import org.inu.jikbit.data.httpservice.AccountHttpService
import org.inu.jikbit.data.model.Account

class AccountRepositoryImpl(
    private val httpService: AccountHttpService
): AccountRepository{

    override fun getAccounts(): List<Account> {
        return httpService.getAccounts().execute().body()!!
    }
}