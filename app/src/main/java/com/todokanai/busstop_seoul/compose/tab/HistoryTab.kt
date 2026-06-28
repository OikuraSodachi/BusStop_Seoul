package com.todokanai.busstop_seoul.compose.tab

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.todokanai.busstop_seoul.R
import com.todokanai.busstop_seoul.compose.holder.SearchResultHolder
import com.todokanai.busstop_seoul.dataclass.searchresult.abstracts.SearchResult

@Composable
fun HistoryTab(
    navController: NavHostController,
    history: List<SearchResult>,
    deleteFromFavorites:(SearchResult)->Unit,
    saveToFavorites:(SearchResult)->Unit
){
    if(history.isEmpty()){
        Text(
            text = stringResource(R.string.search_result_empty),
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize()
        )
    }else{
        LazyColumn{
            itemsIndexed(history, key = { _, item -> item.id }){ index, item ->
                SearchResultHolder(
                    data = item,
                    onItemClick = {item.onItemClick(navController)},
                    saveToFavorites = { saveToFavorites(item) },
                    deleteFromFavorites = { deleteFromFavorites(item) }
                )
            }
        }
    }

}