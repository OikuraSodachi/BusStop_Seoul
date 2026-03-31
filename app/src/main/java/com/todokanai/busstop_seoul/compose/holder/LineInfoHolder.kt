package com.todokanai.busstop_seoul.compose.holder

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.todokanai.busstop_seoul.dataclass.LineInfo

@Composable
fun LineInfoHolder(
    lineInfo: LineInfo,
    modifier: Modifier = Modifier
){
    Row(modifier = modifier){
        Column(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                .weight(1f)
                .width(4.dp)
                .background(Color.Red)
            )

            Text(
                text = lineInfo.stNm,
                modifier = Modifier
                    .wrapContentSize()
                    .weight(1f),
                textAlign = TextAlign.Center
            )

            Box(
                modifier = Modifier
                .weight(1f)
                .width(4.dp)
                .background(Color.Red)
            )
            // Todo: 정류장 표시 UI
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            lineInfo.busInfo.forEach {
                Text(
                    text = it,
                    modifier = Modifier.padding(2.dp)
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
                stNm = "Name",
                busInfo = listOf("123", "123")
            ),
            modifier = Modifier.height(100.dp)

        )

    }
}