package com.todokanai.busstop_seoul.components.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.todokanai.busstop_seoul.compose.navigation.BusNavHost
import com.todokanai.busstop_seoul.compose.ui.BusStopTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusStopTheme {
                BusStopApp()
            }
        }
    }

}

@Composable
fun BusStopApp(){
    val navController = rememberNavController()

    BusNavHost(navController = navController)
}