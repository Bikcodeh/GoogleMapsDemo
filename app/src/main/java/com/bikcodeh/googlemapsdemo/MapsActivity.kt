package com.bikcodeh.googlemapsdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.bikcodeh.googlemapsdemo.databinding.ActivityMapsBinding
import com.bikcodeh.googlemapsdemo.misc.CameraAndViewport
import com.bikcodeh.googlemapsdemo.misc.TypeAndStyle
import com.google.android.gms.maps.model.MapStyleOptions
import java.lang.Exception

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private val typeAndStyle by lazy { TypeAndStyle() }
    private val cameraAndViewport by lazy { CameraAndViewport() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.map_types_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        typeAndStyle.setMapType(item, map)
        return true
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        // Add a marker in Sydney and move the camera
        val losAngeles = LatLng(34.051841600403634, -118.24025417915313)
        map.addMarker(MarkerOptions().position(losAngeles).title("Marker in Los Angeles"))
        //map.moveCamera(CameraUpdateFactory.newLatLngZoom(losAngeles, 10f))
        map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraAndViewport.losAngeles))
        map.uiSettings.apply {
            //Buttons for zooming in and zooming out - false by default
            isZoomControlsEnabled = true
            //Gestures to zooming in or zooming out with taps and so on
            //isZoomGesturesEnabled = false
            //Not move the map, static position
            //isScrollGesturesEnabled = false
        }

        //(left, top, right, bottom)
        //Padding to move the zooming controls when maybe the app is using some drawer
        //map.setPadding(0, 0, 300, 0)
        typeAndStyle.setMapStyle(map, this)
    }
}