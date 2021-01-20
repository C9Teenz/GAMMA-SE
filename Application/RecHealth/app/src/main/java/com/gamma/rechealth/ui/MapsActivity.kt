package com.gamma.rechealth

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, EasyPermissions.PermissionCallbacks {

    private lateinit var mMap: GoogleMap
    private lateinit var onCameraIdleListener: GoogleMap.OnCameraIdleListener
    lateinit var lat: String
    lateinit var lng: String
    private val RC_LOCATION_PERM = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        //setMap()
        setLocation()
    }

    private fun setMap() {
        onCameraIdleListener = GoogleMap.OnCameraIdleListener {
            val latLng =
                mMap.cameraPosition.target
            Log.d("TAG", "setMap: $latLng")
            lat = latLng.latitude.toString()
            lng = latLng.longitude.toString()

        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val ananda = LatLng(-7.4209926,109.2128149)
        val hermina = LatLng(-7.426227,109.2017427)
        val islam = LatLng(-7.4209926,109.2128149)
        val elizabeth = LatLng(-7.419801,109.2294231)
        val wijayakusuma = LatLng(-7.4163008,109.2374912)

        mMap.addMarker(MarkerOptions().position(ananda).title("Rumah Sakit Ananda"))
        mMap.addMarker(MarkerOptions().position(hermina).title("Rumah Sakit Hermina"))
        mMap.addMarker(MarkerOptions().position(islam).title("Rumah Sakit Islam"))
        mMap.addMarker(MarkerOptions().position(elizabeth).title("Rumah Sakit Elizabeth"))
        mMap.addMarker(MarkerOptions().position(wijayakusuma).title("Rumah Sakit Wijaya Kusuma"))
        mMap.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                ananda, 12f
            )
        )
        getCurrentLocation()
    }


    private fun getCurrentLocation() {
        val mFusedLocation = LocationServices.getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        mFusedLocation.lastLocation.addOnSuccessListener(
            this
        ) { location -> // Do it all with location
            Log.d(
                "My Current location",
                "Lat : ${location?.latitude} Long : ${location?.longitude}"
            )
            location?.let{
                mMap.moveCamera(
                    CameraUpdateFactory.newLatLngZoom(
                        LatLng(
                            it.latitude,
                            it.longitude
                        ), 6f
                    )
                )
            }

        }

    }

    private fun setLocation() {
        if (!hasLocationPermision())
            setLocationPermission()

    }

    private fun setLocationPermission() {

        EasyPermissions.requestPermissions(
            this,
            "Kami butuh lokasi anda untuk mencari restoran terdekat.",
            RC_LOCATION_PERM,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    }

    private fun hasLocationPermision(): Boolean {
        return EasyPermissions.hasPermissions(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) && EasyPermissions.hasPermissions(this, Manifest.permission.ACCESS_COARSE_LOCATION)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == RC_LOCATION_PERM && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation()
        }
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {

    }

}

