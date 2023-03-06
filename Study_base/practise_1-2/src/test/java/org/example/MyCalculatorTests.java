package org.example;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyCalculatorTests {
    @Test
    @DisplayName("testing multiplication operations (MyCalculator.multiply)")
    void testMultiplication() {
        assertTrue(MyCalculator.multiply(2.0, 3.0) == 6.0);
    }

    @Test
    @DisplayName("testing division operations (MyCalculator.divide)")
    void testDivision() {
        assertTrue(MyCalculator.divide(6.0, 3.0) == 2.0);
    }

    @Test
    @DisplayName("testing addition operations (MyCalculator.add)")
    void testAddition() {
        assertTrue(MyCalculator.add(6.0, 3.0) == 9.0);
    }

    @Test
    @DisplayName("testing subtraction operations (MyCalculator.subtract)")
    void testSubtraction() {
        assertTrue(MyCalculator.subtract(6.0, 3.0) == 3.0);
    }
}