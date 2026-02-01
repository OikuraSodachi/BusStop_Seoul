package com.todokanai.busstop_seoul.components.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.todokanai.busstop_seoul.compose.MainScreen
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
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    MainScreen(
        uiState = uiState.value,
        mainMapCallback = viewModel.mainMapCallback,
        mainScreenInterface = viewModel.mainScreenCallback
    )
}