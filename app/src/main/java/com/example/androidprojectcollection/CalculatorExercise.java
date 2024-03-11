package com.example.androidprojectcollection;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class CalculatorExercise extends AppCompatActivity {
    Calculator calculator = new Calculator();
    Stack<Float> operands = new Stack<>();
    Stack<String> operators = new Stack<>();
    TextInputLayout input;
    EditText inputtext;
    TextView tempanswer;

    Button btnAdd, btnSubtract, btnMultiply, btnDivide, btnEquals, btnPoint;
    Button btnNumber0, btnNumber1, btnNumber2,
            btnNumber3, btnNumber4, btnNumber5,
            btnNumber6, btnNumber7, btnNumber8, btnNumber9;
    Button btnAllClear;

    boolean pointActive = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_exercise);

        input = findViewById(R.id.input);
        inputtext = input.getEditText();
        tempanswer = findViewById(R.id.tempanswer);

        btnAllClear = findViewById(R.id.btnAllClear);

        setOperatorButtonClickListener(btnAllClear, "AC");

        btnNumber0 = findViewById(R.id.btnNumber0);
        btnNumber1 = findViewById(R.id.btnNumber1);
        btnNumber2 = findViewById(R.id.btnNumber2);
        btnNumber3 = findViewById(R.id.btnNumber3);
        btnNumber4 = findViewById(R.id.btnNumber4);
        btnNumber5 = findViewById(R.id.btnNumber5);
        btnNumber6 = findViewById(R.id.btnNumber6);
        btnNumber7 = findViewById(R.id.btnNumber7);
        btnNumber8 = findViewById(R.id.btnNumber8);
        btnNumber9 = findViewById(R.id.btnNumber9);

        setNumberButtonClickListener(btnNumber0, "0");
        setNumberButtonClickListener(btnNumber1, "1");
        setNumberButtonClickListener(btnNumber2, "2");
        setNumberButtonClickListener(btnNumber3, "3");
        setNumberButtonClickListener(btnNumber4, "4");
        setNumberButtonClickListener(btnNumber5, "5");
        setNumberButtonClickListener(btnNumber6, "6");
        setNumberButtonClickListener(btnNumber7, "7");
        setNumberButtonClickListener(btnNumber8, "8");
        setNumberButtonClickListener(btnNumber9, "9");

        btnAdd = findViewById(R.id.btnAdd);
        btnSubtract = findViewById(R.id.btnSubtract);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnDivide = findViewById(R.id.btnDivide);
        btnEquals = findViewById(R.id.btnEquals);
        btnPoint = findViewById(R.id.btnPoint);

        setOperatorButtonClickListener(btnAdd, "+");
        setOperatorButtonClickListener(btnSubtract, "-");
        setOperatorButtonClickListener(btnMultiply, "x");
        setOperatorButtonClickListener(btnDivide, "/");
        setOperatorButtonClickListener(btnEquals, "=");
        setOperatorButtonClickListener(btnPoint, ".");
    }

    private void setNumberButtonClickListener(Button button, final String number) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // First integer will always be pushed immediately
                if(operands.isEmpty()){
                    operands.push((float) Integer.parseInt(number));
                }
                // A valid expression now exists (e.g. 5 + 3)
                else if(operands.size() > operators.size()){
                    float currentTop = operands.pop();
                    // If there are no decimal points
                    if(!pointActive){
                        currentTop = currentTop * 10 + Integer.parseInt(number);
                    }
                    else {
                        double frac = Double.parseDouble(convertToDecimalForm(Double.parseDouble(number)));
                        currentTop += frac;
                    }
                    operands.push(currentTop);
                }
                else if(operands.size() == operators.size())
                    operands.push((float) Integer.parseInt(number));

                inputtext.append(number);
                float result = calculator.calculate(operands, operators);
                if(hasDecimal(result))
                    tempanswer.setText("" + result);
                else tempanswer.setText("" + (int) result);
            }
        });
    }

    private void setOperatorButtonClickListener(Button button, final String operator) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(operands.isEmpty())
                    return;
                switch(operator){
                    case "AC":
                        inputtext.setText("");
                        tempanswer.setText("");
                        operands.clear();
                        operators.clear();
                        pointActive = false;
                        break;
                    case "=":
//                        Context context = getApplicationContext();
//                        String debugMessage = "Operands: " + operands + ", Operators: " + operators;
//                        Toast.makeText(context, debugMessage, Toast.LENGTH_SHORT).show();
                        if(checkOpEquality())
                            // Remove last operator if last character is an operator
                            operators.pop();
                        float result = calculator.calculateEMDAS(operands, operators);
                        if(hasDecimal(result)){
                            inputtext.setText("" + result);
                            tempanswer.setText("" + result);
                        } else {
                            inputtext.setText("" + (int) result);
                            tempanswer.setText("" + (int) result);
                        }
                        break;
                    case ".":
                        if(!pointActive){
                            inputtext.append(operator);
                            pointActive = true;
                        }
                        break;
                    default:
                        if(operators.size() == operands.size()){
                            inputtext.getText().delete(inputtext.length() - 1, inputtext.length());
                            operators.pop();
                        }
                        pointActive = false;
                        inputtext.append(operator);
                        operators.push(operator);
                }
            }
        });
    }

    public static String convertToDecimalForm(double value) {
        if (value == (int) value) {
            return "0." + ((int) value);
        } else {
            int numberOfDigits = String.valueOf(value).length() - (String.valueOf(value).indexOf('.') + 1);
            int denominator = (int) Math.pow(10, numberOfDigits);
            double result = value / denominator;
            return String.valueOf(result);
        }
    }

    public boolean checkOpEquality() {
            return operands.size() == operators.size();
    }

    public static boolean hasDecimal(float value) {
        return value % 1 != 0;
    }
}

