package com.todokanai.busstop_seoul.compose.buttons

import androidx.compose.foundation.Image
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.todokanai.busstop_seoul.R

@Composable
fun MainMenuButton(
){
    Button(
        onClick = { TODO() }
    ) {
        Image(
            painter = painterResource(R.drawable.outline_menu_24),
            contentDescription = null
        )
    }
}