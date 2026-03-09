package com.todokanai.busstop_seoul.compose

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
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

    if(currentTab == 0){
        SearchTab(
            navController = navController,
            onKeyWordChanged = {viewModel.onKeyWordChanged(it)},
            results = uiState.value.results
        )
    }else{
        FavoritesTab()
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