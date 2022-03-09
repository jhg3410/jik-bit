package org.inu.jikbit.data.repository

import org.inu.jikbit.data.model.Account

interface AccountRepository {
    fun getAccounts(): List<Account>
}