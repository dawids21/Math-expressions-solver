import java.util.ArrayDeque;

public class MathExpression {
    public static int solve(String expression) {
        if (expression == null) {
            throw new NullPointerException("Expression is null");
        }
        if (expression.length() == 0) {
            return 0;
        }

        var operandStack = new ArrayDeque<Integer>();
        var operatorStack = new ArrayDeque<Character>();
        var normalizedExpression = normalizeExpression(expression);
        var tokens = normalizedExpression.split("\\s+");
        for (var token : tokens) {
            if (token.length() == 0) {
                continue;
            }
            if (token.charAt(0) == '+' || token.charAt(0) == '-') {
                while (!operatorStack.isEmpty() && (operatorStack.peek() == '+' || operatorStack.peek() == '-' || operatorStack
                        .peek() == '*' || operatorStack.peek() == '/' || operatorStack.peek() == '%' || operatorStack.peek() == '^')) {
                    performOperation(operandStack, operatorStack);
                }
                operatorStack.push(token.charAt(0));
            } else if (token.charAt(0) == '*' || token.charAt(0) == '/' || token.charAt(0) == '%') {
                while (!operatorStack.isEmpty() && (operatorStack.peek() == '*' || operatorStack.peek() == '/' || operatorStack
                        .peek() == '%' || operatorStack
                        .peek() == '^')) {
                    performOperation(operandStack, operatorStack);
                }
                operatorStack.push(token.charAt(0));
            } else if (token.charAt(0) == '^') {
                while (!operatorStack.isEmpty() && operatorStack.peek() == '^') {
                    performOperation(operandStack, operatorStack);
                }
                operatorStack.push(token.charAt(0));
            } else if (token.charAt(0) == '(') {
                operatorStack.push('(');
            } else if (token.charAt(0) == ')') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    performOperation(operandStack, operatorStack);
                }
                operatorStack.pop();
            } else {
                try {
                    operandStack.push(Integer.parseInt(token));
                } catch (NumberFormatException e) {
                    operandStack.push(0);
                }
            }
        }
        while (!operatorStack.isEmpty()) {
            performOperation(operandStack, operatorStack);
        }
        return !operandStack.isEmpty() ? operandStack.pop() : 0;
    }

    private static void performOperation(ArrayDeque<Integer> operandStack, ArrayDeque<Character> operatorStack) {
        char operator = operatorStack.pop();
        int operand2 = operandStack.pop();
        int operand1 = operandStack.pop();
        switch (operator) {
            case '+':
                operandStack.push(operand1 + operand2);
                break;
            case '-':
                operandStack.push(operand1 - operand2);
                break;
            case '*':
                operandStack.push(operand1 * operand2);
                break;
            case '/':
                operandStack.push(operand1 / operand2);
                break;
            case '^':
                operandStack.push(power(operand1, operand2));
                break;
            case '%':
                operandStack.push(operand1 % operand2);
                break;
        }
    }

    private static int power(int base, int exponent) {
        if (exponent == 0 || base == 1) {
            return 1;
        }

        int result = 1;
        for (var i = 0; i < exponent; i++) {
            result *= base;
        }
        return result;
    }

    private static String normalizeExpression(String expression) {
        var stringBuilder = new StringBuilder();
        for (char ch : expression.replaceAll("[^\\d+-/*)(^%]", "").toCharArray()) {
            if (ch == '+' || ch == '-' || ch == '/' || ch == '*' || ch == '(' || ch == ')' || ch == '^' || ch == '%') {
                stringBuilder.append(" ").append(ch).append(" ");
            } else {
                stringBuilder.append(ch);
            }
        }
        return stringBuilder.toString();
    }
}
