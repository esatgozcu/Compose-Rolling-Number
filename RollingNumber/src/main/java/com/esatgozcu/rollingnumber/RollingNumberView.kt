package com.esatgozcu.rollingnumber

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp
import com.esatgozcu.rollingnumber.helper.MeasureUnconstrainedViewWidth
import com.esatgozcu.rollingnumber.numberWheel.NumberWheel

@Composable
fun RollingNumberView(vm: RollingNumberVM){
    MeasureUnconstrainedViewWidth(viewToMeasure = {
        //Default text to determine size
        Text(
            text = "0",
            style = vm.textStyle
        )
    }) { width,height ->
        val result = vm.number.toCharArray()
        LazyRow(modifier = Modifier
            .clip(RectangleShape)
            .layout { measurable, constraints ->
                val placeable = measurable.measure(constraints)
                layout(width = placeable.width, height = height.roundToPx()) {
                    placeable.place(0, 0)
                }
            }
            .wrapContentWidth()
        ) {
            item {
                if (vm.prefix.isNotEmpty()){
                    Text(text = vm.prefix, style = vm.textStyle)
                }
            }
            items(items = result.asList()){
                NumberComponent(char = it, Size(width = width.value, height = height.value), vm)
            }
            item {
                if (vm.suffix.isNotEmpty()){
                    Text(text = vm.suffix, style = vm.textStyle)
                }
            }
        }
    }
}

@Composable
fun NumberComponent(char: Char, size: Size, vm: RollingNumberVM) {
    if (char.isDigit()){
        NumberWheel(visibleNumber = char.digitToInt(), size, vm)
    }
    else{
        Text(
            text = char.toString(),
            modifier = Modifier.height(size.height.dp),
            style = vm.textStyle
        )
    }
}


