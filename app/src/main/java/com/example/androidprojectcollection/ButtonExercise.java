package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ButtonExercise extends AppCompatActivity {

    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_exercise);

        btn1 = (Button) findViewById(R.id.btnDisplayToast);
        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(ButtonExercise.this, "This is my Toast message!",
                        Toast.LENGTH_LONG).show();
            }
        });

        btn2 = (Button) findViewById(R.id.btnBack);
        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn3 = (Button) findViewById(R.id.btnChangeBtnBG);
        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                btn3.setBackgroundColor(Color.parseColor("#ffef12"));
            }
        });

        btn4 = (Button) findViewById(R.id.btnChangeBG);
        ConstraintLayout bg = (ConstraintLayout) findViewById(R.id.buttonExerciseBackground);
        btn4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                bg.setBackgroundColor(Color.parseColor("#373737"));
            }
        });

        btn5 = (Button) findViewById(R.id.btnRemoveBtn);
        btn5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                btn5.setVisibility(View.INVISIBLE);
            }
        });
    }
}