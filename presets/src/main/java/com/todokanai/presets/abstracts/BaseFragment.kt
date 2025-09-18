package com.todokanai.presets.abstracts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch

/** view 의 lifeCycle 대신, 기능 ( 작업 ) 단위로 구분하기 위한 class **/
abstract class BaseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = prepareView()
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                collectUIState()
            }
        }
        return rootView
    }

    /** @return root view **/
    abstract fun prepareView(): View

    /** update view **/
    abstract suspend fun collectUIState()

}