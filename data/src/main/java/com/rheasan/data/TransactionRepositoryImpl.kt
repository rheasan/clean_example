package com.rheasan.data

import com.rheasan.domain.entity.Transaction
import com.rheasan.domain.TransactionRepository

class TransactionRepositoryImpl(
    private val dao: TransactionDao
) : TransactionRepository {
    override suspend fun getAllTransactions() = dao.getAll().map { it.toDomain() }
    override suspend fun addTransaction(transaction: Transaction) = dao.insert(transaction.toEntity())
    override suspend fun getBalance(): Double = dao.getAll().sumOf { it.amount }
}
