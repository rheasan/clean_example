package com.rheasan.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.rheasan.data.TransactionEntity

@Dao
interface TransactionDao {
    @Query("SELECT * FROM TransactionEntity ORDER BY timestamp DESC")
    suspend fun getAll(): List<TransactionEntity>

    @Insert
    suspend fun insert(transaction: TransactionEntity)
}