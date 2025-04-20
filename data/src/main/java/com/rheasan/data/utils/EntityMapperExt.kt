package com.rheasan.data.utils

import com.rheasan.data.TransactionEntity
import com.rheasan.domain.entity.Transaction

fun TransactionEntity.toDomain() = Transaction(id, description, amount, timestamp)
fun Transaction.toEntity() = TransactionEntity(id, description, amount, timestamp)
