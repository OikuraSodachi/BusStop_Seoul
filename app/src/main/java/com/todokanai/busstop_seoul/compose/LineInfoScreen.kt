package com.todokanai.busstop_seoul.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.todokanai.busstop_seoul.compose.holder.LineInfoHolder
import com.todokanai.busstop_seoul.viewmodel.LineInfoViewModel

/** 특정 노선의 현재 위치, 정류소 정보 화면 **/
@Composable
fun LineInfoScreen(
    routeId:Long,
    viewModel: LineInfoViewModel = hiltViewModel()
){
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        itemsIndexed(uiState.value.lineInfos){ index, lineInfo ->
            LineInfoHolder(
                lineInfo = lineInfo,
                modifier = Modifier.height(150.dp)
            )
            if(index < uiState.value.lineInfos.lastIndex)
                HorizontalDivider()
        }
    }

    LaunchedEffect(key1 = routeId){
        viewModel.setRouteId(routeId)
    }

}

//@Preview
@Composable
private fun LineInfoScreenPreview(){
    Surface{
        LineInfoScreen(
            routeId = 123
        )
    }
}