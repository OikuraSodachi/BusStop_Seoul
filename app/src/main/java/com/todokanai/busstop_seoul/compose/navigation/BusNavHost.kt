package com.todokanai.busstop_seoul.compose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.todokanai.busstop_seoul.compose.LineInfoScreen
import com.todokanai.busstop_seoul.compose.MainScreen
import com.todokanai.busstop_seoul.compose.SearchScreen

@Composable
fun BusNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = MainScreen.route,
        modifier = modifier
    ){
        composable(
            route = MainScreen.route,
            arguments = MainScreen.arguments
        ){ backStackEntry ->
            val stationArgument = backStackEntry.arguments?.getString(MainScreen.stationInfoArg)
            MainScreen(
                navController = navController,
            )
        }

        composable(route = SearchScreen.route){
            SearchScreen(navController = navController)
        }

        composable(
            route = LineInfoScreen.routeWithArgs,
            arguments = LineInfoScreen.arguments
        ) {
            LineInfoScreen(
                navController = navController,
                routeId = it.arguments?.getString(LineInfoScreen.lineInfoArg)?.toLong() ?: 0L,
            )
        }
    }

}

fun NavHostController.navigateToMainScreen(){
    this.navigate(MainScreen.route)
}

fun NavHostController.navigateToMainScreen(stationId:Long){
    this.navigate("${MainScreen.route}/$stationId")
}

fun NavHostController.navigateToLineInfo(routeId:Long){
    this.navigate("${LineInfoScreen.route}/$routeId")
}

fun NavHostController.navigateToSearchScreen(){
    this.navigate(SearchScreen.route)
}