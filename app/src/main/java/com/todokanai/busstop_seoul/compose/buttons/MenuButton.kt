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
import com.todokanai.busstop_seoul.interfaces.compose.MenuButtonInterface

@Composable
fun MenuButton(
    menuButtonInterface: MenuButtonInterface,
    isRotationEnabled:Boolean
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
            val rotationText = if(isRotationEnabled){
                stringResource(R.string.disable_rotation)
            }else {
                stringResource(R.string.enable_rotation)
            }

            DropdownMenuItem(
                text = { Text(stringResource(R.string.toggle_small_map))},
                onClick = { menuButtonInterface.toggleSmallMap() }
            )
            DropdownMenuItem(
                text = { Text(rotationText) },
                onClick = { menuButtonInterface.enableRotation() }
            )
            DropdownMenuItem(
                text = { Text(stringResource(R.string.range_selection_mode)) },
                onClick = { menuButtonInterface.toggleRangeSelectionMode() }
            )
        }
    }
}