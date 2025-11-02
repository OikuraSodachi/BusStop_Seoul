package com.todokanai.busstop_seoul.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.todokanai.busstop_seoul.compose.holder.StationArriveHolder
import com.todokanai.busstop_seoul.dataclass.StationArriveInfo

@Composable
fun StationInfoScreen(
    stationId:Long,
    getArriveInfos: suspend (key:Long) -> List<StationArriveInfo>,
    onClose: () -> Unit,
    modifier:Modifier = Modifier
){
    val arriveInfos =  remember{ mutableStateOf(emptyList<StationArriveInfo>())}
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Close",
            modifier = Modifier
                .fillMaxWidth()
                .clickable{
                    onClose()
                }
        )
        LazyColumn{
            itemsIndexed(arriveInfos.value) { index, _ ->
                StationArriveHolder(arriveInfos.value[index])
                if (index < arriveInfos.value.lastIndex)
                    HorizontalDivider()
            }
        }
    }

    LaunchedEffect(key1 = stationId) {
        arriveInfos.value = getArriveInfos(stationId)
    }

}