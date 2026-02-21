package com.todokanai.busstop_seoul.compose.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed interface NavDestinations{
    val route: String
}

data object MainScreen: NavDestinations{
    override val route: String = "MainScreen"
    const val arsIdArg = "arsIdArg"
    const val stNameArg = "stNameArg"

    val routeWithArgs = "$route?$arsIdArg={$arsIdArg}&$stNameArg={$stNameArg}"
    val arguments = listOf(
        navArgument(arsIdArg) {
            type = NavType.StringType
            nullable = true
            defaultValue = null
        },
        navArgument(stNameArg){
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
        navArgument(lineInfoArg) { type = NavType.StringType }
    )
}