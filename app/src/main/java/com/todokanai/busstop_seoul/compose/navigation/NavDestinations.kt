package com.todokanai.busstop_seoul.compose.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed interface NavDestinations{
    val route: String
}

data object MapScreen: NavDestinations{
    override val route: String = "MapScreen"
    const val stIdArg = "stIdArg"

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
    override val route: String = "SearchScreen"
}

data object LineInfoScreen: NavDestinations{
    override val route: String = "LineInfoScreen"
    const val lineInfoArg = "lineInfoArg"
    val routeWithArgs = "$route/{$lineInfoArg}"
    val arguments = listOf(
        navArgument(lineInfoArg) { type = NavType.LongType }
    )
}

data object MainScreen: NavDestinations{
    override val route: String = "MainScreen"
}