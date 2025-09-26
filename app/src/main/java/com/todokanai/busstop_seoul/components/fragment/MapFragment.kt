package com.todokanai.busstop_seoul.components.fragment

import android.view.View
import androidx.fragment.app.viewModels
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.todokanai.busstop_seoul.databinding.FragmentMapBinding
import com.todokanai.busstop_seoul.util.MapReadyCallback
import com.todokanai.busstop_seoul.viewmodel.MapViewModel
import com.todokanai.presets.abstracts.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class MapFragment : BaseFragment() {

    private val binding by lazy { FragmentMapBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<MapViewModel>()
    val mapFragment = SupportMapFragment.newInstance()
    val callback: OnMapReadyCallback = MapReadyCallback()

    override fun prepareView(): View {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .add(binding.map.id, mapFragment)
            .commit()
        mapFragment.getMapAsync(callback)
        return binding.root
    }

    override suspend fun collectUIState() {

    }

}