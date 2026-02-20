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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.todokanai.busstop_seoul.compose.holder.SearchResultHolder
import com.todokanai.busstop_seoul.viewmodel.SearchScreenViewModel

@Composable
fun SearchScreen(
    navController: NavHostController,
    viewModel: SearchScreenViewModel = hiltViewModel()
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
                text = "no result",
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
                        // searchDataCallback = viewModel.searchDataCallback
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
        SearchScreen(
            navController = NavHostController(LocalContext.current)
        )
    }
}