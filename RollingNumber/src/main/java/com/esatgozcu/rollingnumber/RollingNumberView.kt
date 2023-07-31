package com.esatgozcu.rollingnumber

import androidx.compose.foundation.layout.*
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
        Column {
            //Avoid to use lazy row or column because we use repeat to add child view.
            //Otherwise you need to face this problem:
            //Error: Vertically scrollable component was measured with an infinity maximum height constraints, which is disallowed.
            //We want to use custom view inside column or row. If column or row has verticalScrollState, we will receive error.
            Row(modifier = Modifier
                .clip(RectangleShape)
                .layout { measurable, constraints ->
                    val placeable = measurable.measure(constraints)
                    layout(width = placeable.width, height = height.roundToPx()) {
                        placeable.place(0, 0)
                    }
                }
                .wrapContentWidth()
            ) {
                if (vm.prefix.isNotEmpty()){
                    Text(text = vm.prefix, style = vm.textStyle)
                }
                repeat(result.asList().size){
                    NumberComponent(char = result.asList()[it], Size(width = width.value, height = height.value), vm)
                }
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


