package com.rheasan.domain

import com.rheasan.domain.entity.Transaction

interface TransactionRepository {
    suspend fun getAllTransactions(): List<Transaction>
    suspend fun addTransaction(transaction: Transaction)
    suspend fun getBalance(): Double
}