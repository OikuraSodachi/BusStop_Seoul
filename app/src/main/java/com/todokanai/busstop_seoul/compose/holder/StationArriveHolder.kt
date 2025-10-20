package com.todokanai.busstop_seoul.compose.holder

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.todokanai.busstop_seoul.dataclass.StationArriveInfo

@Composable
fun StationArriveHolder(
    stationArriveInfo: StationArriveInfo
){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = stationArriveInfo.lineNumber)
        Text(text = stationArriveInfo.estTime)
    }

}