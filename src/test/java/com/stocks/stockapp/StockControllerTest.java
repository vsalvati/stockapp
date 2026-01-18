package com.stocks.stockapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class StockControllerTest {

    @Test
    public void testCalculationLogic() {
        double price = 100.00;
        
        assertEquals(101.0, calculate(price, 1.01));
        assertEquals(100.8, calculate(price, 1.008));
        assertEquals(98.0, calculate(price, 0.98));
    }

    private double calculate(double price, double factor) {
        return BigDecimal.valueOf(price * factor)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
