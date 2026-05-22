package com.todokanai.busstop_seoul.interfaces.compose

import com.todokanai.busstop_seoul.dataclass.StationInfo

sealed interface MapScreenMode {

    /** Todo: [MapScreenMode] 외부에서 보이면 안됨 **/
    enum class SelectionType{ START, END }

    // 일반 모드: 특정 정류소를 선택할 수 있음
    data class Normal(val targetStation: StationInfo? = null) :
        MapScreenMode

    // 구간 선택 모드: 시작/종료 그룹을 관리함
    data class RangeSelection(
        val type: SelectionType = SelectionType.START,
        val startGroup: List<StationInfo> = emptyList(),
        val endGroup: List<StationInfo> = emptyList(),
        val isGroupViewEnabled: Boolean = false,    // 선택된 목록 창 활성화 여부
        val isSearchResultEnabled: Boolean = false
    ) : MapScreenMode {

        fun isStartMode():Boolean{
            return type == SelectionType.START
        }

        fun toStartMode():RangeSelection{
            return copy(type = SelectionType.START)
        }

        fun toEndMode():RangeSelection{
            return copy(type = SelectionType.END)
        }

        /** Todo: [RangeSelection] 을 반환하는 구조가 바람직한 구조인지?
         *  @param stationInfo 추가/제거할 정류소
         * @return 수정된 목록 **/
        fun updateGroupItems(stationInfo: StationInfo):RangeSelection{
            return when(type){
                SelectionType.START -> {
                    this.copy(startGroup = rangeSelector(stationInfo, startGroup))
                }
                SelectionType.END -> {
                    this.copy(endGroup = rangeSelector(stationInfo, endGroup))
                }
            }
        }

        /** startGroup, endGroup 에 targetStation 추가/제거
         *
         * @param targetStation 추가/제거할 정류소
         * @param group startGroup, endGroup
         * @return 수정된 목록 **/
        private fun rangeSelector(targetStation:StationInfo, group:List<StationInfo>):List<StationInfo>{
            return if(group.contains(targetStation)){
                group.filter { it != targetStation }
            }else{
                group + targetStation
            }
        }
    }
}