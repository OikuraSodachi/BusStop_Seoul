package com.todokanai.busstop_seoul.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.todokanai.busstop_seoul.compose.holder.StationArriveHolder
import com.todokanai.busstop_seoul.dataclass.StationArriveInfo
import com.todokanai.busstop_seoul.dataclass.StationInfo
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StationInfoScreen(
    targetStation: StationInfo,
    getArriveInfos: suspend (key:Long) -> List<StationArriveInfo>,
    onClose: () -> Unit,
    toLineInfoScreen: (routeId:Long) -> Unit,
    modifier:Modifier = Modifier
){
    val swipeState = rememberPullToRefreshState()
    val isRefreshing = remember{mutableStateOf(false)}
    val scope = rememberCoroutineScope()
    val arriveInfos =  remember{ mutableStateOf(emptyList<StationArriveInfo>())}

    fun onRefresh(){
        scope.launch {
            isRefreshing.value = true
            arriveInfos.value = emptyList()
            arriveInfos.value = getArriveInfos(targetStation.arsId.toLong())
            isRefreshing.value = false
        }
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Close",
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClose()
                }
        )
        Text(text = targetStation.stNm)

        PullToRefreshBox(
            isRefreshing = isRefreshing.value,
            onRefresh = { onRefresh() },
            state = swipeState
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                itemsIndexed(arriveInfos.value) { index, _ ->
                    StationArriveHolder(
                        stationArriveInfo = arriveInfos.value[index],
                        modifier = Modifier
                            .clickable{
                                toLineInfoScreen(arriveInfos.value[index].busRouteId.toLong())
                            }
                    )
                    if (index < arriveInfos.value.lastIndex)
                        HorizontalDivider()
                }
            }
        }
    }

    LaunchedEffect(key1 = targetStation.arsId) {
        onRefresh()
    }

}