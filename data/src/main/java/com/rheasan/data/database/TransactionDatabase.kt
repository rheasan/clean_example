package com.rheasan.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rheasan.data.TransactionEntity

@Database(entities = [TransactionEntity::class], version = 1)
abstract class TransactionDatabase: RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
}
