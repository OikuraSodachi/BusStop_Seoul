package com.todokanai.busstop_seoul.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.todokanai.busstop_seoul.R
import com.todokanai.busstop_seoul.RangeSelectionColors

@Composable
fun RangeSelectionMenu(
    isStartMode:Boolean,
    onToggleGroupView: ()->Unit,
    onToggleSearchResult: ()->Unit,
    selectStartRange: () -> Unit,
    selectEndRange: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row {
            Button(
                onClick = selectStartRange,
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    tint = RangeSelectionColors.startColor
                )
                Spacer(modifier = Modifier.width(4.dp))
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
                onClick = selectEndRange,
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    tint = RangeSelectionColors.endColor
                )
                Spacer(modifier = Modifier.width(4.dp))
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
        Row {
            Button(
                onClick = onToggleGroupView,
                modifier = Modifier.weight(1f)
            ) {
                val text = if (isStartMode) {
                    stringResource(R.string.show_range_selection_start_list)
                } else {
                    stringResource(R.string.show_range_selection_end_list)
                }
                Text(text = text)
            }

            Button(
                onClick = onToggleSearchResult,
                modifier = Modifier.weight(1f)
            ) {
                val text = stringResource(R.string.show_range_search_result)

                Text(text = text)
            }
        }
    }

}