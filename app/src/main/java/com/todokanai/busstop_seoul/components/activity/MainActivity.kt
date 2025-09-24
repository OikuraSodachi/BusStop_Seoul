package com.todokanai.busstop_seoul.components.activity

import android.view.View
import androidx.activity.viewModels
import com.todokanai.busstop_seoul.databinding.ActivityMainBinding
import com.todokanai.busstop_seoul.viewmodel.MainViewModel
import com.todokanai.presets.abstracts.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<MainViewModel>()

    override fun prepareView(): View {
        return binding.root
    }

    override suspend fun collectUIState() {

    }

}