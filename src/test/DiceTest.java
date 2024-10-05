import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

class DiceTest {
    
    @Test
    void normalDistributionTest() {
        int TOTALROLLS = 100000000;
        Die d1 = new Die();
        Die d2 = new Die();
        int roll1;
        int roll2;
        var finalArray = new double[11];
        // Using BigDecimal here to round down to two digits
        BigDecimal[] distribution = new BigDecimal[11];
        Arrays.fill(distribution, BigDecimal.ZERO);
        BigDecimal divisor = new BigDecimal(TOTALROLLS);
        BigDecimal increment = new BigDecimal(1);

        // Theoretical distrution of the sum of two die from https://www.omnicalculator.com/statistics/dice
        var theoreticalDistribution = new double[] {0.027, 0.055, 0.083, 0.111, 0.138, 0.166, 0.138, 0.111, 0.083, 0.055, 0.027};

        for (var i = 0; i < TOTALROLLS; i++) {
            d1.roll();
            roll1 = d1.getFace();
            d2.roll();
            roll2 = d2.getFace();
            switch (roll1+roll2) {
                case 2 -> distribution[0] = distribution[0].add(increment);
                case 3 -> distribution[1] = distribution[1].add(increment);
                case 4 -> distribution[2] = distribution[2].add(increment);
                case 5 -> distribution[3] = distribution[3].add(increment);
                case 6 -> distribution[4] = distribution[4].add(increment);
                case 7 -> distribution[5] = distribution[5].add(increment);
                case 8 -> distribution[6] = distribution[6].add(increment);
                case 9 -> distribution[7] = distribution[7].add(increment);
                case 10 -> distribution[8] = distribution[8].add(increment);
                case 11 -> distribution[9] = distribution[9].add(increment);
                case 12 -> distribution[10] = distribution[10].add(increment);
            }
        }
        // Copying over to finalArray because assertArrayEquals doesn't accept BigDecimal
        for (var i = 0; i < finalArray.length; i++) {
            distribution[i] = distribution[i].divide(divisor).setScale(3, RoundingMode.DOWN);
            finalArray[i] = distribution[i].doubleValue();
        }
        assertArrayEquals(theoreticalDistribution, finalArray);
    }

    @Test
    void equalsTest() {
        int TOTALROLLS = 100000000;
        Die d1 = new Die();
        Die d2 = new Die();
        int roll1;
        int roll2;
        // Same as above
        BigDecimal amountEquals = new BigDecimal(0);
        BigDecimal divisor = new BigDecimal(TOTALROLLS);
        BigDecimal increment = new BigDecimal(1);
        double theoreticalAmount = 0.167;

        for (var i = 0; i < TOTALROLLS; i++) {
            d1.roll();
            roll1 = d1.getFace();
            d2.roll();
            roll2 = d2.getFace();
            if (roll1 == roll2) {
                amountEquals = amountEquals.add(increment);
            }
        }
        
        amountEquals = amountEquals.divide(divisor).setScale(3, RoundingMode.CEILING);

        assertEquals(theoreticalAmount, amountEquals.doubleValue());
    }

    //Using JUnit's Timeout annotation to check whether the code inside the test runs in under 333 ms
    @Test
    @Timeout(value = 333,unit = TimeUnit.MILLISECONDS)
    void performanceTest() {
        Die d1 = new Die();
        Die d2 = new Die();

        d1.roll();
        var roll1 = d1.getFace();

        d2.roll();
        var roll2 = d2.getFace();
    }

}