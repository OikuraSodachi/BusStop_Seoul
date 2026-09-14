package com.todokanai.busstop_seoul.interfaces.compose

import androidx.compose.runtime.Stable

/** interface for [com.todokanai.busstop_seoul.compose.buttons.MenuButton] **/
@Stable
interface MenuButtonInterface {
    fun toggleSmallMap()

    fun enableRotation()

    fun toggleRangeSelectionMode()
}