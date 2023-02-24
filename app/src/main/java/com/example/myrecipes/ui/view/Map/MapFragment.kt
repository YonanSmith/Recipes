package com.example.myrecipes.ui.view.Home.Map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myrecipes.R
import com.example.myrecipes.databinding.FragmentMapBinding
import com.example.myrecipes.domain.model.Autor
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : Fragment(), OnMapReadyCallback {

    private var _mBinding: FragmentMapBinding? = null
    private val mBinding get() = _mBinding!!

    private lateinit var map: GoogleMap
    private lateinit var autor: Autor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            autor = it.getParcelable("dataRecipe")!!
        }
    }

    private fun createFragment() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentMapBinding.inflate(inflater, container, false)
        createFragment()
        return mBinding.root
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        createMarker()
    }

    private fun createMarker() {
        val coordinates = LatLng(autor.latitud, autor.longitud)
        val marker = MarkerOptions()
            .position(coordinates)
            .title(autor.nombre)
        map.addMarker(marker)
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(coordinates, 13f),
            2500,
            null
        )
    }
}