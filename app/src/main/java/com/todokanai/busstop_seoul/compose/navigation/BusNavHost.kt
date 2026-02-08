package com.todokanai.busstop_seoul.compose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.todokanai.busstop_seoul.compose.LineInfoScreen
import com.todokanai.busstop_seoul.compose.MainScreen
import com.todokanai.busstop_seoul.compose.SearchScreen
import com.todokanai.busstop_seoul.viewmodel.MainViewModel

@Composable
fun BusNavHost(
    navController: NavHostController,
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = MainScreen.route,
        modifier = modifier
    ){
        composable(route = MainScreen.route){
            val uiState = viewModel.uiState.collectAsStateWithLifecycle()
            MainScreen(
                uiState = uiState.value,
                mainMapCallback = viewModel.mainMapCallback,
                mainScreenInterface = viewModel.mainScreenCallback,
                navController = navController
            )
        }

        // Todo: SearchScreen 에 대한 viewModel 도입 고려하기
        composable(route = SearchScreen.route){
            SearchScreen(
                navController = navController,
                searchScreenInterface = viewModel.searchScreenCallback
            )
        }

        composable(
            route = LineInfoScreen.routeWithArgs,
            arguments = LineInfoScreen.arguments
        ) {
            LineInfoScreen(
                routeId = it.arguments?.getString(LineInfoScreen.lineInfoArg)?.toLong() ?: 0L,
                getLineInfos = {routeId ->
                    viewModel.mainScreenCallback.getLineInfos(routeId)
                }
            )

        }
    }

}

fun NavHostController.navigateToLineInfo(routeId:Long){
    this.navigate("${LineInfoScreen.route}/$routeId")
}