package com.todokanai.busstop_seoul.compose.holder

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.todokanai.busstop_seoul.dataclass.LineSearchResult
import com.todokanai.busstop_seoul.dataclass.abstracts.SearchResult

@Composable
fun SearchResultHolder(
    data: SearchResult,
    modifier: Modifier = Modifier
){
    Row(modifier = modifier) {

        // Todo: result type icon
        Box(
            modifier = Modifier
                .width(50.dp)
        )
        {

        }
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clickable{
                    data.onItemClick()
                }
        ){

        }

        // Todo: 더보기 버튼
        Box(
            modifier = Modifier
                .width(30.dp)
        ){

        }
    }

}

//@Preview
@Composable
private fun SearchResultHolderPreview(){
    Surface {
        SearchResultHolder(
            data = LineSearchResult(
                lineId = 123,
                lineName = "123"
            ),
            modifier = Modifier.height(100.dp)
        )
    }
}