package com.rheasan.cleanexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rheasan.cleanexample.composables.AppTopBar
import com.rheasan.cleanexample.composables.Transactions
import com.rheasan.cleanexample.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val allTransactions by viewModel.allTransactions.collectAsState()
            val totalBalance by viewModel.totalBalance.collectAsState()

            Scaffold(
                topBar = {
                    AppTopBar("Transactions")
                },
                bottomBar = {
                    Button(
                        onClick = {
                            AddTransactionActivity.start(this)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = Color.White),
                        modifier = Modifier.padding(4.dp).padding(top = 6.dp).fillMaxWidth()
                    ) {
                        Text("Add Transaction", color = Color.White)
                    }
                }
            ) { padding ->
                Column(modifier = Modifier.fillMaxSize().padding(padding)) {
                    Row(modifier = Modifier.fillMaxWidth().padding(10.dp), horizontalArrangement = Arrangement.End) {
                        Text("Total Balance: $totalBalance", fontSize = 16.sp)
                    }
                    Spacer(modifier = Modifier.height(2.dp).fillMaxWidth().background(Color.Black))
                    Transactions(transactions = allTransactions)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllTransactions()
    }
}