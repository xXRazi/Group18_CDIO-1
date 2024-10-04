public class DiceGame {
        private Player player1;
        private Player player2;
        private Die die1;
        private Die die2;

    public DiceGame(String name1, String name2) {
        this.player1 = new Player(name1, 0);
        this.player2 = new Player(name2, 0);
        this.die1 = new Die();
        this.die2 = new Die();
    }


    public void play() {
        boolean isGameRunning = true;
        boolean hasPlayerWon = false;
        while (isGameRunning) {
            if (hasPlayerWon) {
                gameEnds(player2.getName());
                break;
            }

            rollDice();
            hasPlayerWon = logic(player1, die1.getFace(), die2.getFace());

            if (hasPlayerWon) {
                gameEnds(player1.getName());
                break;
            }

            rollDice();
            hasPlayerWon = logic(player2, die1.getFace(), die2.getFace());
        }

        
    }


    private void rollDice() {
        die1.roll();
        die2.roll();
    }


    private boolean logic(Player player, int die1_value, int die2_value) {
        boolean isPlayerWon = false;
        int score = die1_value + die2_value;
        
        System.out.println("die 1: " + die1_value + " die 2: " + die2_value);
        System.out.println("Sum of roll: " + score);

        if (die1_value == die2_value) {
            if (die1_value == 1 && die2_value == 1) {
                player.SetScore(0);
            }
            if (die1_value == 6 && die2_value == 6) {
                rollDice();

                if (die1.getFace() == 6 && die2.getFace() == 6) {
                    isPlayerWon = true;
                }
            }
            if (player.getScore() >= 40) {
                isPlayerWon = true;
                
            }
            else {
                player.addToScore(score);
                rollDice();
                isPlayerWon = logic(player, die1.getFace(), die2.getFace());
            }
        }

        if (!isPlayerWon) {
            player.addToScore(score);
            System.out.println("player: " + player.getName() + " score is: " + player.getScore());
        }

        return isPlayerWon;
    }


    public void gameEnds(String winner) {
        System.out.println("Game over " + winner + " Won!!!");
    }
}