package com.example.test;

import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.test.data.Coord;
import com.example.test.data.Main;
import com.example.test.data.WeatherData;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.test.databinding.ActivityMapsBinding;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;




public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private Api mApi = Api.Instance.getApi();
    private ActivityMapsBinding binding;

    private WeatherData weatherData;

    public void backGo(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Bundle arguments = getIntent().getExtras();
        float lat = (float) arguments.get("lat");
        float lon = (float) arguments.get("lon");

        LatLng latLng = new LatLng(lat, lon);
        mMap.addMarker(new MarkerOptions().position(latLng).title("Marker in city"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }
}