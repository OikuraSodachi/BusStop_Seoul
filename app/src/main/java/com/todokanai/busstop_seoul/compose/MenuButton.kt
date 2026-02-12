package com.todokanai.busstop_seoul.compose

import androidx.compose.foundation.Image
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import com.todokanai.busstop_seoul.R
import com.todokanai.busstop_seoul.compose.presets.MyDropdownMenu

@Composable
fun MenuButton(
    toggleSmallMap: () -> Unit,
    enableRotation: () -> Unit,
    toSearchScreen: () -> Unit // 테스트 용도 파라미터
){
    val expanded = remember{mutableStateOf(false)}

    FloatingActionButton(
        onClick = {
            expanded.value = !expanded.value
        }
    ) {
        Image(
            painter = painterResource(R.drawable.baseline_settings_24),
            contentDescription = null
        )
        MyDropdownMenu(
            contents = listOf(
                Pair(
                    "toggle small map",
                    {toggleSmallMap()}
                ),
                Pair(
                    "enable rotation",
                    {enableRotation()}
                ),
                Pair(
                    "to SearchScreen",
                {toSearchScreen()}
                )
            ),
            expanded = expanded.value,
            onDismissRequest = {expanded.value = false}
        )
    }
}