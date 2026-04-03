package com.todokanai.busstop_seoul.compose.holder

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.todokanai.busstop_seoul.dataclass.StationArriveInfo

@Composable
fun StationArriveHolder(
    stationArriveInfo: StationArriveInfo,
    toLineInfoScreen: ()-> Unit,
    modifier:Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable{
                toLineInfoScreen()
            }
    ){
        Text(
            modifier = Modifier
                .padding(4.dp),
            text = stationArriveInfo.rtNm
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            text = stationArriveInfo.arrmsg1.toString()
        )
    }

}