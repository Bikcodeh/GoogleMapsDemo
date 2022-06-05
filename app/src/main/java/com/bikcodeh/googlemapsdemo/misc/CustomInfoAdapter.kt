package com.bikcodeh.googlemapsdemo.misc

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.TextView
import com.bikcodeh.googlemapsdemo.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

class CustomInfoAdapter(context: Context): GoogleMap.InfoWindowAdapter {

    private val contentView = (context as Activity).layoutInflater.inflate(R.layout.custom_info_marker_window, null)

    override fun getInfoContents(marker: Marker): View? {
        renderViews(marker, contentView)
        return contentView
    }

    override fun getInfoWindow(marker: Marker): View? {
        renderViews(marker, contentView)
        return contentView
    }

    private fun renderViews(marker: Marker?, contentView: View) {
        with(contentView){
            findViewById<TextView>(R.id.tvTitle).apply { text = marker?.title }
            findViewById<TextView>(R.id.tvDescription).apply { text = marker?.snippet }
        }
    }
}