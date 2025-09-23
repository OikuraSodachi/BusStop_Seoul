package com.todokanai.busstop_seoul.util

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng

class MapReadyCallback: OnMapReadyCallback {

    override fun onMapReady(googleMap: GoogleMap) {
        println("onMapReady")
        val seoul = LatLng(37.554891, 126.970814)

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
        }
    }

}