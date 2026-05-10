package com.todokanai.busstop_seoul.compose.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.todokanai.busstop_seoul.dataclass.StationInfo

/** rangeSelectionMode 상태에서 선택된 목록
 *
 * Todo: [rangeSelectionPointList] 타입을 더 단순화 할 수 있을지도? **/
@Composable
fun RangeSelectionPointList(
    rangeSelectionPointList:List<StationInfo>,
    onItemClick:(StationInfo)->Unit,        // Todo: ()-> Unit 형태로 바꾸는게 나을지 고민
    modifier: Modifier = Modifier
){

    LazyColumn(
        modifier = modifier.height(300.dp)
    ) {
        itemsIndexed(items = rangeSelectionPointList) { index, item ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .clickable{
                        onItemClick(item)
                    }
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(),
                    text = item.stNm
                )
            }

        }
    }
}