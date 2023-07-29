package com.esatgozcu.rollingnumber

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel

class RollingNumberVM(
    var number: String = "",
    var prefix: String = "",
    var suffix: String = "",
    var textStyle: TextStyle = TextStyle(
        fontSize = 24.sp)
): ViewModel() {

}