public class Player {
    private final String playerName;
    private int playerScore;

    public Player(String name, int score) {
        this.playerName = name;
        this.playerScore = score;
    }

    
    public String getName() {
        return playerName;
    }


    public int getScore() {
        return playerScore;
    }
    

    public void SetScore(int newScore) {
        this.playerScore = newScore;
    }


    public void addToScore(int addScore) {
        this.playerScore += addScore;
    }
}