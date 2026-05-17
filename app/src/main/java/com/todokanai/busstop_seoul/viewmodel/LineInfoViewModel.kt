package com.todokanai.busstop_seoul.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.todokanai.busstop_seoul.dataclass.LineInfo
import com.todokanai.domain.BusUseCase
import com.todokanai.domain.dataclass.BusPositionItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LineInfoViewModel @Inject constructor(
    private val busUseCase: BusUseCase
): ViewModel() {

    private val lineInfos = MutableStateFlow<List<LineInfo>>(emptyList())
    private val busRouteId = MutableStateFlow<Long>(0L)

    val uiState = combine(
        lineInfos,
        busRouteId
    ){ lineInfo, id ->
        LineInfoScreenUiState(
            lineInfos = getLineInfos(id)
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = LineInfoScreenUiState()
    )

    suspend fun getLineInfos(busRouteId:Long): List<LineInfo> {
        val busRouteArriveInfo = busUseCase.getArriveInfoByRouteAll(busRouteId)
        val busPositions = busUseCase.getBusPositions(busRouteId)

        return busRouteArriveInfo.map {
            LineInfo(
                arsId = it.arsId,
                stNm = it.stNm,
                busInfo = busPositionCheck(it.stId,busPositions)
            )
        }
    }

    fun setRouteId(id:Long){
        viewModelScope.launch {
            busRouteId.value = id
        }
    }

    private fun busPositionCheck(stId:Long,busPosition: List<BusPositionItem>):List<String>{
        val result = mutableListOf<String>()
        busPosition.forEach {
            if(it.lastStnId == stId){
                result.add(it.plainNo.toString())
            }
        }
        return result
    }

}

data class LineInfoScreenUiState(
    val lineInfos: List<LineInfo> = emptyList()
)