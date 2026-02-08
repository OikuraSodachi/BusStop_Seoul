package com.todokanai.busstop_seoul.viewmodel

import androidx.lifecycle.ViewModel
import com.todokanai.busstop_seoul.dataclass.LineInfo
import com.todokanai.domain.BusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LineInfoViewModel @Inject constructor(
    private val busUseCase: BusUseCase
): ViewModel() {

}

data class LineInfoScreenUiState(
    val lineInfos: List<LineInfo> = emptyList()
)