package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MapsExercise extends AppCompatActivity {
    ImageButton ib1, ib2, ib3, ib4, ib5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_exercise);

        String[] coords = {
                "geo:10.281420367131922, 123.87867515281074", // Cebu Ocean Park
                "geo:10.271967606591394, 123.88120119454314", // Nustar Resort
                "geo:43.098331177484425, 142.82985049936624", // Shintokuyama Ski Area
                "geo:35.65951363042907, 139.7006111319989", // Shibuya Crossing
                "geo:35.64498006731865, 139.74317667213867", // Cover Corp
        };

        ib1 = findViewById(R.id.imageButton1);
        ib2 = findViewById(R.id.imageButton2);
        ib3 = findViewById(R.id.imageButton3);
        ib4 = findViewById(R.id.imageButton4);
        ib5 = findViewById(R.id.imageButton5);

        mapOnlickListener(ib1, coords[0], "Cebu Ocean Park", R.drawable.cebu_ocean_park, R.drawable.street_cebu_ocean_park);
        mapOnlickListener(ib2, coords[1], "Nustar Resort", R.drawable.nustar_resort, R.drawable.street_nustar_resort);
        mapOnlickListener(ib3, coords[2], "Shintokuyama Ski Area", R.drawable.shintokuyama_ski_area, R.drawable.street_shintokuyama_ski_area);
        mapOnlickListener(ib4, coords[3], "Shibuya Crossing", R.drawable.shibuya_crossing, R.drawable.street_shibuya_crossing);
        mapOnlickListener(ib5, coords[4], "Cover Corp", R.drawable.cover_corp, R.drawable.street_cover_corp);

    }

    private void mapOnlickListener(ImageButton button, final String coord, String locName, int bg, int streetView) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapsExercise.this, MapsExercise2.class);
                intent.putExtra("coord", coord);
                intent.putExtra("locationName", locName);
                intent.putExtra("bg", bg);
                intent.putExtra("streetView", streetView);

                startActivity(intent);
            }
        });
    }

}