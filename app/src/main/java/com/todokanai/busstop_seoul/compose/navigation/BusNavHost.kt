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
            route = MainScreen.routeWithArgs,
            arguments = MainScreen.arguments
        ){ backStackEntry ->
            val arsId = backStackEntry.arguments?.getString(MainScreen.arsIdArg)?.toLong()
            val stNm = backStackEntry.arguments?.getString(MainScreen.stNameArg)

            MainScreen(
                navController = navController,
                arsId = arsId,
                stNm = stNm
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

/** SearchScreen 의 StationInfo 조회 상황에 호출
 *
 * Todo: 해당 StationInfo 의 Marker 선택 동작 구현 **/
fun NavHostController.navigateToMainScreen(arsId:Long, stNm:String){
    this.navigate("${MainScreen.route}?${MainScreen.arsIdArg}=$arsId&${MainScreen.stNameArg}=$stNm")
}

fun NavHostController.navigateToLineInfo(routeId:Long){
    this.navigate("${LineInfoScreen.route}/$routeId")
}

fun NavHostController.navigateToSearchScreen(){
    this.navigate(SearchScreen.route)
}