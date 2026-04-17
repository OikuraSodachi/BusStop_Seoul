package com.todokanai.busstop_seoul.compose.buttons

import androidx.compose.foundation.Image
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.todokanai.busstop_seoul.R
import com.todokanai.busstop_seoul.compose.navigation.navigateToMapScreen
import com.todokanai.busstop_seoul.compose.navigation.navigateToSearchScreen
import com.todokanai.busstop_seoul.compose.presets.MyDropdownMenu

@Composable
fun MainMenuButton(
    navController: NavHostController
){

    var expanded by remember{mutableStateOf(false)}

    Button(
        onClick = { expanded = !expanded  }
    ) {
        Image(
            painter = painterResource(R.drawable.outline_menu_24),
            contentDescription = null
        )
        MyDropdownMenu(
            contents = listOf(
                Pair(
                    stringResource(R.string.to_search_screen),
                    {navController.navigateToSearchScreen()}
                ),
                Pair(
                    stringResource(R.string.to_map_screen),
                    {navController.navigateToMapScreen()}
                )
            ),
            expanded = expanded,
            onDismissRequest = {expanded = false}
        )
    }
}