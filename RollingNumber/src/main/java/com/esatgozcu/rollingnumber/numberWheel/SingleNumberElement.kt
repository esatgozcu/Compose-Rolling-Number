package com.esatgozcu.rollingnumber.numberWheel

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun SingleNumberElement(number: Int, size: Size, style: TextStyle){
    Text(
        text = "$number",
        modifier = Modifier.height(size.height.dp),
        style = style
    )
}