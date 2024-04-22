package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PassingIntentsExercise2 extends AppCompatActivity {
    Button btnFinish;
    TextView tFname, tLname, tGender, tBdate, tPnum, tEmail, tAddress, tCity, tState, tYear, tPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passing_intents_exercise2);

        tFname = findViewById(R.id.textFirstName);
        tLname = findViewById(R.id.textLastName);
        tGender = findViewById(R.id.textGender);
        tBdate = findViewById(R.id.textBirthDate);
        tPnum = findViewById(R.id.textPhoneNumber);
        tEmail = findViewById(R.id.textEmailAddress);
        tAddress = findViewById(R.id.textAddress); // New
        tCity = findViewById(R.id.textCity);
        tState = findViewById(R.id.textState);
        tYear = findViewById(R.id.textYear);
        tPassword = findViewById(R.id.textPassword);
        btnFinish = findViewById(R.id.btnReturn);

        Intent intent = getIntent();

        String fname = intent.getStringExtra("fname_key");
        String lname = intent.getStringExtra("lname_key");
        String gender = intent.getStringExtra("gender_key");
        String bdate = intent.getStringExtra("bdate_key");
        String pnum = intent.getStringExtra("pnum_key");
        String email = intent.getStringExtra("eadd_key");
        String address = intent.getStringExtra("add_key");
        String city = intent.getStringExtra("city_key");
        String state = intent.getStringExtra("state_key");
        String year = intent.getStringExtra("year_key");
        String pwd = intent.getStringExtra("pwd_key");

        tFname.setText(fname);
        tLname.setText(lname);
        tGender.setText(gender);
        tBdate.setText(bdate);
        tPnum.setText(pnum);
        tEmail.setText(email);
        tAddress.setText(address);
        tCity.setText(city);
        tState.setText(state);
        tYear.setText(year);
        tPassword.setText(pwd);

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}