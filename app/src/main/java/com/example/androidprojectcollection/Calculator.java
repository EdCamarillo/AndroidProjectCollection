package com.example.androidprojectcollection;

import java.util.List;
import java.util.Stack;

public class Calculator {
    public static float calculate(Stack<Float> operands, Stack<String> operators) {
        Stack<Float> operandsCopy = new Stack<>();
        Stack<String> operatorsCopy = new Stack<>();

        // Create copies of the original stacks
        operandsCopy.addAll(operands);
        operatorsCopy.addAll(operators);
        operatorsCopy = reverseStack(operatorsCopy);
        operandsCopy = reverseStack(operandsCopy);

        // Perform calculations using the copies
        float result = calculateInternal(operandsCopy, operatorsCopy);

        // The original stacks remain unchanged
        return result;
    }

    private static <T> Stack<T> reverseStack(Stack<T> stack) {
        Stack<T> reversedStack = new Stack<>();
        while (!stack.isEmpty()) {
            reversedStack.push(stack.pop());
        }
        return reversedStack;
    }

    public static float calculateInternal(Stack<Float> operands, Stack<String> operators) {
        Stack<Float> resultStack = new Stack<>();

        while (!operators.isEmpty()) {
            float operand1 = operands.pop();
            float operand2 = operands.pop();
            String operator = operators.pop();

            float result = performSingleOperation(operand1, operand2, operator);
            operands.push(result);
        }

        if (!operands.isEmpty()) {
            resultStack.push(operands.pop());
        }

        // Copy the results back to the original operands stack
        operands.addAll(resultStack);

        // Return the final result
        return operands.peek();
    }

    public static float calculateEMDAS(Stack<Float> operands, Stack<String> operators) {
        List<String> expression = combineIntoExpressionList(operands, operators);

        Stack<Float> tempOperands = new Stack<>();
        Stack<String> tempOperators = new Stack<>();

        for (String token : expression) {
            if (isNumeric(token)) {
                tempOperands.push(Float.valueOf(token));
            } else if (isOperator(token)) {
                while (!tempOperators.isEmpty() && precedence(tempOperators.peek()) >= precedence(token)) {
                    performOperation(tempOperands, tempOperators);
                }
                tempOperators.push(token);
            }
        }

        while (!tempOperators.isEmpty()) {
            performOperation(tempOperands, tempOperators);
        }

        // Update the original stacks with the final result
        operands.clear();
        operands.push(tempOperands.pop());

        return operands.peek();
    }

    private static List<String> combineIntoExpressionList(Stack<Float> operands, Stack<String> operators) {
        List<String> expression = new java.util.ArrayList<>(operands.size() + operators.size());

        // Add operands and operators to the list alternately
        while (!operands.isEmpty() || !operators.isEmpty()) {
            if (!operands.isEmpty()) {
                expression.add(String.valueOf(operands.pop()));
            }
            if (!operators.isEmpty()) {
                expression.add(operators.pop());
            }
        }

        // Reverse the list to maintain the original order
        java.util.Collections.reverse(expression);

        return expression;
    }

    private static void performOperation(Stack<Float> operands, Stack<String> operators) {
        float operand2 = operands.pop();
        float operand1 = operands.pop();
        String operator = operators.pop();

        float result = performSingleOperation(operand1, operand2, operator);
        operands.push(result);
    }

    private static float performSingleOperation(float operand1, float operand2, String operator) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "x":
                return operand1 * operand2;
            case "/":
                if (operand2 != 0) {
                    return operand1 / operand2;
                } else {
                    throw new ArithmeticException("Cannot divide by zero.");
                }
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?"); // Check if the string is a valid number
    }

    private static boolean isOperator(String str) {
        return str.matches("[+\\-x/]"); // Check if the string is one of the supported operators
    }

    private static int precedence(String operator) {
        switch (operator) {
            case "+":
            case "-":
                return 1;
            case "x":
            case "/":
                return 2;
            default:
                return 0; // Default precedence for unknown operators
        }
    }
}