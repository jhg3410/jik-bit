package org.inu.jikbit.data.api

import org.inu.jikbit.data.model.Account
import retrofit2.Call
import retrofit2.http.GET

interface AccountHttpService {
    @GET("/v1/accounts")
    fun getAccounts(): Call<List<Account>>
}