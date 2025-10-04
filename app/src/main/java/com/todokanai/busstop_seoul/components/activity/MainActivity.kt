package com.todokanai.busstop_seoul.components.activity

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.todokanai.busstop_seoul.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()


}