package com.todokanai.busstop_seoul.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.todokanai.busstop_seoul.dataclass.LineInfo
import com.todokanai.domain.BusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class LineInfoViewModel @Inject constructor(
    private val busUseCase: BusUseCase
): ViewModel() {

    private val lineInfos = MutableStateFlow<List<LineInfo>>(emptyList())
    private val routeId = MutableStateFlow<Long>(0L)

    val uiState = combine(
        lineInfos,
        routeId
    ){ lineInfo, id ->
        LineInfoScreenUiState(
            lineInfos = getLineInfos(id)
        )

    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = LineInfoScreenUiState()
    )

    suspend fun getLineInfos(routeId:Long): List<LineInfo> {

        val response = busUseCase.getArriveInfoByRouteAll(routeId)
        val stList = response.map{
            it.stNm
        }           // 노선이 지나는 정류소 목록

        fun busPositionCheck(stNm:String):List<String>{
            // Todo: response 로부터, 해당 정류소에 위차한 버스 목록 가져오기. REST API 호출 횟수 최적화에 주의.
            return emptyList()
        }
        val result = stList.map{
            LineInfo(
                stNm = it,
                busInfo = busPositionCheck(it)
            )

        }
        return result
    }

    fun setRouteId(id:Long){
        routeId.value = id
    }
}

data class LineInfoScreenUiState(
    val lineInfos: List<LineInfo> = emptyList()
)