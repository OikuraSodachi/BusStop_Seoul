package com.todokanai.busstop_seoul.compose.holder

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.todokanai.busstop_seoul.R
import com.todokanai.busstop_seoul.dataclass.searchresult.LineSearchResult
import com.todokanai.busstop_seoul.dataclass.searchresult.abstracts.ResultType
import com.todokanai.busstop_seoul.dataclass.searchresult.abstracts.SearchResult

@Composable
fun SearchResultHolder(
    data: SearchResult,
    onItemClick:()->Unit,
    saveToFavorites:()->Unit,
    deleteFromFavorites:()->Unit,
    modifier: Modifier = Modifier
){

    var expanded by remember {mutableStateOf(false)}

    Row(
        modifier = modifier
            .height(80.dp)
            .padding(8.dp)
    ) {

        val painter = if(data.type == ResultType.LINE ){
            R.drawable.bus_symbol_icon
        }else{
            R.drawable.location_sign
        }
        Image(
            painter = painterResource(painter),
            contentDescription = null
        )
        Box(modifier = Modifier.width(50.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clickable{ onItemClick() },
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = data.description,
                modifier = Modifier
                    .padding(vertical = 8.dp)
            )
            Text(
                text = data.optionalInfo.toString()
            )
        }

        Box(modifier = Modifier.width(30.dp)){
            Image(
                painter = painterResource(R.drawable.outline_more_vert_24),     // Todo: dark theme 에서도 검은색임
                contentDescription = null,
                modifier = Modifier
                    .clickable{
                        expanded = !expanded
                    }
            )

            DropdownMenu(
                modifier = modifier.wrapContentSize(),
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {

                if(data.isFavorite){
                    DropdownMenuItem(
                        text = { Text(stringResource(R.string.delete_from_favorites))},
                        onClick = { deleteFromFavorites() }
                    )
                }else {
                    DropdownMenuItem(
                        text = { Text(stringResource(R.string.add_to_favorites)) },
                        onClick = { saveToFavorites() }
                    )
                }
            }

        }
    }

}

//@Preview
@Composable
private fun SearchResultHolderPreview(){
    Surface {
        SearchResultHolder(
            data = LineSearchResult(
                busRouteId = 123,
                rtNm = "123",
                term = "123",
                firstBusTm = "123",
                lastBusTm = "123",
                favorite = false
            ),
            onItemClick = {},
            saveToFavorites = {},
            deleteFromFavorites = {},
            modifier = Modifier.height(100.dp)
        )
    }
}