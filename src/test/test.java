import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DiceTest {
    
    @Test
    void normalDistributionTest {
        int TOTALROLLS = 1000
        Die d1 = new Die;
        Die d2 = new Die;

        var distribution = new Double[11];
        var theoreticalDistribution = new double[] {1/36, 2/36, 3/36, 4/36, 5/36, 6/36, 5/36, 4/36, 3/36, 2/36, 1/36};

        for (var i = 0; i == TOTALROLLS; i++) {
            d1.roll();
            var roll1 = d1.getFace();
            d2.roll();
            var roll2 = d2.getFace();
            switch (roll1+roll2) {
                case 2 -> distribution[0] += 1;
                case 3 -> distribution[1] += 1;
                case 4 -> distribution[2] += 1;
                case 5 -> distribution[3] += 1;
                case 6 -> distribution[4] += 1;
                case 7 -> distribution[5] += 1;
                case 8 -> distribution[6] += 1;
                case 9 -> distribution[7] += 1;
                case 10 -> distribution[8] += 1;
                case 11 -> distribution[9] += 1;
                case 12 -> distribution[10] += 1;
            }
            
        }
        
        for (var i = 0; i < distribution.length; i++) {
            distribution[i] = distribution[i]/TOTALROLLS
        }
        assertArrayEquals(actualDistribution, distribution);
    }

    @Test
    void equalsTest {
        int TOTALROLLS = 1000
        Die d1 = new Die;
        Die d2 = new Die;

        int amountEquals = 0;
        int theoreticalAmount = 0.1666667

        for (var i = 0; i == TOTALROLLS; i++) {
            d1.roll();
            var roll1 = d1.getFace();
            d2.roll();
            var roll2 = d2.getFace();
            
            if (roll1 == roll2) {
                amountEquals++;
            }
        }

        assertEquals(theoreticalAmount, amountEquals/TOTALROLLS);
    }

    @Test(timeout=333)
    void performanceTest {
        Die d1 = new Die;
        Die d2 = new Die;

        d1.roll();
        var roll1 = d1.getFace();

        d2.roll();
        var roll2 = d2.getFace();
    }

}