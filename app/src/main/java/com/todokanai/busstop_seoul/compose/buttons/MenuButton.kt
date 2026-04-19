package com.todokanai.busstop_seoul.compose.buttons

import androidx.compose.foundation.Image
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.todokanai.busstop_seoul.R
import com.todokanai.busstop_seoul.compose.presets.MyDropdownMenu

@Composable
fun MenuButton(
    toggleSmallMap: () -> Unit,
    enableRotation: () -> Unit,
    toggleRangeSelectionMode: () -> Unit
){
    var expanded by remember{mutableStateOf(false)}

    FloatingActionButton(
        onClick = {
            expanded = !expanded
        }
    ) {
        Image(
            painter = painterResource(R.drawable.baseline_settings_24),
            contentDescription = null
        )
        MyDropdownMenu(
            contents = listOf(
                Pair(
                    stringResource(R.string.toggle_small_map),
                    {toggleSmallMap()}
                ),
                Pair(
                    stringResource(R.string.enable_rotation),
                    {enableRotation()}
                ),
                Pair(
                    stringResource(R.string.range_selection_mode),
                    {toggleRangeSelectionMode()}
                )
            ),
            expanded = expanded,
            onDismissRequest = {expanded = false}
        )
    }
}