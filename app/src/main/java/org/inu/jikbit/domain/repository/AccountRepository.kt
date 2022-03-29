package org.inu.jikbit.domain.repository

import org.inu.jikbit.domain.model.AccountEntity

interface AccountRepository {
    suspend fun getAccounts(): List<AccountEntity>
}