package com.example.flightapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private Button btnPage1;
    private Button btnPage2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnPage1 = findViewById(R.id.btnPage1);
        btnPage2 = findViewById(R.id.btnPage2);

        btnPage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPage1();
            }
        });

        btnPage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPage2();
            }
        });
    }

    private void openPage1() {
        // Replace Page1Activity.class with the actual activity class for Page 1
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void openPage2() {
        // Replace Page2Activity.class with the actual activity class for Page 2
        Intent intent = new Intent(this, ApartTrackerActivity.class);
        startActivity(intent);
    }
}
