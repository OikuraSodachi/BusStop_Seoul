package com.todokanai.busstop_seoul.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.todokanai.busstop_seoul.compose.holder.LineInfoHolder
import com.todokanai.busstop_seoul.compose.navigation.navigateToMapScreen
import com.todokanai.busstop_seoul.viewmodel.LineInfoViewModel

/** 특정 노선의 현재 위치, 정류소 정보 화면 **/
@Composable
fun LineInfoScreen(
    navController: NavHostController,
    routeId:Long,
    targetStationId:Long? = null,                   // Todo: targetStation 으로 스크롤
    viewModel: LineInfoViewModel = hiltViewModel()
){
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()

    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize()
    ) {
        itemsIndexed(uiState.value.lineInfos){ index, lineInfo ->
            LineInfoHolder(
                lineInfo = lineInfo,
                toStationInfo = { navController.navigateToMapScreen(lineInfo.stId) }
            )
            if(index < uiState.value.lineInfos.lastIndex)
                HorizontalDivider()
        }
    }

    LaunchedEffect(key1 = routeId){
        viewModel.setRouteId(routeId)
    }
    LaunchedEffect(key1 = uiState.value.lineInfos){
        if(targetStationId != null && uiState.value.lineInfos.isNotEmpty()){
            val lineInfos = uiState.value.lineInfos
            val indexOfFirst = lineInfos.indexOfFirst { it.stId == targetStationId }
            listState.scrollToItem(indexOfFirst)
        }
    }

}

//@Preview
@Composable
private fun LineInfoScreenPreview(){
    Surface{
        LineInfoScreen(
            navController = NavHostController(LocalContext.current),
            routeId = 123
        )
    }
}