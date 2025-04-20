package com.rheasan.cleanexample.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rheasan.domain.entity.Transaction
import java.util.Calendar
import java.util.Date

@Composable
fun Transactions(transactions: List<Transaction>) {
    LazyColumn {
        items(transactions) { transaction ->
            SingleTransaction(transaction)
            Spacer(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color.Black))
        }
    }
}

@Composable
private fun SingleTransaction(transaction: Transaction) {
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 4.dp)) {
        Text(
            transaction.description,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text("Amount = ${transaction.amount}")
        Text("Date: ${Date(transaction.timestamp)}")
    }
}