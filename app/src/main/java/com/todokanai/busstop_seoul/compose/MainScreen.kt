package com.todokanai.busstop_seoul.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.todokanai.busstop_seoul.R
import com.todokanai.busstop_seoul.compose.buttons.MainMenuButton
import com.todokanai.busstop_seoul.compose.navigation.navigateToSearchScreen
import com.todokanai.busstop_seoul.compose.tab.FavoritesTab
import com.todokanai.busstop_seoul.compose.tab.HistoryTab
import com.todokanai.busstop_seoul.viewmodel.MainScreenViewModel

@Composable
fun MainScreen(
    navController: NavHostController,
    viewModel: MainScreenViewModel = hiltViewModel()
){
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    var currentTab by remember { mutableIntStateOf(0) }

    Column {
        Row(
            modifier = Modifier.height(60.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { navController.navigateToSearchScreen() },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = stringResource(R.string.main_screen_search))
            }

            MainMenuButton(navController = navController)
        }

        Row(modifier = Modifier.height(50.dp)) {
            Text(
                text = stringResource(R.string.main_screen_history),
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize()
                    .weight(1f)
                    .clickable {
                        currentTab = 0
                    }
            )
            Text(
                text = stringResource(R.string.main_screen_favorites),
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize()
                    .weight(1f)
                    .clickable {
                        currentTab = 1
                    }
            )
        }
        if (currentTab == 0) {
            HistoryTab(
                navController = navController,
                history = uiState.value.history,
                saveToFavorites = { viewModel.saveToFavorite(it) },
                deleteFromFavorites = { viewModel.deleteSearchData(it) }
            )
        } else {
            FavoritesTab(
                navController = navController,
                results = uiState.value.favorites,
                saveToFavorites = { viewModel.saveToFavorite(it) },
                deleteFromFavorites = { viewModel.deleteSearchData(it) }
            )
        }
    }

}

//@Preview
@Composable
private fun MainScreenPreview(){
    Surface{
        MainScreen(
            navController = NavHostController(LocalContext.current)
        )
    }
}