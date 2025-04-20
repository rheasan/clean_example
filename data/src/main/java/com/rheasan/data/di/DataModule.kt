package com.rheasan.data.di

import android.content.Context
import androidx.room.Room
import com.rheasan.data.database.TransactionDao
import com.rheasan.data.TransactionRepositoryImpl
import com.rheasan.data.database.TransactionDatabase
import com.rheasan.domain.TransactionRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideTransactionDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, TransactionDatabase::class.java, "transaction_db").build()

    @Provides
    fun provideTransactionDao(db: TransactionDatabase) = db.transactionDao()


    @Provides
    fun bindsTransactionRepository(dao: TransactionDao): TransactionRepository =
        TransactionRepositoryImpl(dao)
}