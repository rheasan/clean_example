package com.rheasan.cleanexample.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rheasan.cleanexample.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(title: String, onBackPress: (() -> Unit)? = null) {
    TopAppBar(
        title = { Text(text = title, color = Color.White) },
        navigationIcon = {
            if(onBackPress != null) {
                IconButton(
                    onClick = onBackPress
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_back_white),
                        contentDescription = "Back"
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
    )
}

@Preview
@Composable
fun AppTopBarPreview() {
    AppTopBar("Transactions") {}
}