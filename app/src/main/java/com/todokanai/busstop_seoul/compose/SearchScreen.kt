package com.todokanai.busstop_seoul.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.todokanai.busstop_seoul.interfaces.compose.SearchScreenInterface

@Composable
fun SearchScreen(
    navController: NavHostController,
    searchScreenInterface: SearchScreenInterface
){

    var text by remember { mutableStateOf("") }

    Column(){
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            TextField(
                value = text,
                onValueChange = {text = it}
            )
        }
        LazyColumn(
            modifier = Modifier
                .weight(1f)
        ) {


        }
    }

}

//@Preview
@Composable
private fun SearchScreenPreview(){
    Surface{
        SearchScreen(
            navController = NavHostController(LocalContext.current),
            searchScreenInterface = object : SearchScreenInterface {

            }
        )
    }
}