package com.example.androidprojectcollection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.graphics.Color;

import java.util.Random;

public class MenuExercise extends AppCompatActivity {
    Button btnChanger;
    Random random;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_exercise);

        btnChanger = findViewById(R.id.btnTransformingButton);
        random = new Random();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_menu_exercise,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mItemChange) {
            Toast.makeText(this, "Edit Object Item is clicked", Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId() == R.id.mItemReset) {
            // Reset (hard-coded)
            resetButton();
        }
        else if (item.getItemId() == R.id.mItemExit) {
            finish();
        }
        else if (item.getItemId() == R.id.mItemChangeShape) {
            // Change Shape
            int radius = random.nextInt(250);
            GradientDrawable drawable = (GradientDrawable) btnChanger.getBackground();
            drawable.setCornerRadius(radius);
            btnChanger.setBackground(drawable);
        }
        else if (item.getItemId() == R.id.mItemChangeTextColor) {
            // Change Text Color
            int textColor = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
            btnChanger.setTextColor(textColor);
        }
        else if (item.getItemId() == R.id.mItemChangeTextSize) {
            // Change Text Size
            int size = random.nextInt(60);
            btnChanger.setTextSize(size);
        }
        else if (item.getItemId() == R.id.mItemChangeButtonColor) {
            // Change Button Color
            int buttonColor = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
            GradientDrawable drawable = (GradientDrawable) btnChanger.getBackground();
            drawable.setColor(buttonColor);
            btnChanger.setBackground(drawable);
        }
        else if (item.getItemId() == R.id.mItemChangeButtonSize) {
            // Change Button Size
            ViewGroup.LayoutParams params = btnChanger.getLayoutParams();
            params.width = random.nextInt(convertDpToPixels(280));
            params.height = random.nextInt(convertDpToPixels(180));

            btnChanger.setLayoutParams(params);
        }

        return true;
    }

    public void resetButton() {
        // Button Shape
        int radius = convertDpToPixels(20);
        GradientDrawable drawable = (GradientDrawable) btnChanger.getBackground();
        drawable.setCornerRadius(radius);
        // Button Size
        int width = convertDpToPixels(250);
        int height = convertDpToPixels(150);
        ViewGroup.LayoutParams params = btnChanger.getLayoutParams();
        params.width = width;
        params.height = height;
        btnChanger.setLayoutParams(params);
        // Button Color
        drawable.setColor(Color.parseColor("#ABABAB"));
        // Text Color
        btnChanger.setTextColor(Color.BLACK);
        // Text Size
        btnChanger.setTextSize(14);

        btnChanger.setBackground(drawable);
    }

    private int convertDpToPixels(int dp) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}