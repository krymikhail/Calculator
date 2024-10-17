package com.geeks.calculator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    private TextView resultText;
    private String currentInput = "";
    private String operator = "";
    private double firstNumber = 0;
    private boolean isOperatorPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultText = findViewById(R.id.result_text);

        MaterialButton buttonAC = findViewById(R.id.button_ac);
        MaterialButton buttonPlusMinus = findViewById(R.id.button_plus_minus);
        MaterialButton buttonPercent = findViewById(R.id.button_percent);
        MaterialButton buttonDivide = findViewById(R.id.button_divide);
        MaterialButton buttonMultiply = findViewById(R.id.button_multiply);
        MaterialButton buttonMinus = findViewById(R.id.button_minus);
        MaterialButton buttonPlus = findViewById(R.id.button_plus);
        MaterialButton buttonEquals = findViewById(R.id.button_equals);
        MaterialButton buttonDot = findViewById(R.id.button_dot);

        MaterialButton[] numberButtons = new MaterialButton[10];
        numberButtons[0] = findViewById(R.id.button_0);
        numberButtons[1] = findViewById(R.id.button_1);
        numberButtons[2] = findViewById(R.id.button_2);
        numberButtons[3] = findViewById(R.id.button_3);
        numberButtons[4] = findViewById(R.id.button_4);
        numberButtons[5] = findViewById(R.id.button_5);
        numberButtons[6] = findViewById(R.id.button_6);
        numberButtons[7] = findViewById(R.id.button_7);
        numberButtons[8] = findViewById(R.id.button_8);
        numberButtons[9] = findViewById(R.id.button_9);

        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialButton button = (MaterialButton) v;
                onNumberClick(button.getText().toString());
            }
        };

        for (MaterialButton button : numberButtons) {
            button.setOnClickListener(numberClickListener);
        }

        buttonAC.setOnClickListener(v -> resetCalculator());
        buttonPlusMinus.setOnClickListener(v -> toggleSign());
        buttonPercent.setOnClickListener(v -> applyPercentage());
        buttonDivide.setOnClickListener(v -> onOperatorClick("÷"));
        buttonMultiply.setOnClickListener(v -> onOperatorClick("X"));
        buttonMinus.setOnClickListener(v -> onOperatorClick("-"));
        buttonPlus.setOnClickListener(v -> onOperatorClick("+"));
        buttonEquals.setOnClickListener(v -> onEqualClick());
        buttonDot.setOnClickListener(v -> onDecimalClick());
    }

    private void onNumberClick(String number) {
        if (isOperatorPressed) {
            currentInput = "";
            isOperatorPressed = false;
        }
        currentInput += number;
        resultText.setText(currentInput);
    }

    private void onOperatorClick(String selectedOperator) {
        if (!currentInput.isEmpty()) {
            firstNumber = Double.parseDouble(currentInput);
        }
        operator = selectedOperator;
        isOperatorPressed = true;
    }

    private void onEqualClick() {
        if (!currentInput.isEmpty()) {
            double secondNumber = Double.parseDouble(currentInput);
            double result = 0;
            switch (operator) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "X":
                    result = firstNumber * secondNumber;
                    break;
                case "÷":
                    if (secondNumber != 0) {
                        result = firstNumber / secondNumber;
                    } else {
                        resultText.setText("Error");
                        return;
                    }
                    break;
            }

            if (result == (int) result) {
                resultText.setText(String.valueOf((int) result));
            } else {
                resultText.setText(String.valueOf(result));
            }
            currentInput = String.valueOf(result);
        }
    }

    private void onDecimalClick() {
        if (!currentInput.contains(".")) {
            currentInput += ".";
            resultText.setText(currentInput);
        }
    }

    private void resetCalculator() {
        currentInput = "";
        operator = "";
        firstNumber = 0;
        resultText.setText("0");
    }

    private void toggleSign() {
        if (!currentInput.isEmpty()) {
            double value = Double.parseDouble(currentInput);
            value = -value;
            currentInput = String.valueOf(value);
            resultText.setText(currentInput);
        }
    }

    private void applyPercentage() {
        if (!currentInput.isEmpty()) {
            double value = Double.parseDouble(currentInput);
            value = value / 100;
            currentInput = String.valueOf(value);
            resultText.setText(currentInput);
        }
    }
}