package com.rheasan.cleanexample.viewmodel

import androidx.lifecycle.ViewModel
import com.rheasan.domain.TransactionRepository
import com.rheasan.domain.entity.Transaction
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository
): ViewModel() {
    private val _allTransactions = MutableStateFlow<List<Transaction>>(listOf())
    private val _totalBalance = MutableStateFlow(0.0)

    val allTransactions = _allTransactions.asStateFlow()
    val totalBalance = _totalBalance.asStateFlow()

    fun getAllTransactions() {
        viewModelScope.launch {
            _allTransactions.value = transactionRepository.getAllTransactions()
            _totalBalance.value = transactionRepository.getBalance()
        }
    }

    fun addTransaction(transaction: Transaction) {
        viewModelScope.launch {
            transactionRepository.addTransaction(transaction)
        }
    }
}