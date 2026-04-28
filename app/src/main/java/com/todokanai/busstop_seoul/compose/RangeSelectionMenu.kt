package com.todokanai.busstop_seoul.compose

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import com.todokanai.busstop_seoul.R

@Composable
fun RangeSelectionMenu(
    isStartMode:Boolean,
    selectStartRange: () -> Unit,
    selectEndRange: () -> Unit,
    modifier: Modifier = Modifier
){
    Row(modifier = modifier) {
        Button(
            onClick = { selectStartRange() },
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = stringResource(R.string.range_selection_start_mode),
                style = TextStyle(
                    textDecoration = if (isStartMode) {
                        TextDecoration.Underline
                    } else {
                        null
                    }
                )
            )
        }
        Button(
            onClick = { selectEndRange() },
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = stringResource(R.string.range_selection_end_mode),
                style = TextStyle(
                    textDecoration = if (!isStartMode) {
                        TextDecoration.Underline
                    } else {
                        null
                    }
                )
            )
        }
    }

}