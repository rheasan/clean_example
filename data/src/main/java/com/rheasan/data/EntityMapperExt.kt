package com.rheasan.data

import com.rheasan.domain.entity.Transaction

fun TransactionEntity.toDomain() = Transaction(id, description, amount, timestamp)
fun Transaction.toEntity() = TransactionEntity(id, description, amount, timestamp)
