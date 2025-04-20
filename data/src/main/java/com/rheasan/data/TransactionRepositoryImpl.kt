package com.rheasan.data

import com.rheasan.data.database.TransactionDao
import com.rheasan.data.utils.toDomain
import com.rheasan.data.utils.toEntity
import com.rheasan.domain.entity.Transaction
import com.rheasan.domain.TransactionRepository
import kotlinx.coroutines.flow.map

class TransactionRepositoryImpl(
    private val dao: TransactionDao
) : TransactionRepository {
    override suspend fun getAllTransactions() = dao.getAll().map { it.toDomain() }
    override suspend fun addTransaction(transaction: Transaction) = dao.insert(transaction.toEntity())
    override suspend fun getBalance(): Double = dao.getAll().sumOf { it.amount }
}
