package com.todokanai.busstop_seoul.compose.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.todokanai.busstop_seoul.dataclass.LineInfo
import com.todokanai.busstop_seoul.dataclass.StationInfo

@Composable
fun RangeSearchResultList(
    startGroup: List<StationInfo>,
    endGroup: List<StationInfo>,
    rangeSearchResultList: suspend ()-> List<LineInfo>,
    modifier: Modifier = Modifier
){

    var itemList by remember{ mutableStateOf(emptyList<LineInfo>())}

    LazyColumn(
        modifier = modifier.height(300.dp)
    ){
        itemsIndexed(items = itemList) { index, item ->
            Box(
                modifier = Modifier.height(40.dp)
            ){
                Text(
                    text = item.stNm,
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize()
                )
            }

        }
    }

    LaunchedEffect(key1 = startGroup, key2 = endGroup){
        itemList = rangeSearchResultList()
    }

}