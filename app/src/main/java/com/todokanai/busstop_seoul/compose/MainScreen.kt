package com.todokanai.busstop_seoul.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.todokanai.busstop_seoul.compose.tab.FavoritesTab
import com.todokanai.busstop_seoul.compose.tab.SearchTab
import com.todokanai.busstop_seoul.viewmodel.MainScreenViewModel

@Composable
fun MainScreen(
    navController: NavHostController,
    viewModel: MainScreenViewModel = hiltViewModel()
){
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    var currentTab by remember { mutableStateOf(0)}
    Column{
        Row(modifier = Modifier.height(50.dp)){
            Text(
                text = "Search",
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize()
                    .weight(1f)
                    .clickable{
                        currentTab = 0
                    }
            )
            Text(
                text = "Favorites",
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize()
                    .weight(1f)
                    .clickable{
                        currentTab = 1
                    }
            )
        }
        if(currentTab == 0){
            SearchTab(
                navController = navController,
                onKeyWordChanged = {viewModel.onKeyWordChanged(it)},
                results = uiState.value.results,
                saveToFavorites = {viewModel.saveToFavorite(it)},
                deleteFromFavorites = {viewModel.deleteSearchData(it)}
            )
        }else{
            FavoritesTab(
                navController = navController,
                results = uiState.value.favorites,
                saveToFavorites = {viewModel.saveToFavorite(it)},
                deleteFromFavorites = {viewModel.deleteSearchData(it)}
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