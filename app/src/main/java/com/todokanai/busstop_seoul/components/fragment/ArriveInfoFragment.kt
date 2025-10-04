package com.todokanai.busstop_seoul.components.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.todokanai.busstop_seoul.adapters.ArriveInfoRecyclerViewAdapter
import com.todokanai.busstop_seoul.databinding.FragmentArriveInfoBinding
import com.todokanai.busstop_seoul.viewmodel.ArriveInfoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.getValue

@AndroidEntryPoint
class ArriveInfoFragment : Fragment() {

    private var _binding: FragmentArriveInfoBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<ArriveInfoViewModel>()
    private lateinit var arriveAdapter: ArriveInfoRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArriveInfoBinding.inflate(inflater, container, false)
        arriveAdapter = ArriveInfoRecyclerViewAdapter()
        binding.ArriveInfoRecyclerView.run{
            adapter = arriveAdapter
            layoutManager = LinearLayoutManager(context)
        }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                collectUIState()
            }
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    suspend fun collectUIState() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}