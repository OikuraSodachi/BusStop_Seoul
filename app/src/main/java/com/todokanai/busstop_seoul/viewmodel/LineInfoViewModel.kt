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
        val response = busUseCase.getArriveInfoByRouteAll(busRouteId)
        val stList = response.map{
            it.stNm
        }           // 노선이 지나는 정류소 목록

        val busPositions = busUseCase.getBusPositions(busRouteId)

        fun busPositionCheck(stId:Long,busPosition: List<BusPositionItem>):List<String>{
            val result = mutableListOf<String>()
            busPosition.forEach {
                if(it.lastStnId == stId){
                    result.add(it.plainNo.toString())
                }
            }
            return result
        }
        val result = mutableListOf<LineInfo>()
        response.forEach {
            result.add(
                LineInfo(
                    stNm = it.stNm,
                    busInfo = busPositionCheck(it.stId,busPositions)
                )
            )
        }

        return result
    }

    fun setRouteId(id:Long){
        viewModelScope.launch {
            busRouteId.value = id
        }
    }
}

data class LineInfoScreenUiState(
    val lineInfos: List<LineInfo> = emptyList()
)