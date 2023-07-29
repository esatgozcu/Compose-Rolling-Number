package com.esatgozcu.rollingnumber.numberWheel

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.dp
import com.esatgozcu.rollingnumber.RollingNumberVM

@Composable
fun NumberWheel(visibleNumber: Int = 0, size: Size, vm: RollingNumberVM){

    val itemTarget = remember { Animatable(0f) }
    val numbers = listOf(9,8,7,6,5,4,3,2,1,0)

    fun offset(): Float {
        val offsetMultiplier = 9f - visibleNumber.toFloat()
        val height = size.height
        return -height*offsetMultiplier
    }

    LaunchedEffect(key1 = visibleNumber, block = {
        itemTarget.animateTo(
            targetValue = offset(),
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioLowBouncy,
                stiffness = Spring.StiffnessLow
            ),
            initialVelocity = 2f
        )
    })

    LazyColumn(Modifier.offset(y = itemTarget.value.dp)
    ) {
        items(numbers.count()){
            SingleNumberElement(number = numbers[it],size, vm)
        }
    }
}