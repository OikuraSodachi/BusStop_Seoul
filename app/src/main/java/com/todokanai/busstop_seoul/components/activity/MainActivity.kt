package com.todokanai.busstop_seoul.components.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.todokanai.busstop_seoul.compose.MainScreen
import com.todokanai.busstop_seoul.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    /** Todo: composable 에 사용할 interface 의 instance 생성 작업을 여기서 할 지 고민해볼 것 **/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val uiState = viewModel.uiState.collectAsStateWithLifecycle()
            MainScreen(
                uiState = uiState.value,
                mainMapCallback = viewModel.mainMapCallback,
                mainScreenInterface = viewModel.mainScreenCallback
            )
        }
    }

}