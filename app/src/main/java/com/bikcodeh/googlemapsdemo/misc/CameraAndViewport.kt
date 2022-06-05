package com.bikcodeh.googlemapsdemo.misc

import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng

class CameraAndViewport {

    val losAngeles: CameraPosition = CameraPosition.builder()
        .target( LatLng(34.051841600403634, -118.24025417915313))
        .zoom(17f)
        .bearing(0f)
        .tilt(45f)
        .build()
}