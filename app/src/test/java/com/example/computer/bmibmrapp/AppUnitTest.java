package com.example.computer.bmibmrapp;

import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.hamcrest.number.IsCloseTo.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(JUnit4.class)
@SmallTest
public class AppUnitTest {

    private AppCalculations calculations;

    @Before
    public void setCalculations() {
        calculations = new AppCalculations();
    }

    @Test
    public void bmi_isCorrect() {
        double resultBMI = calculations.bmiCalculation(150d, 80d);
        assertThat(resultBMI, is(equalTo(35.56d)));
    }

    @Test
    public void bmr_female_isCorrect() {
        double resultBMR = calculations.bmrFemaleCalculation(170, 70, 32);
        assertThat(resultBMR, is(equalTo(1443.36)));
    }

    @Test
    public void bmr_male_isCorrect() {
        double result = calculations.bmrMaleCalculation(170, 80, 32);
        assertThat(result, is(equalTo(1709.26)));
    }
}