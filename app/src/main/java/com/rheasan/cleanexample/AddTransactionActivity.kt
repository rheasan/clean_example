package com.rheasan.cleanexample

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rheasan.cleanexample.composables.AppTopBar
import com.rheasan.cleanexample.composables.TransactionDatePicker
import com.rheasan.cleanexample.viewmodel.MainViewModel
import com.rheasan.domain.entity.Transaction
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTransactionActivity: ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()


    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var description by remember { mutableStateOf("") }
            var amount by remember { mutableStateOf("") }
            var date by remember { mutableLongStateOf(0L) }
            Scaffold(
                topBar = {
                    AppTopBar("Add Transaction", ::finish)
                }
            ) { padding ->
                Column(modifier = Modifier.padding(padding).padding(horizontal = 10.dp)) {
                    OutlinedTextField(
                        value = description,
                        onValueChange = {
                            description = it
                        },
                        label = {
                            Text("Description")
                        }
                    )
                    OutlinedTextField(
                        value = amount,
                        onValueChange = {
                            amount = it
                        },
                        label = {
                            Text("Amount")
                        }
                    )
                    TransactionDatePicker {
                        date = it
                    }
                    Row(modifier = Modifier.fillMaxWidth()) {
                        IconButton(
                            modifier = Modifier.weight(1f)
                                .border(2.dp, Color.White),
                            colors = IconButtonDefaults.iconButtonColors(containerColor = Color.Green, contentColor = Color.White),
                            onClick = { addTransaction(description, amount, date, false) }
                        ) {
                            Text(
                                "+",
                                color = Color.White,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.width(5.dp))
                        IconButton(
                            modifier = Modifier.weight(1f)
                                .border(2.dp, Color.White),
                            colors = IconButtonDefaults.iconButtonColors(containerColor = Color.Red, contentColor = Color.White),
                            onClick = { addTransaction(description, amount, date, true) }
                        ) {
                            Text(
                                "-",
                                color = Color.White,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }


    private fun addTransaction(description: String, amount: String, date: Long, expense: Boolean) {
        if(description.isEmpty()) {
            Toast.makeText(this, "Please enter a description", Toast.LENGTH_SHORT).show()
            return
        }
        if(amount.isEmpty() || amount.toDoubleOrNull() == null) {
            Toast.makeText(this, "Please enter an amount", Toast.LENGTH_SHORT).show()
            return
        }
        val amount = if(amount.startsWith("-")) {
            amount.takeLastWhile { it.isDigit() }
        } else {
            amount
        }
        if(date == 0L) {
            Toast.makeText(this, "Please select a date", Toast.LENGTH_SHORT).show()
            return
        }
        viewModel.addTransaction(
            transaction = Transaction(
                description = description,
                amount = if(expense) {
                    -1 * amount.toDouble()
                } else {
                    amount.toDouble()
                },
                timestamp = date
            )
        )
        finish()
    }


    companion object {
        fun start(context: Context){
            val intent = android.content.Intent(context, AddTransactionActivity::class.java)
            context.startActivity(intent)
        }
    }

}