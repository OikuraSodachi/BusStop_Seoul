package com.todokanai.busstop_seoul.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.todokanai.busstop_seoul.compose.holder.StationArriveHolder
import com.todokanai.busstop_seoul.dataclass.StationArriveInfo

@Composable
fun StationInfoScreen(
    arriveInfos:List<StationArriveInfo>,
    onClose: () -> Unit,
    modifier:Modifier = Modifier
){
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Close",
            modifier = Modifier
                .fillMaxWidth()
                .clickable{
                    onClose()
                }
        )
        LazyColumn{
            itemsIndexed(arriveInfos) { index, _ ->
                StationArriveHolder(arriveInfos[index])
                if (index < arriveInfos.lastIndex)
                    HorizontalDivider()
            }
        }
    }

}