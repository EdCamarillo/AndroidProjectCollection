package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class PassingIntentsExercise extends AppCompatActivity {
    Button submitButton, clearButton;
    EditText eFName, eLName, eBDate, eNum, eMail;
    RadioButton rMale, rFem, rOth;
    RadioGroup rgGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passing_intents_exercise);

        eFName = findViewById(R.id.editTextFirstName);
        eLName = findViewById(R.id.editTextLastName);
        eBDate = findViewById(R.id.editTextBirthDate);
        eNum = findViewById(R.id.editTextPhoneNumber);
        eMail = findViewById(R.id.editTextEmail);
        rMale = findViewById(R.id.rMale);
        rFem = findViewById(R.id.rFem);
        rOth = findViewById(R.id.rOth);
        rgGender = findViewById(R.id.rgGender);

        submitButton = findViewById(R.id.btnSubmit);
        clearButton = findViewById(R.id.btnClear);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fName = eFName.getText().toString();
                String lName = eLName.getText().toString();

                String gender;
                if (rMale.isChecked())
                    gender = "Male";
                else if (rFem.isChecked())
                    gender = "Female";
                else if (rOth.isChecked())
                    gender = "Others";
                else
                    gender = "Unknown";

                String bDate = eBDate.getText().toString();
                String pNumber = eNum.getText().toString();
                String emailAdd = eMail.getText().toString();

                Intent intent = new Intent(PassingIntentsExercise.this, PassingIntentsExercise2.class);
                intent.putExtra("fname_key", fName);
                intent.putExtra("lname_key", lName);
                intent.putExtra("gender_key", gender);
                intent.putExtra("bdate_key", bDate);
                intent.putExtra("pnum_key", pNumber);
                intent.putExtra("eadd_key", emailAdd);

                startActivity(intent);

            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eFName.setText("");
                eLName.setText("");
                rgGender.clearCheck();
                eBDate.setText("");
                eNum.setText("");
                eMail.setText("");
            }
        });
    }
}