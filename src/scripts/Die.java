import java.util.Random;


public class Die {
    private final Random rand;
    private int faceValue;

    public Die() {
        this.rand = new Random();
        this.faceValue = rand.nextInt(6) + 1;
    }


    public int getFace() {
        return faceValue;
    }


    public void roll() {
        this.faceValue = rand.nextInt(6) + 1;
    }
}