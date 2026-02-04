package com.todokanai.busstop_seoul.compose.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Purple,
    secondary = Teal,
    background = Background,
    surface = Background,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black
)

private val DarkColors = darkColorScheme()

@Composable
fun BusStopTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    CompositionLocalProvider(
        LocalContentColor provides
                if(darkTheme) MaterialTheme.colorScheme.background
                else MaterialTheme.colorScheme.primary
    ) {

        MaterialTheme(
            //colorScheme = LightColors,
            typography = MaterialTheme.typography, // 기본값 사용
            shapes = MaterialTheme.shapes,         // 기본값 사용
            content = content
        )
    }
}