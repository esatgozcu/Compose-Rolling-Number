package com.esatgozcu.rollingnumber

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.esatgozcu.rollingnumber.helper.MeasureUnconstrainedViewWidth
import com.esatgozcu.rollingnumber.numberWheel.NumberWheel

@Composable
fun RollingNumberView(
    number: String = "",
    prefix: String = "",
    suffix: String = "",
    textStyle: TextStyle = TextStyle(fontSize = 24.sp)
){
    MeasureUnconstrainedViewWidth(viewToMeasure = {
        //Default text to determine size
        Text(
            text = "0",
            style = textStyle
        )
    }) { width,height ->
        val result = number.toCharArray()
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
                if (prefix.isNotEmpty()){
                    Text(text = prefix, style = textStyle)
                }
                repeat(result.size){
                    NumberComponent(
                        char = result[it],
                        size = Size(width = width.value, height = height.value),
                        style = textStyle
                    )
                }
                if (suffix.isNotEmpty()){
                    Text(text = suffix, style = textStyle)
                }
            }
        }
    }
}

@Composable
fun NumberComponent(char: Char, size: Size, style: TextStyle) {
    if (char.isDigit()){
        NumberWheel(visibleNumber = char.digitToInt(), size, style)
    }
    else{
        Text(
            text = char.toString(),
            modifier = Modifier.height(size.height.dp),
            style = style
        )
    }
}


