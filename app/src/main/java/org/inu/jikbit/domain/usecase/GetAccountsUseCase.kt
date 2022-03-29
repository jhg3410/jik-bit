package org.inu.jikbit.domain.usecase

import org.inu.jikbit.domain.model.AccountEntity
import org.inu.jikbit.domain.repository.AccountRepository

class GetAccountsUseCase(private val repository: AccountRepository) {

    suspend fun invoke(): List<AccountEntity> {
        return repository.getAccounts()
    }
}