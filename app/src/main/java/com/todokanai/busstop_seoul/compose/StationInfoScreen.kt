package com.todokanai.busstop_seoul.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.todokanai.busstop_seoul.compose.holder.StationArriveHolder
import com.todokanai.busstop_seoul.dataclass.StationArriveInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StationInfoScreen(
    stationId:Long,
    getArriveInfos: suspend (key:Long) -> List<StationArriveInfo>,
    onClose: () -> Unit,
    modifier:Modifier = Modifier
){
    val swipeState = rememberPullToRefreshState()
    val arriveInfos =  remember{ mutableStateOf(emptyList<StationArriveInfo>())}
    Column(
        modifier = modifier
    ) {
        Row {
            Text(
                text = "Close",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onClose()
                    }
            )
        }

        LazyColumn(
            modifier = Modifier.nestedScroll(swipeState.nestedScrollConnection)
        ) {
            itemsIndexed(arriveInfos.value) { index, _ ->
                StationArriveHolder(arriveInfos.value[index])
                if (index < arriveInfos.value.lastIndex)
                    HorizontalDivider()
            }
        }
    }

    if(swipeState.isRefreshing){
        LaunchedEffect(true) {
            arriveInfos.value = emptyList()
            arriveInfos.value = getArriveInfos(stationId)
            swipeState.endRefresh()
        }       // Todo: Refresh 진행중임을 나타내는 UI 추가하기
    }
    LaunchedEffect(key1 = stationId) {
        arriveInfos.value = getArriveInfos(stationId)
    }

}