package com.todokanai.busstop_seoul.compose.holder

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.todokanai.busstop_seoul.dataclass.searchresult.LineSearchResult
import com.todokanai.busstop_seoul.dataclass.searchresult.abstracts.SearchResult

@Composable
fun SearchResultHolder(
    data: SearchResult,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier
            .padding(8.dp)
    ) {

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
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = data.description(),
                modifier = Modifier
                    .padding(vertical = 8.dp)
            )
        }

        // Todo: 더보기 버튼
        Box(
            modifier = Modifier
                .width(30.dp)
        ){

        }
    }

}

@Preview
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