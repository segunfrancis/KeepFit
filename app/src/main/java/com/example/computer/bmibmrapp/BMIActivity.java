package com.example.computer.bmibmrapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class BMIActivity extends AppCompatActivity {

    static final String TEXT_1 = "firstText";
    static final String TEXT_2 = "secondText";
    static final String TEXT_3 = "thirdText";
    static final String TEXT_4 = "fourthText";
    static final String TEXT_5 = "fifthText";

    EditText userWeight, userHeight;
    TextView check, bmi_text, improveBMI, improveBMIMethods, precautions, precautionsList;
    String weight, height, lineBreak;
    double converted_height, converted_weight, bmi;
    LinearLayout bmiLinearLayout;

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
        setContentView(R.layout.activity_bmi);

        userWeight = findViewById(R.id.weight);
        userHeight = findViewById(R.id.height);
        check = findViewById(R.id.check_bmi);
        bmi_text = findViewById(R.id.bmi_text);
        improveBMI = findViewById(R.id.improve_bmi);
        improveBMIMethods = findViewById(R.id.methods);
        precautions = findViewById(R.id.precautions);
        precautionsList = findViewById(R.id.precautions_list);
        lineBreak = getString(R.string.line_break);
        bmiLinearLayout = findViewById(R.id.bmi_linear_layout);

        // Making the Linear Layout Invisible
        bmiLinearLayout.setVisibility(View.INVISIBLE);

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBmi();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(TEXT_1, getString(R.string.precautions));
        outState.putString(TEXT_2, getString(R.string.increase_bmi));
        outState.putString(TEXT_3, getString(R.string.increase_bmi_precaution));
        outState.putString(TEXT_4, getString(R.string.increase_bmi_1));
        outState.putString(TEXT_5, getString(R.string.bmi_value_underweight));

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    private void restartActivity() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    public void updateBmi() {
        weight = userWeight.getText().toString();
        height = userHeight.getText().toString();
        if (weight.length() <= 0 || height.length() <= 0) {
            Toast.makeText(BMIActivity.this, "Make sure all fields have been filled", Toast.LENGTH_SHORT).show();
        } else {
            converted_height = Double.parseDouble(height);
            converted_weight = Double.parseDouble(weight);

            AppCalculations calculations = new AppCalculations();
            bmi = calculations.bmiCalculation(converted_height, converted_weight);

            if (bmi < 18.5) {
                bmi_text.setText(getString(R.string.bmi_value_underweight) + bmi);
                bmi_text.setTextColor(getResources().getColor(R.color.colorAmber));
                improveBMI.setText(getString(R.string.increase_bmi));
                improveBMIMethods.setText(getString(R.string.increase_bmi_1) + lineBreak + getString(R.string.increase_bmi_2) + lineBreak + getString(R.string.increase_bmi_3));
                precautions.setText(getString(R.string.precautions));
                precautionsList.setText(getString(R.string.increase_bmi_precaution));
                bmiLinearLayout.setVisibility(View.VISIBLE);
                precautions.setVisibility(View.VISIBLE);
                precautionsList.setVisibility(View.VISIBLE);
            } else if (bmi < 25) {
                bmi_text.setText(getString(R.string.bmi_value_normal) + bmi);
                bmi_text.setTextColor(getResources().getColor(R.color.colorGreen));
                improveBMI.setText(getString(R.string.maintain_bmi));
                improveBMIMethods.setText(getString(R.string.maintain_bmi_1) + lineBreak + getString(R.string.maintain_bmi_2) + lineBreak + getString(R.string.maintain_bmi_3));
                bmiLinearLayout.setVisibility(View.VISIBLE);
                precautions.setVisibility(View.GONE);
                precautionsList.setVisibility(View.GONE);
            } else if (bmi < 30) {
                bmi_text.setText(getString(R.string.bmi_value_overweight) + bmi);
                bmi_text.setTextColor(getResources().getColor(R.color.colorRed));
                improveBMI.setText(getString(R.string.decrease_bmi));
                improveBMIMethods.setText(getString(R.string.reduce_bmi_1) + lineBreak + getString(R.string.reduce_bmi_2) + lineBreak + getString(R.string.reduce_bmi_3) + lineBreak + getString(R.string.reduce_bmi_4));
                precautions.setText(getString(R.string.precautions));
                precautionsList.setText(getString(R.string.decrease_bmi_precaution));
                bmiLinearLayout.setVisibility(View.VISIBLE);
                precautions.setVisibility(View.VISIBLE);
                precautionsList.setVisibility(View.VISIBLE);
            } else {
                bmi_text.setText(getString(R.string.bmi_value_obese) + bmi);
                bmi_text.setTextColor(getResources().getColor(R.color.colorRedDark));
                improveBMI.setText(getString(R.string.decrease_bmi));
                improveBMIMethods.setText(getString(R.string.reduce_bmi_1) + lineBreak + getString(R.string.reduce_bmi_2) + lineBreak + getString(R.string.reduce_bmi_3) + lineBreak + getString(R.string.reduce_bmi_4));
                precautions.setText(getString(R.string.precautions));
                precautionsList.setText(getString(R.string.decrease_bmi_precaution));
                bmiLinearLayout.setVisibility(View.VISIBLE);
                precautions.setVisibility(View.VISIBLE);
                precautionsList.setVisibility(View.VISIBLE);
            }
        }
    }
}