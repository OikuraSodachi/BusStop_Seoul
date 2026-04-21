package com.todokanai.busstop_seoul.compose.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.todokanai.busstop_seoul.R

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

        DropdownMenu(
            modifier = Modifier.wrapContentSize(),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text(stringResource(R.string.toggle_small_map))},
                onClick = { toggleSmallMap() }
            )
            DropdownMenuItem(
                text = { Text(stringResource(R.string.enable_rotation)) },
                onClick = { enableRotation() }
            )
            DropdownMenuItem(
                text = { Text(stringResource(R.string.range_selection_mode)) },
                onClick = { toggleRangeSelectionMode() }
            )
        }
    }
}