package com.todokanai.busstop_seoul.compose

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.todokanai.busstop_seoul.compose.holder.StationArriveHolder
import com.todokanai.busstop_seoul.dataclass.StationArriveInfo

@Composable
fun StationInfoScreen(
    arriveInfos:List<StationArriveInfo>,
    modifier:Modifier = Modifier
){
    LazyColumn(
        modifier = modifier
    ){
        items(arriveInfos.size){ index ->
            StationArriveHolder(arriveInfos[index])
        }

    }

}