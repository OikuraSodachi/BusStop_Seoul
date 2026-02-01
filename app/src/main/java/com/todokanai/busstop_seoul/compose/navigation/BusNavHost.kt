package com.todokanai.busstop_seoul.compose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

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
        composable(route = MainScreen.route){

        }

        composable(
            route = LineInfoScreen.route,
            arguments = LineInfoScreen.arguments
        ) {

        }
    }

}

fun NavHostController.navigateToLineInfo(routeId:Long){
    this.navigate("${LineInfoScreen.route}/$routeId")
}