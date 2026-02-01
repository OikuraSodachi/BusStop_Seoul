package com.todokanai.busstop_seoul.components.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.todokanai.busstop_seoul.compose.MainScreen
import com.todokanai.busstop_seoul.compose.navigation.BusNavHost
import com.todokanai.busstop_seoul.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusStopApp(viewModel)
        }
    }

}

@Composable
fun BusStopApp(viewModel: MainViewModel){
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination

    //val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    BusNavHost(
        navController = navController,
        viewModel = viewModel
    )
}