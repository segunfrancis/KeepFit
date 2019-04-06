package com.example.computer.bmibmrapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class BMRActivity extends AppCompatActivity {

    // Declaration of Variables
    EditText height, weight, age;
    Spinner gender;
    TextView checkBMR, bmrText, improveBMR, improveBMRMethods, precautions, precautionsList;
    String userHeight, userWeight, userAge, userGender, convertedBMR;
    double convertedHeight, convertedWeight, convertedAge;
    double bmr;
    DecimalFormat decimalFormat;

    @Override
    protected void onRestart() {
        restartActivity();
        super.onRestart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences preferences = getSharedPreferences(getPackageName() + ".my_theme", MODE_PRIVATE);
        boolean isChecked = preferences.getBoolean("theme", false);

        if (isChecked) {
            setTheme(R.style.NightTheme);
        } else {
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmr);

        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        age = findViewById(R.id.age);
        gender = findViewById(R.id.gender_spinner);
        checkBMR = findViewById(R.id.check_bmr);
        bmrText = findViewById(R.id.bmr_text);
        improveBMR = findViewById(R.id.improve_bmr);
        improveBMRMethods = findViewById(R.id.methods);
        precautions = findViewById(R.id.precautions);
        precautionsList = findViewById(R.id.precautions_list);

        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(new NothingSelectedSpinnerAdapter(adapter, R.layout.gender_spinner_row_nothing_selected, this));


        checkBMR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userHeight = height.getText().toString();
                userWeight = weight.getText().toString();
                userAge = age.getText().toString();

//                userGender = null;
                if (userHeight.length() > 0 && userWeight.length() > 0 && userAge.length() > 0 && gender != null && gender.getSelectedItem() != null) {
                    convertedHeight = new Double(userHeight);
                    convertedWeight = new Double(userWeight);
                    convertedAge = new Double(userAge);

                    userGender = (String) gender.getSelectedItem();
                    if (userGender.matches("Male")) {
                        AppCalculations calculations = new AppCalculations();
                        bmr = calculations.bmrMaleCalculation(convertedHeight, convertedWeight, convertedAge);
                        bmrText.setText("Your BMR is " + bmr + "k/cal");
                    } else if (userGender.matches("Female")) {
                        AppCalculations calculations = new AppCalculations();
                        bmr = calculations.bmrFemaleCalculation(convertedHeight, convertedWeight, convertedAge);
                        bmrText.setText("Your BMR is " + bmr + "k/cal");
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Make sure all fields have been filled", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void restartActivity() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}