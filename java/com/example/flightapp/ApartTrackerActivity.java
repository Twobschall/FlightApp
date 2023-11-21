package com.example.flightapp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ApartTrackerActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "MyPrefsFile";

    private EditText editTextFoundationalKnowledgeExamDate;
    private EditText editTextColdWeatherOperations;
    private EditText editTextMountainEnvironmentalTraining;
    private EditText editTextHotWeatherOperations;
    private EditText editTextAltitudeAndPhysiology;
    private EditText editTextSpatialDisorientation;
    private EditText editTextStressAndFatigue;
    private EditText editTextExogenousFactors;
    private EditText editTextHGU56P;
    private EditText editTextFlightClothing;
    private EditText editTextIntroductionToAirWarrior;
    private EditText editTextSKRAMBag;
    private EditText editTextFirstAidKits;
    private EditText editTextEmergencyLocatorTransmitter;
    private EditText editTextCBATO;
    private EditText editTextROCV;
    private EditText editTextThreatAwareness;
    private EditText editTextTacticsTask2900;
    private EditText editTextMissionPlanning;
    private EditText editTextCBATC;
    private EditText editTextDayFlight;
    private EditText editTextNVGFlight;
    private EditText editTextHoist;
    private Button btnHome;
    private List<EditText> allEditTextFields; // List to hold all EditText fields



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aparttracker);

        // Initialize EditText fields
        editTextFoundationalKnowledgeExamDate = findViewById(R.id.editTextFoundationalKnowledgeExamDate);
        editTextColdWeatherOperations = findViewById(R.id.editTextColdWeatherOperations);
        editTextMountainEnvironmentalTraining = findViewById(R.id.editTextMountainEnvironmentalTraining);
        editTextHotWeatherOperations = findViewById(R.id.editTextHotWeatherOperations);
        editTextAltitudeAndPhysiology = findViewById(R.id.editTextAltitudeAndPhysiology);
        editTextSpatialDisorientation = findViewById(R.id.editTextSpatialDisorientation);
        editTextStressAndFatigue = findViewById(R.id.editTextStressAndFatigue);
        editTextExogenousFactors = findViewById(R.id.editTextExogenousFactors);
        editTextHGU56P = findViewById(R.id.editTextHGU56P);
        editTextFlightClothing = findViewById(R.id.editTextFlightClothing);
        editTextIntroductionToAirWarrior = findViewById(R.id.editTextIntroductionToAirWarrior);
        editTextSKRAMBag = findViewById(R.id.editTextSKRAMBag);
        editTextFirstAidKits = findViewById(R.id.editTextFirstAidKits);
        editTextEmergencyLocatorTransmitter = findViewById(R.id.editTextEmergencyLocatorTransmitter);
        editTextCBATO = findViewById(R.id.editTextCBATO);
        editTextROCV = findViewById(R.id.editTextROCV);
        editTextThreatAwareness = findViewById(R.id.editTextThreatAwareness);
        editTextTacticsTask2900 = findViewById(R.id.editTextTactics2900);
        editTextMissionPlanning = findViewById(R.id.editTextMissionPlanning);
        editTextCBATC = findViewById(R.id.editTextCBATC);
        editTextDayFlight = findViewById(R.id.editTextDayFlight);
        editTextNVGFlight = findViewById(R.id.editTextNVGFlight);
        editTextHoist = findViewById(R.id.editTextHoist);

        btnHome = findViewById(R.id.btnHome);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomeActivity();
            }
        });

        // Load the saved data
        loadData();

        // Setup date picker for relevant EditText fields
        setupDatePickerForEditTextsWithDateInputType();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Save the data
        saveData();
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Save the data for each EditText field
        editor.putString("foundationalKnowledgeExamDate", editTextFoundationalKnowledgeExamDate.getText().toString());
        editor.putString("coldWeatherOperations", editTextColdWeatherOperations.getText().toString());
        editor.putString("MountainEnvironmentalTraining", editTextMountainEnvironmentalTraining.getText().toString());
        editor.putString("HotWeatherOperations", editTextHotWeatherOperations.getText().toString());
        editor.putString("AltitudeAndPhysiology", editTextAltitudeAndPhysiology.getText().toString());
        editor.putString("SpatialDisorientation", editTextSpatialDisorientation.getText().toString());
        editor.putString("StressAndFatigue", editTextStressAndFatigue.getText().toString());
        editor.putString("ExogenousFactors", editTextExogenousFactors.getText().toString());
        editor.putString("HGU56P", editTextHGU56P.getText().toString());
        editor.putString("FlightClothing", editTextFlightClothing.getText().toString());
        editor.putString("IntroductionToAirWarrior", editTextIntroductionToAirWarrior.getText().toString());
        editor.putString("SKRAMBag", editTextSKRAMBag.getText().toString());
        editor.putString("FirstAidKits", editTextFirstAidKits.getText().toString());
        editor.putString("EmergencyLocatorTransmitter", editTextEmergencyLocatorTransmitter.getText().toString());
        editor.putString("CBATO", editTextCBATO.getText().toString());
        editor.putString("ROCV", editTextROCV.getText().toString());
        editor.putString("ThreatAwareness", editTextThreatAwareness.getText().toString());
        editor.putString("TacticsTask2900", editTextTacticsTask2900.getText().toString());
        editor.putString("MissionPlanning", editTextMissionPlanning.getText().toString());
        editor.putString("CBATC", editTextCBATC.getText().toString());
        editor.putString("DayFlight", editTextDayFlight.getText().toString());
        editor.putString("NVGFlight", editTextNVGFlight.getText().toString());
        editor.putString("Hoist", editTextHoist.getText().toString());
        // Save other EditText fields here for other text views

        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // Retrieve the saved data for each EditText field
        String foundationalKnowledgeExamDate = sharedPreferences.getString("foundationalKnowledgeExamDate", "");
        String coldWeatherOperations = sharedPreferences.getString("coldWeatherOperations", "");
        String MountainEnvironmentalTraining = sharedPreferences.getString("MountainEnvironmentalTraining", "");
        String HotWeatherOperations = sharedPreferences.getString("HotWeatherOperations", "");
        String AltitudeAndPhysiology = sharedPreferences.getString("AltitudeAndPhysiology", "");
        String SpatialDisorientation = sharedPreferences.getString("SpatialDisorientation", "");
        String StressAndFatigue = sharedPreferences.getString("StressAndFatigue", "");
        String ExogenousFactors = sharedPreferences.getString("ExogenousFactors", "");
        String HGU56P = sharedPreferences.getString("HGU56P", "");
        String FlightClothing = sharedPreferences.getString("FlightClothing", "");
        String IntroductionToAirWarrior = sharedPreferences.getString("IntroductionToAirWarrior", "");
        String SKRAMBag = sharedPreferences.getString("SKRAMBag", "");
        String FirstAidKits = sharedPreferences.getString("FirstAidKits", "");
        String EmergencyLocatorTransmitter = sharedPreferences.getString("EmergencyLocatorTransmitter", "");
        String CBATO = sharedPreferences.getString("CBATO", "");
        String ROCV = sharedPreferences.getString("ROCV", "");
        String ThreatAwareness = sharedPreferences.getString("ThreatAwareness", "");
        String TacticsTask2900 = sharedPreferences.getString("TacticsTask2900", "");
        String MissionPlanning = sharedPreferences.getString("MissionPlanning", "");
        String CBATC = sharedPreferences.getString("CBATC", "");
        String DayFlight = sharedPreferences.getString("DayFlight", "");
        String NVGFlight = sharedPreferences.getString("NVGFlight", "");
        String Hoist = sharedPreferences.getString("Hoist", "");
        // Retrieve other EditText fields here for other text views

        // Set the retrieved data to the EditText fields
        editTextFoundationalKnowledgeExamDate.setText(foundationalKnowledgeExamDate);
        editTextColdWeatherOperations.setText(coldWeatherOperations);
        editTextMountainEnvironmentalTraining.setText(MountainEnvironmentalTraining);
        editTextHotWeatherOperations.setText(HotWeatherOperations);
        editTextAltitudeAndPhysiology.setText(AltitudeAndPhysiology);
        editTextSpatialDisorientation.setText(SpatialDisorientation);
        editTextStressAndFatigue.setText(StressAndFatigue);
        editTextExogenousFactors.setText(ExogenousFactors);
        editTextHGU56P.setText(HGU56P);
        editTextFlightClothing.setText(FlightClothing);
        editTextIntroductionToAirWarrior.setText(IntroductionToAirWarrior);
        editTextSKRAMBag.setText(SKRAMBag);
        editTextFirstAidKits.setText(FirstAidKits);
        editTextEmergencyLocatorTransmitter.setText(EmergencyLocatorTransmitter);
        editTextCBATO.setText(CBATO);
        editTextROCV.setText(ROCV);
        editTextThreatAwareness.setText(ThreatAwareness);
        editTextTacticsTask2900.setText(TacticsTask2900);
        editTextMissionPlanning.setText(MissionPlanning);
        editTextCBATC.setText(CBATC);
        editTextDayFlight.setText(DayFlight);
        editTextNVGFlight.setText(NVGFlight);
        editTextHoist.setText(Hoist);
        // Set other EditText fields here for other text views
    }

    private void setupDatePickerForEditTextsWithDateInputType() {
        // Create a calendar instance to get the current date
        final Calendar calendar = Calendar.getInstance();

        // Create the list to hold all EditText fields
        allEditTextFields = new ArrayList<>();
        allEditTextFields.add(editTextFoundationalKnowledgeExamDate);
        allEditTextFields.add(editTextColdWeatherOperations);
        allEditTextFields.add(editTextHotWeatherOperations);
        allEditTextFields.add(editTextAltitudeAndPhysiology);
        allEditTextFields.add(editTextSpatialDisorientation);
        allEditTextFields.add(editTextStressAndFatigue);
        allEditTextFields.add(editTextExogenousFactors);
        allEditTextFields.add(editTextHGU56P);
        allEditTextFields.add(editTextFlightClothing);
        allEditTextFields.add(editTextIntroductionToAirWarrior);
        allEditTextFields.add(editTextSKRAMBag);
        allEditTextFields.add(editTextFirstAidKits);
        allEditTextFields.add(editTextEmergencyLocatorTransmitter);
        allEditTextFields.add(editTextCBATO);
        allEditTextFields.add(editTextROCV);
        allEditTextFields.add(editTextThreatAwareness);
        allEditTextFields.add(editTextTacticsTask2900);
        allEditTextFields.add(editTextMissionPlanning);
        allEditTextFields.add(editTextCBATC);
        allEditTextFields.add(editTextMountainEnvironmentalTraining);
        allEditTextFields.add(editTextDayFlight);
        allEditTextFields.add(editTextNVGFlight);
        allEditTextFields.add(editTextHoist);
        // Add other EditText fields here for other dates

        // Iterate through all the EditText fields
        for (final EditText editText : allEditTextFields) {
            // Set onClickListener for the date picker on relevant EditText fields
            editText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Create a date picker dialog with the current date as the default date
                    DatePickerDialog datePickerDialog = new DatePickerDialog(ApartTrackerActivity.this,
                            R.style.CustomDatePickerDialog, // Use the custom style here
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                    // Set the selected date on the EditText field
                                    String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                                    editText.setText(date);
                                }
                            },
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH));

                    // Show the date picker dialog
                    datePickerDialog.show();
                }
            });
        }
    }


    private void openHomeActivity() {
        // Define the intent to open the HomeActivity
        Intent intent = new Intent(this, HomeActivity.class);

        // Start the HomeActivity
        startActivity(intent);
    }
}


