// Created by Dayu Wang (dwang@stchas.edu) on 2022-09-21

// Last updated by Dayu Wang (dwang@stchas.edu) on 2022-09-21


package evening.lecture_5;

import java.util.Scanner;

public class Algorithms {
    /** Reverses an array using a stack.
        @param arr: array to reverse
    */
    public static void reverse(int[] arr) {
        LinkedListStack<Integer> stk = new LinkedListStack<>();
        for (int val : arr) { stk.push(val); }
        for (int j = 0; j < arr.length; j++) { arr[j] = stk.pop(); }
    }  // Time complexity: O(n)

    /** Tests whether a string is a palindrome using a stack.
        @param str: string to test
        @return: {true} if the string is palindromic; {false} otherwise
    */
    public static boolean isPalindromic(String str) {
        LinkedListStack<Character> stk = new LinkedListStack<>();
        for (int i = 0; i < str.length(); i++) { stk.push(str.charAt(i)); }
        StringBuilder reversedStr = new StringBuilder();
        while (!stk.isEmpty()) { reversedStr.append(stk.pop()); }
        return reversedStr.toString().equals(str);
    }  // Time complexity: O(n)

    /** Tests whether parentheses are balanced in an expression.
        @param exp: expression to test
        @return: {true} if parentheses are balanced in the expression; {false} otherwise
    */
    public static boolean isBalanced(String exp) {
        LinkedListStack<Character> stk = new LinkedListStack<>();
        for (int i = 0; i < exp.length(); i++) {
            if (exp.charAt(i) == '(' || exp.charAt(i) == '[' || exp.charAt(i) == '{') { stk.push(exp.charAt(i)); }
            if (exp.charAt(i) == ')' || exp.charAt(i) == ']' || exp.charAt(i) == '}') {
                if (stk.isEmpty()) { return false; }
                if (exp.charAt(i) == ')' && stk.peek() != '(') { return false; }
                if (exp.charAt(i) == ']' && stk.peek() != '[') { return false; }
                if (exp.charAt(i) == '}' && stk.peek() != '{') { return false; }
                stk.pop();
            }
        }
        return stk.isEmpty();
    }  // Time complexity: O(n)

    /** Evaluates a postfix expression using a stack.
        @param postfixExp: postfix expression to evaluate
        @return: evaluation result
        @throws ArithmeticException: divide-by-zero
    */
    public static int evaluate(String postfixExp) {
        LinkedListStack<Integer> stk = new LinkedListStack<>();
        Scanner scanner = new Scanner(postfixExp);
        while (scanner.hasNext()) {
            String token = scanner.next();
            if (Character.isDigit(token.charAt(0))) { stk.push(Integer.valueOf(token)); }
            else {
                int rightOperand = stk.pop(), leftOperand = stk.pop();
                // Supported operators
                if (token.equals("+")) { stk.push(leftOperand + rightOperand); }
                if (token.equals("-")) { stk.push(leftOperand - rightOperand); }
                if (token.equals("*")) { stk.push(leftOperand * rightOperand); }
                if (token.equals("/")) {
                    if (rightOperand == 0) {
                        scanner.close();
                        throw new ArithmeticException("Dividing by zero");
                    }
                    stk.push(leftOperand / rightOperand);
                }
            }
        }
        scanner.close();
        return stk.pop();
    }  // Time complexity: O(n)

    /** Returns the precedence of an operator.
        @param oper: operator to find its precedence
        @return: precedence of the operator
        @throws IllegalArgumentException: operator is not supported.
    */
    private static int precedence(String oper) {
        if (oper.equals("*") || oper.equals("/")) { return 12; }
        if (oper.equals("+") || oper.equals("-")) { return 11; }
        throw new IllegalArgumentException("Operator not supported");
    }

    /** Converts an infix expression to postfix expression.
        @param infixExp: infix expression to convert
        @return: result postfix expression
    */
    public static String infixToPostfix(String infixExp) {
        LinkedListStack<String> stk = new LinkedListStack<>();
        StringBuilder postfix = new StringBuilder();
        Scanner scanner = new Scanner(infixExp);
        while (scanner.hasNext()) {
            String token = scanner.next();
            if (Character.isDigit(token.charAt(0))) { postfix.append(token).append(' '); }
            else if (token.equals("(")) { stk.push(token); }
            else if (token.equals(")")) {
                while (!stk.peek().equals("(")) { postfix.append(stk.pop()).append(' '); }
                stk.pop();
            } else {
                while (!stk.isEmpty() && !stk.peek().equals("(") && precedence(token) <= precedence(stk.peek())) {
                    postfix.append(stk.pop()).append(' ');
                }
                stk.push(token);
            }
        }
        while (!stk.isEmpty()) { postfix.append(stk.pop()).append(' '); }
        scanner.close();
        return postfix.toString();
    }  // Time complexity: O(n)
}