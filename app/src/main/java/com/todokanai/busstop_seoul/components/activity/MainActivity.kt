package com.todokanai.busstop_seoul.components.activity

import android.view.View
import androidx.activity.viewModels
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.todokanai.busstop_seoul.R
import com.todokanai.busstop_seoul.databinding.ActivityMainBinding
import com.todokanai.busstop_seoul.util.MapReadyCallback
import com.todokanai.busstop_seoul.viewmodel.MainViewModel
import com.todokanai.presets.abstracts.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel : MainViewModel by viewModels()
    val callback: OnMapReadyCallback = MapReadyCallback()
    val mapFragment = SupportMapFragment.newInstance()

    override fun prepareView(): View {

        supportFragmentManager
            .beginTransaction()
            .add(R.id.map, mapFragment)
            .commit()
        mapFragment.getMapAsync(callback)
        return binding.root
    }

    override suspend fun collectUIState() {

    }

}