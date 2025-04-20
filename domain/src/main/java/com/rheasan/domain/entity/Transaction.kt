package com.rheasan.domain.entity

data class Transaction(
    val id: Int,
    val description: String,
    val amount: Double,
    val timestamp: Long
)