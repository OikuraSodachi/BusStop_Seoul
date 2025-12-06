package com.todokanai.busstop_seoul.compose.holder

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.todokanai.busstop_seoul.dataclass.StationArriveInfo

@Composable
fun StationArriveHolder(
    stationArriveInfo: StationArriveInfo
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ){
        Text(
            modifier = Modifier
                .padding(4.dp),
            text = stationArriveInfo.lineNumber
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Cyan)
                .padding(4.dp),
            text = stationArriveInfo.estTime
        )
    }

}

@Preview
@Composable
private fun StationArriveHolderPreview(){
    Surface {
        StationArriveHolder(
            stationArriveInfo = StationArriveInfo(
                lineNumber = "line",
                estTime = "estTime"
            )
        )
    }
}