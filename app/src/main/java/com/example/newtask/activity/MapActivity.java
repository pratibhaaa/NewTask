package com.example.newtask.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.newtask.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

public class MapActivity extends AppCompatActivity  implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}