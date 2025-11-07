package com.example.albaymap

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import org.osmdroid.views.MapView
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Marker
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.config.Configuration

class DisplayMap : AppCompatActivity() {

    private var mapView: MapView? = null
    private var btnToggle: Button? = null
    private var isSatellite = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Configuration.getInstance().load(applicationContext, getSharedPreferences("osmdroid_settings", MODE_PRIVATE))
        setContentView(R.layout.activity_displaymap)

        mapView = findViewById(R.id.mapViewDisplayMap)
        btnToggle = findViewById(R.id.btnToggle)

        createMap()

        btnToggle!!.setOnClickListener() {
            if(isSatellite){
                mapView!!.setTileSource(TileSourceFactory.MAPNIK)
                btnToggle!!.setText("Satellite View")
            }
            else{
                mapView!!.setTileSource(TileSourceFactory.USGS_SAT)
                btnToggle!!.setText("Normal View")
            }
            isSatellite = !isSatellite
        }

    }

    private fun createMap(){
        mapView!!.setTileSource(TileSourceFactory.MAPNIK)
        mapView!!.setMultiTouchControls(true)
        mapView!!.controller.setZoom(15.0)


        //Point of marker
        val startPoint = GeoPoint(13.1391, 123.7438)
        mapView!!.controller.setCenter(startPoint)

        val startMarker = Marker(mapView)
        startMarker.position = startPoint
        startMarker.title = "Test Marker"
        startMarker.subDescription = "This is a test marker.\n Location: Entrance to Vil Amor"

        mapView!!.overlays.add(startMarker)
    }

    override fun onPause(){
        super.onPause()
        mapView!!.onPause()
    }

    override fun onResume(){
        super.onResume()
        mapView!!.onResume()
    }
}