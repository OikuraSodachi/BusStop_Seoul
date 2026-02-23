package com.todokanai.busstop_seoul.compose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.todokanai.busstop_seoul.compose.LineInfoScreen
import com.todokanai.busstop_seoul.compose.MapScreen
import com.todokanai.busstop_seoul.compose.SearchScreen

@Composable
fun BusNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = MapScreen.route,
        modifier = modifier
    ){
        composable(
            route = MapScreen.routeWithArgs,
            arguments = MapScreen.arguments
        ){ backStackEntry ->
            val arsId = backStackEntry.arguments?.getString(MapScreen.arsIdArg)?.toLong()
            val stNm = backStackEntry.arguments?.getString(MapScreen.stNameArg)

            MapScreen(
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
                routeId = it.arguments?.getLong(LineInfoScreen.lineInfoArg) ?: 0L,
            )
        }
    }

}

fun NavHostController.navigateToMapScreen(){
    this.navigate(MapScreen.route)
}

/** SearchScreen 의 StationInfo 조회 상황에 호출
 *
 * Todo: 해당 StationInfo 의 Marker 선택 동작 구현 **/
fun NavHostController.navigateToMapScreen(arsId:Long, stNm:String){
    this.navigate("${MapScreen.route}?${MapScreen.arsIdArg}=$arsId&${MapScreen.stNameArg}=$stNm")
}

fun NavHostController.navigateToLineInfo(routeId:Long){
    this.navigate("${LineInfoScreen.route}/$routeId")
}

fun NavHostController.navigateToSearchScreen(){
    this.navigate(SearchScreen.route)
}