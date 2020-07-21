import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MathExpressionTest {

    @Test
    void should_evaluate_expressions_with_addition() {
        assertEquals(9, MathExpression.solve("4 + 5"));
    }

    @Test
    void should_evaluate_expressions_with_subtraction() {
        assertEquals(224, MathExpression.solve("235 - 11"));
    }

    @Test
    void should_evaluate_expressions_with_multiplication() {
        assertEquals(42, MathExpression.solve("6 * 7"));
    }

    @Test
    void should_evaluate_expressions_with_division() {
        assertEquals(3, MathExpression.solve("9 / 3"));
    }

    @Test
    void should_preserve_operations_precedence() {
        assertEquals(7, MathExpression.solve("2 + 3 * 6 - 26 / 2"));
    }

    @Test
    void should_evaluate_expressions_with_parentheses() {
        assertEquals(6, MathExpression.solve("19 - (3 + 2 * 5)"));
    }

    @Test
    void should_evaluate_expression_with_exponent() {
        assertEquals(27, MathExpression.solve("3 ^ 3"));
    }

    @Test
    void should_evaluate_expression_with_modulo() {
        assertEquals(5, MathExpression.solve("23 % 6"));
    }

    @Test
    void should_evaluate_mixed_expressions() {
        assertAll(() -> assertEquals(3, MathExpression.solve("1+2")),
                  () -> assertEquals(3, MathExpression.solve("1 +        2")),
                  () -> assertEquals(3, MathExpression.solve("(1)+     2")),
                  () -> assertEquals(3, MathExpression.solve("((1))+    2")),
                  () -> assertEquals(3, MathExpression.solve("(1 +      2)")),
                  () -> assertEquals(321, MathExpression.solve("51+(54*(3+2))")),
                  () -> assertEquals(9, MathExpression.solve("(1+2)*4-3")),
                  () -> assertEquals(95, MathExpression.solve("3 + (4 + 5) * (3 + 5) + 4 * 5")),
                  () -> assertEquals(559, MathExpression.solve("4 + 5 5 5")),
                  () -> assertEquals(160, MathExpression.solve("(5 * 2 ^ 3 + 2 * 3 % 2) * 4")));
    }
}