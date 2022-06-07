package com.bikcodeh.googlemapsdemo.misc

import com.bikcodeh.googlemapsdemo.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.GroundOverlay
import com.google.android.gms.maps.model.GroundOverlayOptions
import com.google.android.gms.maps.model.LatLng

class Overlays {

    private val losAngeles = LatLng(34.051841600403634, -118.24025417915313)

    fun addGroundOverlay(map: GoogleMap): GroundOverlay? {
        val overlay =map.addGroundOverlay(
            GroundOverlayOptions().apply {
                position(losAngeles, 1000f, 1000f)
                image(BitmapDescriptorFactory.fromResource(R.drawable.marker))
            }
        )
        /** we can set a specific tag to identify witch overlay are we working  */
        // overlay?.tag = "My overlay tag"
        return overlay
    }
}