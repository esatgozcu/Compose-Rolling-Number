package com.esatgozcu.rollingnumber.numberWheel

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun NumberWheel(visibleNumber: Int = 0, size: Size, style: TextStyle){

    fun offset(): Float {
        val offsetMultiplier = 9f - visibleNumber.toFloat()
        val height = size.height
        return -height*offsetMultiplier
    }

    val itemTarget = remember { Animatable(offset()) }
    val numbers = listOf(9,8,7,6,5,4,3,2,1,0)

    LaunchedEffect(visibleNumber){
        itemTarget.animateTo(
            targetValue = offset(),
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioLowBouncy,
                stiffness = Spring.StiffnessLow
            ),
            initialVelocity = 2f
        )
    }

    Column(Modifier.offset(y = itemTarget.value.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        repeat(numbers.count()){
            SingleNumberElement(number = numbers[it], size, style)
        }
    }
}