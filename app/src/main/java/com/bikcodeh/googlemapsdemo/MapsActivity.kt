package com.bikcodeh.googlemapsdemo

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.lifecycle.lifecycleScope

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.bikcodeh.googlemapsdemo.databinding.ActivityMapsBinding
import com.bikcodeh.googlemapsdemo.misc.CameraAndViewport
import com.bikcodeh.googlemapsdemo.misc.CustomInfoAdapter
import com.bikcodeh.googlemapsdemo.misc.TypeAndStyle
import com.google.android.gms.maps.model.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMarkerDragListener {

    private lateinit var map: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private val typeAndStyle by lazy { TypeAndStyle() }
    private val cameraAndViewport by lazy { CameraAndViewport() }

    private val losAngeles = LatLng(34.051841600403634, -118.24025417915313)
    private val newYork = LatLng(40.6976637,-74.119764)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /** Obtain the SupportMapFragment and get notified when the map is ready to be used. */
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

        /** Add a marker in Sydney and move the camera */

        val losAngeles2 = LatLng(34.00063704769043, -118.24884200288899)
        //A marker set in los angeles draggable
        //val losAngelesMarker = map.addMarker(MarkerOptions().position(losAngeles).title("Marker in Los Angeles").draggable(true))
        /** Change marker's color with HUE value, we can find some color with HSL calculator https://www.w3schools.com/colors/colors_hsl.asp */
        /*val losAngelesMarker = map.addMarker(
            MarkerOptions()
                .position(losAngeles)
                .title("Marker in Los Angeles")
                .icon(BitmapDescriptorFactory.defaultMarker(134f))
        )*/
        /** Add property zIndex to set the visibility of the marker above others*/
        /*val losAngelesMarker2 = map.addMarker(
            MarkerOptions()
                .position(losAngeles2)
                .title("Marker in Los Angeles2")
                .snippet("Some random text")
                //.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))
                .zIndex(1f)
        )*/
        /** Change marker for png marker */
        val losAngelesMarker = map.addMarker(
            MarkerOptions()
                .position(losAngeles)
                .title("Marker in Los Angeles")
                .snippet("Some random text")
                //.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))
        )
        /** Custom marker from drawable with a specific color */
        /*val losAngelesMarker = map.addMarker(
            MarkerOptions()
                .position(losAngeles)
                .title("Marker in Los Angeles")
                .icon(fromVectorToBitmap(R.drawable.ic_android, Color.parseColor("#000000")))
        )*/
        losAngelesMarker?.tag = "Restaurant"
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(losAngeles, 10f))
        //map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraAndViewport.losAngeles))
        map.uiSettings.apply {
            /** Buttons for zooming in and zooming out - false by default */
            isZoomControlsEnabled = true
            /** Gestures to zooming in or zooming out with taps and so on */
            //isZoomGesturesEnabled = false
            /** Not move the map, static position */
            //isScrollGesturesEnabled = false
        }
        map.setOnMarkerClickListener(this)
        map.setOnMarkerDragListener(this)
        map.setInfoWindowAdapter(CustomInfoAdapter(this))
        addPolyline()
        /** (left, top, right, bottom)
            Padding to move the zooming controls when maybe the app is using some drawer */
        //map.setPadding(0, 0, 300, 0)
        typeAndStyle.setMapStyle(map, this)

        /** Set min and max zoom in all devices */
        //map.setMinZoomPreference(15f)
        //map.setMaxZoomPreference(17f)

        /** Programmatically set zoom */

        /*lifecycleScope.launch {
            delay(4000L)
            map.moveCamera(CameraUpdateFactory.zoomBy(3f))
        }*/

        lifecycleScope.launch {
            delay(4000L)
            /** How to remove a specific marker */
            //losAngelesMarker?.remove()
            /** To move the camera position but without animation */
            //map.moveCamera(CameraUpdateFactory.newLatLng(newYork))
            /** To move the camera position in axis X and axis Y without animation */
            //map.moveCamera(CameraUpdateFactory.scrollBy(-200f, 100f))
            /** To use bounds to set some specific area */
            //map.moveCamera(CameraUpdateFactory.newLatLngBounds(cameraAndViewport.newYorkBounds, 100))
            /** To use bounds to set some specific center area between two latlng areas with some specific zoom value */
            //map.moveCamera(CameraUpdateFactory.newLatLngZoom(cameraAndViewport.melbourneBounds.center, 10f))
            /** Animate the move of the camera with a duration a null callback */
            //map.animateCamera(CameraUpdateFactory.newLatLngZoom(cameraAndViewport.melbourneBounds.center, 10f), 2000, null)
            /** Animate zoom */
            //map.animateCamera(CameraUpdateFactory.zoomTo(15f), 2000, null)
            /**  Animate scroll */
            //map.animateCamera(CameraUpdateFactory.scrollBy(-200f, 100f))
            /** Restrict user from scrolling only in the bounds */
            //map.setLatLngBoundsForCameraTarget(cameraAndViewport.melbourneBounds)
            /** Animate Camera position object */
            //map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraAndViewport.losAngeles), 2000, null)
            /** Animate with callback */
           /* map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraAndViewport.losAngeles), 2000, object: GoogleMap.CancelableCallback {
                override fun onCancel() {
                    Toast.makeText(this@MapsActivity, "Canceled animation", Toast.LENGTH_SHORT).show()
                }

                override fun onFinish() {
                    Toast.makeText(this@MapsActivity, "Finished animation", Toast.LENGTH_SHORT).show()
                }
            })*/
        }
        //onMapClicked()
        //onMapLongClicked()
    }

    private fun onMapClicked() {
        map.setOnMapClickListener {
            Toast.makeText(this@MapsActivity, "Single click", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onMapLongClicked() {
        map.setOnMapLongClickListener {
            Toast.makeText(this@MapsActivity, "Long click", Toast.LENGTH_SHORT).show()
            map.addMarker(MarkerOptions().position(it).title("New marker"))
        }
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        Log.i("Marker tag", marker.tag.toString())
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.position, 17f), 2000, null)
        marker.showInfoWindow()
        //If returns true, the marker's title won't show it unless you show it programmatically
        return true
    }

    override fun onMarkerDrag(marker: Marker) {
        Log.i("MarkerDrag", marker.tag.toString())
    }

    override fun onMarkerDragEnd(marker: Marker) {
        Log.i("MarkerDragEnd", marker.tag.toString())
    }

    override fun onMarkerDragStart(marker: Marker) {
        Log.i("MarkerDragStart", marker.tag.toString())
    }

    private fun fromVectorToBitmap(id: Int, color: Int): BitmapDescriptor {
        val vectorDrawable: Drawable? = ResourcesCompat.getDrawable(resources, id, null)
        if (vectorDrawable == null) {
            return BitmapDescriptorFactory.defaultMarker()
        }
        val bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.minimumHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        vectorDrawable.setBounds(0, 0, canvas.width, canvas.height)
        DrawableCompat.setTint(vectorDrawable, color)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    private fun addPolyline() {
        //val pattern = listOf(Dot(), Gap(30f)) only circles
        //val pattern = listOf(Dash(30f), Dot(), Gap(30f), Dot()) //circle line circle

        val polyline = map.addPolyline(PolylineOptions().apply {
            add(losAngeles, newYork)
            width(20f)
            color(Color.BLUE)
            pattern(pattern)
            /** With this property, when in some point there is a union between two points like V the corner will be
             * rounded or bevel for more visual friendly with the user */
            //jointType(JointType.BEVEL)
        })
    }
}