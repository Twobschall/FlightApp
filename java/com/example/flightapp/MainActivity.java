package com.example.flightapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.flightapp.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private long currentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tvBurnRate = findViewById(R.id.tvBurnRate);
        final TextView tvFuelBingo = findViewById(R.id.tvFuelBingo);
        final TextView tvFuelReserve20 = findViewById(R.id.tvFuelReserve20);
        final TextView tvFuelReserve30 = findViewById(R.id.tvFuelReserve30);
        final EditText etInitialFuel = findViewById(R.id.etInitialFuel);
        final EditText etEndFuel = findViewById(R.id.etEndFuel);
        final EditText etTimeBetweenChecks = findViewById(R.id.etTimeBetweenChecks);

        etInitialFuel.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                calculate();
            }
        });

        etEndFuel.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                // Update the current time variable
                currentTime = System.currentTimeMillis();
                calculate();
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                calculate();
            }
        });

        etTimeBetweenChecks.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                calculate();
            }
        });
    }

    private void calculate() {
        final TextView tvFuelBingo = findViewById(R.id.tvFuelBingo);
        final TextView tvBurnRate = findViewById(R.id.tvBurnRate);
        final TextView tvFuelReserve20 = findViewById(R.id.tvFuelReserve20);
        final TextView tvFuelReserve30 = findViewById(R.id.tvFuelReserve30);
        final EditText etInitialFuel = findViewById(R.id.etInitialFuel);
        final EditText etEndFuel = findViewById(R.id.etEndFuel);
        final EditText etTimeBetweenChecks = findViewById(R.id.etTimeBetweenChecks);

        String etInitialFuelStr = etInitialFuel.getText().toString();
        String etEndFuelStr = etEndFuel.getText().toString();
        String etTimeBetweenChecksStr = etTimeBetweenChecks.getText().toString();
        double etInitialFuelDbl;
        try {
            etInitialFuelDbl = Double.parseDouble(etInitialFuelStr);
        } catch (NumberFormatException e) {
            etInitialFuelDbl = 0;
        }
        double etEndFuelDbl;
        try {
            etEndFuelDbl = Double.parseDouble(etEndFuelStr);
        } catch (NumberFormatException e) {
            etEndFuelDbl = 0;
        }
        double etTimeBetweenChecksDbl;
        try {
            etTimeBetweenChecksDbl = Double.parseDouble(etTimeBetweenChecksStr);
        } catch (NumberFormatException e) {
            etTimeBetweenChecksDbl = 15;
        }

        double burnrate = ((etInitialFuelDbl - etEndFuelDbl) * (60 / etTimeBetweenChecksDbl));
        String burnratemessage = "Burn rate is " + burnrate + " pounds/hour";
        tvBurnRate.setText(burnratemessage);

        double endfuelMinutes = (etEndFuelDbl / ((etInitialFuelDbl - etEndFuelDbl) * (60 / etTimeBetweenChecksDbl)));
        long durationMillis = (long) (endfuelMinutes * 60 * 60 * 1000);
        long endTimeMillis = currentTime + durationMillis;
        Date endTime = new Date(endTimeMillis);
        int endfuelHours = (int) Math.floor(endfuelMinutes);
        double endfuelMinutesRemainder = Math.round((endfuelMinutes - endfuelHours) * 60);
        String endfuelmessage = "Burn out in " + endfuelHours + " hours " + endfuelMinutesRemainder + " minutes at " + formatTime(endTime);
        tvFuelBingo.setText(endfuelmessage);

        double endfuelMinutes20 = etEndFuelDbl / (((etInitialFuelDbl - etEndFuelDbl) * 60) / etTimeBetweenChecksDbl) - 0.33;
        long durationMillis20 = (long) (endfuelMinutes20 * 60 * 60 * 1000);
        long endTimeMillis20 = currentTime + durationMillis20;
        Date endTime20 = new Date(endTimeMillis20);
        int endfuelHours20 = (int) Math.floor(endfuelMinutes20);
        double endfuelMinutesRemainder20 = Math.round((endfuelMinutes20 - endfuelHours20) * 60);
        String endfuelmessage20 = "20 minute reserve is " + endfuelHours20 + " hours " + endfuelMinutesRemainder20 + " minutes at " + formatTime(endTime20);
        tvFuelReserve20.setText(endfuelmessage20);

        double endfuelMinutes30 = etEndFuelDbl / (((etInitialFuelDbl - etEndFuelDbl) * 60) / etTimeBetweenChecksDbl) - 0.50;
        long durationMillis30 = (long) (endfuelMinutes30 * 60 * 60 * 1000);
        long endTimeMillis30 = currentTime + durationMillis30;
        Date endTime30 = new Date(endTimeMillis30);
        int endfuelHours30 = (int) Math.floor(endfuelMinutes30);
        double endfuelMinutesRemainder30 = Math.round((endfuelMinutes30 - endfuelHours30) * 60);
        String endfuelmessage30 = "30 minute reserve is " + endfuelHours30 + " hours " + endfuelMinutesRemainder30 + " minutes at " + formatTime(endTime30);
        tvFuelReserve30.setText(endfuelmessage30);
    }

    private String formatTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        return dateFormat.format(date);
    }
}
