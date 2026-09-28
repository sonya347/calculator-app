package com.example.calculator;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private String current = "";
    private double firstOperand = 0;
    private String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        int[] digitIds = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};
        for (int i = 0; i < digitIds.length; i++) {
            final String digit = String.valueOf(i);
            findViewById(digitIds[i]).setOnClickListener(v -> {
                current += digit;
                display.setText(current);
            });
        }

        findViewById(R.id.btnPlus).setOnClickListener(v -> setOperator("+"));
        findViewById(R.id.btnMinus).setOnClickListener(v -> setOperator("-"));
        findViewById(R.id.btnMul).setOnClickListener(v -> setOperator("*"));
        findViewById(R.id.btnDiv).setOnClickListener(v -> setOperator("/"));

        findViewById(R.id.btnEq).setOnClickListener(v -> calculate());
        findViewById(R.id.btnClear).setOnClickListener(v -> {
            current = "";
            firstOperand = 0;
            operator = "";
            display.setText("0");
        });
    }

    private void setOperator(String op) {
        if (!current.isEmpty()) {
            firstOperand = Double.parseDouble(current);
            operator = op;
            current = "";
        }
    }

    private void calculate() {
        if (current.isEmpty() || operator.isEmpty()) return;
        double second = Double.parseDouble(current);
        double result = 0;
        switch (operator) {
            case "+": result = firstOperand + second; break;
            case "-": result = firstOperand - second; break;
            case "*": result = firstOperand * second; break;
            case "/": result = second != 0 ? firstOperand / second : 0; break;
        }
        display.setText(String.valueOf(result));
        current = "";
        operator = "";
    }
}
