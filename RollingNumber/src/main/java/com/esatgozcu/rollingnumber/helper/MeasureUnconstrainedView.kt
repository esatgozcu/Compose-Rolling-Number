package com.esatgozcu.rollingnumber.helper

import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp

@Composable
fun MeasureUnconstrainedViewWidth(
    viewToMeasure: @Composable () -> Unit,
    content: @Composable (measuredWidth: Dp, measuredHeight: Dp) -> Unit,
) {
    SubcomposeLayout { constraints ->
        val measuredWidth = subcompose("viewToMeasureWidth", viewToMeasure)[0]
            .measure(Constraints()).width.toDp()
        val measuredHeight = subcompose("viewToMeasureHeight", viewToMeasure)[0]
            .measure(Constraints()).height.toDp()
        val contentPlaceable = subcompose("content") {
            content(measuredWidth,measuredHeight)
        }[0].measure(constraints)
        layout(contentPlaceable.width, contentPlaceable.height) {
            contentPlaceable.place(0, 0)
        }
    }
}
