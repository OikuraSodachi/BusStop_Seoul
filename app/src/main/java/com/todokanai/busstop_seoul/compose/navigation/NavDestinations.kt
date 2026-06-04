package com.todokanai.busstop_seoul.compose.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.todokanai.busstop_seoul.Constants

sealed interface NavDestinations{
    val route: String
}

data object MapScreen: NavDestinations{
    override val route: String = Constants.MAP_SCREEN_ROUTE
    const val stIdArg = Constants.STATION_ID_ARG

    val routeWithArgs = "$route?$stIdArg={$stIdArg}"
    val arguments = listOf(
        navArgument(stIdArg){
            type = NavType.StringType
            nullable = true
            defaultValue = null
        }
    )
}

data object SearchScreen: NavDestinations{
    override val route: String = Constants.SEARCH_SCREEN_ROUTE
}

data object LineInfoScreen: NavDestinations{
    override val route: String = Constants.LINE_INFO_SCREEN_ROUTE
    const val lineInfoArg = Constants.LINE_INFO_ARG
    const val targetStationIdArg = Constants.TARGET_STATION_ID_ARG

    val routeWithArgs = "$route/{$lineInfoArg}?${targetStationIdArg}={$targetStationIdArg}"

    val arguments = listOf(
        navArgument(lineInfoArg) {
            type = NavType.LongType
        },
        navArgument(targetStationIdArg) {
            type = NavType.StringType
            nullable = true
            defaultValue = null
        }
    )
}

data object MainScreen: NavDestinations{
    override val route: String = Constants.MAIN_SCREEN_ROUTE
}