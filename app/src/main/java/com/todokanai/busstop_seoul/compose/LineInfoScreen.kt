package com.todokanai.busstop_seoul.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.todokanai.busstop_seoul.compose.holder.LineInfoHolder
import com.todokanai.busstop_seoul.dataclass.LineInfo
import kotlinx.coroutines.launch

/** 특정 노선의 현재 위치, 정류소 정보 화면 **/
@Composable
fun LineInfoScreen(
    routeId:Long,
    getLineInfo:LineInfo
){

    val swipeState = rememberPullToRefreshState()
    val isRefreshing = remember{mutableStateOf(false)}
    val scope = rememberCoroutineScope()
    val lineInfos = remember{ mutableStateOf(emptyList<LineInfo>())}

    fun onRefresh(){
        scope.launch {
            isRefreshing.value = true

            isRefreshing.value = false
        }
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        itemsIndexed(lineInfos.value){ index, _ ->
            LineInfoHolder(lineInfos.value[index])
            if(index < lineInfos.value.lastIndex)
                HorizontalDivider()
        }
    }
}