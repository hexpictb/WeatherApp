package com.wheatherapptesttask.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun BoxScope.EmptyView() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "No City Selected",
            color = Color(0XFF2C2C2C),
            fontSize = 30.sp,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 75.sp
        )
        Text(
            text = "Please Search For A City",
            color = Color(0XFF2C2C2C),
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 22.5.sp
        )
    }
}
@Preview
@Composable
private fun EmptyViewPreview() {
    Box(modifier = Modifier.background(Color.White)) {
        EmptyView()
    }
}
