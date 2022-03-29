package org.inu.jikbit.data.repository

import org.inu.jikbit.data.datasource.remote.AccountRemoteDataSource
import org.inu.jikbit.data.mapper.AccountMapper
import org.inu.jikbit.domain.model.AccountEntity
import org.inu.jikbit.domain.repository.AccountRepository

class AccountRepositoryImpl(
    private val dataSource: AccountRemoteDataSource
) : AccountRepository {

    override suspend fun getAccounts(): List<AccountEntity> {
        return AccountMapper.mapperToAccount(dataSource.getAccounts())
    }
}
