package com.esatgozcu.rollingnumber.numberWheel

import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.dp
import com.esatgozcu.rollingnumber.RollingNumberVM

@Composable
fun SingleNumberElement(number: Int, size: Size, vm: RollingNumberVM){
    Text(
        text = "$number",
        modifier = Modifier.size(size.width.dp,size.height.dp),
        style = vm.textStyle
    )
}