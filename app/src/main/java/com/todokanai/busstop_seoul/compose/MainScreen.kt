package com.todokanai.busstop_seoul.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.todokanai.busstop_seoul.R
import com.todokanai.busstop_seoul.compose.holder.SearchResultHolder
import com.todokanai.busstop_seoul.viewmodel.MainScreenViewModel

@Composable
fun MainScreen(
    navController: NavHostController,
    viewModel: MainScreenViewModel = hiltViewModel()
){
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    var text by remember { mutableStateOf("") }

    Column(){
        Row(modifier = Modifier.fillMaxWidth()) {
            TextField(
                value = text,
                onValueChange = {
                    text = it
                    viewModel.onKeyWordChanged(it)
                }
            )
        }
        if(uiState.value.results.isEmpty()){
            Text(
                text = stringResource(R.string.search_result_empty),
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize()
            )
        }else {
            LazyColumn(modifier = Modifier.weight(1f)) {
                itemsIndexed(items = uiState.value.results) { index, item ->
                    SearchResultHolder(
                        item,
                        onItemClick = {item.onItemClick(navController)}
                    )
                }

            }
        }
    }

}

//@Preview
@Composable
private fun SearchScreenPreview(){
    Surface{
        MainScreen(
            navController = NavHostController(LocalContext.current)
        )
    }
}