package com.bikcodeh.googlemapsdemo.misc

import android.content.Context
import android.util.Log
import android.view.MenuItem
import com.bikcodeh.googlemapsdemo.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.MapStyleOptions
import java.lang.Exception

class TypeAndStyle {

    fun setMapStyle(map: GoogleMap, context: Context) {
        try {
            val success = map.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    context,
                    R.raw.map_style
                )
            )

            if (!success) {
                Log.e("Error aplying style", "Error Map")
            }
        } catch (e: Exception) {
            Log.e("Error aplying style", e.message ?: "Error style map")
        }
    }

    fun setMapType(item: MenuItem, map: GoogleMap) {
        when(item.itemId){
            R.id.normal_map -> {
                map.mapType = GoogleMap.MAP_TYPE_NORMAL
            }
            R.id.hybrid_map -> {
                map.mapType = GoogleMap.MAP_TYPE_HYBRID
            }
            R.id.satellite_map -> {
                map.mapType = GoogleMap.MAP_TYPE_SATELLITE
            }
            R.id.terrain_map -> {
                map.mapType = GoogleMap.MAP_TYPE_TERRAIN
            }
            R.id.none_map -> {
                map.mapType = GoogleMap.MAP_TYPE_NONE
            }
        }
    }
}