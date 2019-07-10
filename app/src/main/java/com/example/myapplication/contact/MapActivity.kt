package com.example.myapplication.contact

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.myapplication.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

/**
 * Created by Jiangning LIN on 10/07/2019.
 */
class MapActivity: AppCompatActivity(), OnMapReadyCallback {
    private var mMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val dhaka = LatLng(48.840748, 2.219718)
        mMap?.let {

            it.addMarker(MarkerOptions().position(dhaka).title("Airweb"))
            it.moveCamera(CameraUpdateFactory.newLatLng(dhaka))
        }
    }
}