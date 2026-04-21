package com.todokanai.busstop_seoul.compose.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.todokanai.busstop_seoul.R
import com.todokanai.busstop_seoul.compose.navigation.navigateToMapScreen
import com.todokanai.busstop_seoul.compose.navigation.navigateToSearchScreen

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
        DropdownMenu(
            modifier = Modifier.wrapContentSize(),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text(stringResource(R.string.to_search_screen)) },
                onClick = { navController.navigateToSearchScreen() }
            )
            DropdownMenuItem(
                text = { Text(stringResource(R.string.to_map_screen)) },
                onClick = { navController.navigateToMapScreen() }
            )
        }
    }
}