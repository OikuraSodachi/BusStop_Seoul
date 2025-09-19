package com.todokanai.busstop_seoul.components.fragments

import android.view.View
import androidx.fragment.app.viewModels
import com.todokanai.busstop_seoul.databinding.FragmentMapBinding
import com.todokanai.busstop_seoul.viewmodel.MapViewModel
import com.todokanai.presets.abstracts.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class MapFragment : BaseFragment() {

    private val binding by lazy { FragmentMapBinding.inflate(layoutInflater) }
    private val viewModel : MapViewModel by viewModels()

    override fun prepareView(): View {
        return binding.apply {
            mapView.apply {

            }
        }.root
    }

    override suspend fun collectUIState() {

    }

}