package com.todokanai.busstop_seoul.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.asLiveData
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.todokanai.busstop_seoul.viewmodel.MapUiState
import kotlinx.coroutines.flow.Flow

class MapReadyCallback(private val lifecycleOwner: LifecycleOwner, private val mapState: Flow<MapUiState>): OnMapReadyCallback {

    override fun onMapReady(googleMap: GoogleMap) {
        println("onMapReady")
        val seoul = LatLng(37.554891, 126.970814)

        fun createMarker(pos: LatLng, title: String?, snippet: String?) {
            googleMap.addMarker(
                MarkerOptions()
                    .position(pos)
                    .title(title)
                    .snippet(snippet)
            )
        }

        googleMap.apply {
            moveCamera(CameraUpdateFactory.newLatLngZoom(seoul, 15F)) //카메라 이동

//            viewModel.myLocation.observe(this@MapFragment){
//                moveCamera(CameraUpdateFactory.newLatLngZoom(seoul, 15F)) //카메라 이동
//
//                addMarker {   //사용자 현위치 마커 추가
//                    position(currentPlace)
//                    title("서울")
//                    snippet("대한민국의 수도")
//                }
//            }
            mapState.asLiveData().observe(lifecycleOwner){

            }
        }
    }

}