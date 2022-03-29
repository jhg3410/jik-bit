package org.inu.jikbit.data.api

import org.inu.jikbit.data.model.AccountResponse
import retrofit2.Response
import retrofit2.http.GET

interface AccountHttpService {
    @GET("/v1/accounts")
    suspend fun getAccounts(): Response<List<AccountResponse>>
}