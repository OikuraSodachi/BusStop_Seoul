package com.todokanai.busstop_seoul.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.todokanai.busstop_seoul.compose.holder.LineInfoHolder
import com.todokanai.busstop_seoul.dataclass.LineInfo
import com.todokanai.busstop_seoul.viewmodel.LineInfoViewModel
import kotlinx.coroutines.launch

/** 특정 노선의 현재 위치, 정류소 정보 화면 **/
@Composable
fun LineInfoScreen(
    routeId:Long,
    getLineInfos:suspend (Long) -> List<LineInfo>,
  //  viewModel: LineInfoViewModel = hiltViewModel()
){

    val scope = rememberCoroutineScope()
    val lineInfos = remember{ mutableStateOf(emptyList<LineInfo>())}

    fun onRefresh(){
        scope.launch {
            lineInfos.value = getLineInfos(routeId)
        }
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        itemsIndexed(lineInfos.value){ index, _ ->
            LineInfoHolder(
                lineInfo = lineInfos.value[index],
                modifier = Modifier.height(150.dp)
            )
            if(index < lineInfos.value.lastIndex)
                HorizontalDivider()
        }
    }

    LaunchedEffect(key1 = routeId){
        onRefresh()
    }
}

//@Preview
@Composable
private fun LineInfoScreenPreview(){
    Surface{
        LineInfoScreen(
            routeId = 123,
            getLineInfos = {
                listOf(
                    LineInfo(
                        stNm = "Name",
                        busInfo = listOf("1", "2", "3", "4", "5", "6")
                    )
                )
            }
        )
    }
}