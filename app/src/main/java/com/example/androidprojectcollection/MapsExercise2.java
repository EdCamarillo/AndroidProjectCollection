package com.example.androidprojectcollection;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MapsExercise2 extends AppCompatActivity {
    TextView txtLocationName;
    ConstraintLayout mapBackground;
    ImageView imgStreetView;
    Button btnOpenMaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_exercise2);

        txtLocationName = findViewById(R.id.txtLocationName);
        mapBackground = findViewById(R.id.mapBackground);
        imgStreetView = findViewById(R.id.imgStreetView);
        btnOpenMaps = findViewById(R.id.btnOpenMaps);

        Intent intent = getIntent();

        String coord = intent.getStringExtra("coord");
        String locName = intent.getStringExtra("locationName");
        int bg = intent.getIntExtra("bg", 0);
        int streetView = intent.getIntExtra("streetView", 0);

        txtLocationName.setText(locName);
        mapBackground.setBackground(getDrawable(bg));
        imgStreetView.setImageResource(streetView);

        btnOpenMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(coord));
                startActivity(intent);
            }
        });
    }
}