package com.todokanai.busstop_seoul.components.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.gms.maps.SupportMapFragment
import com.todokanai.busstop_seoul.databinding.FragmentMapBinding
import com.todokanai.busstop_seoul.util.MapReadyCallback
import com.todokanai.busstop_seoul.viewmodel.MapViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class MapFragment : Fragment() {

    private var _binding:FragmentMapBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<MapViewModel>()
    private lateinit var mapFragment : SupportMapFragment
    private lateinit var callback : MapReadyCallback

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        mapFragment = SupportMapFragment.newInstance()
        callback = MapReadyCallback(viewLifecycleOwner,viewModel.mapUiState)
        requireActivity().supportFragmentManager
            .beginTransaction()
            .add(binding.map.id, mapFragment)
            .commit()
        mapFragment.getMapAsync(callback)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}