package org.inu.jikbit.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.inu.jikbit.domain.model.AccountEntity
import org.inu.jikbit.domain.repository.AccountRepository

class GetAccountsUseCase(private val repository: AccountRepository) {
    // accounts 가져오는 UseCase
    suspend operator fun invoke(): List<AccountEntity> {
        return withContext(Dispatchers.IO) {
            repository.getAccounts()
        }
    }
}