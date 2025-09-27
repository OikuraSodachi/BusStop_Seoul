package com.todokanai.busstop_seoul.components.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.todokanai.busstop_seoul.databinding.FragmentMapBinding
import com.todokanai.busstop_seoul.util.MapReadyCallback
import com.todokanai.busstop_seoul.viewmodel.MapViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class MapFragment : Fragment() {

    private val binding by lazy { FragmentMapBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<MapViewModel>()
    val mapFragment = SupportMapFragment.newInstance()
    val callback: OnMapReadyCallback = MapReadyCallback(viewLifecycleOwner,viewModel.mapUiState)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .add(binding.map.id, mapFragment)
            .commit()
        mapFragment.getMapAsync(callback)
        return binding.root
    }

}