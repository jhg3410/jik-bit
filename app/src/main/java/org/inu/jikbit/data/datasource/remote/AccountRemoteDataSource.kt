package org.inu.jikbit.data.datasource.remote

import org.inu.jikbit.data.api.AccountHttpService
import org.inu.jikbit.data.model.AccountResponse

interface AccountRemoteDataSource {
    suspend fun getAccounts(): List<AccountResponse>
}

class AccountRemoteDataSourceImpl(private val api: AccountHttpService) : AccountRemoteDataSource {
    override suspend fun getAccounts(): List<AccountResponse> {
        val result = api.getAccounts()

        try {
            return result.body() ?: listOf(AccountResponse("", "", "", "", false, ""))
        } catch (e: Exception) {
            throw IllegalStateException("ERROR")
        }
    }
}
