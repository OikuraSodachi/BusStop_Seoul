package com.todokanai.busstop_seoul.compose.holder

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.todokanai.busstop_seoul.interfaces.compose.SearchDataInterface

@Composable
fun SearchResultHolder(
    searchDataCallback: SearchDataInterface,        // Todo: reference 함수 형태로 넘기기 고려해볼 것
    modifier: Modifier = Modifier
){
    Row(modifier = modifier) {

        // Todo: result type icon
        Box()
        {

        }
        Column(
            modifier = Modifier
                .weight(1f)
                .clickable{
                    searchDataCallback
                }
        ){

        }

        // Todo: 더보기 버튼
        Box(){

        }
    }

}

@Composable
private fun SearchResultHolderPreview(){
    Surface {
        SearchResultHolder(
            searchDataCallback = object : SearchDataInterface {
                override fun onItemClick() {
                    TODO("Not yet implemented")
                }

            }
        )
    }
}