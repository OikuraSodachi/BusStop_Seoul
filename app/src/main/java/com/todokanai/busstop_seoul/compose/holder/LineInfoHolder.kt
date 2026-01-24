package com.todokanai.busstop_seoul.compose.holder

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.todokanai.busstop_seoul.dataclass.LineInfo
import com.todokanai.busstop_seoul.dataclass.StationInfo

@Composable
fun LineInfoHolder(
    lineInfo: LineInfo,
    modifier: Modifier = Modifier
){
    Row(modifier = modifier){
        Box(modifier = Modifier.weight(1f)) {
            Text(
                text = lineInfo.stationInfo.stNm,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize()
            )

            // Todo: 정류장 표시 UI
        }
        Box(modifier = Modifier.weight(1f)) {
            lineInfo.busInfo?.let {
                Text(
                    text = it,
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize()
                )

                // Todo: 버스 정보 UI
            }
        }
    }
}

//@Preview
@Composable
private fun LineInfoHolderPreview(){
    Surface{
        LineInfoHolder(
            lineInfo = LineInfo(
                stationInfo = StationInfo(
                    stId = "1",
                    stNm = "2",
                    arsId = "3",
                    tmX = "4",
                    tmY = "5"
                ),
                busInfo = "123"
            )

        )

    }
}