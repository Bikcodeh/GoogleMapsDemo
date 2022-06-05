package com.bikcodeh.googlemapsdemo.misc

import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds

class CameraAndViewport {

    val losAngeles: CameraPosition = CameraPosition.builder()
        .target( LatLng(34.051841600403634, -118.24025417915313))
        .zoom(17f)
        .bearing(0f)
        .tilt(45f)
        .build()

    val melbourneBounds = LatLngBounds(
        LatLng(-38.3652522458361, 144.70407938022473), //South West
        LatLng(-37.50962829899587, 145.366005606716) //North East
    )
}