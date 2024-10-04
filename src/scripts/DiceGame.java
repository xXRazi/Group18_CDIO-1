public class DiceGame {
        private final Player player1;
        private final Player player2;
        private final Die die1;
        private final Die die2;

    public DiceGame(String name1, String name2) {
        this.player1 = new Player(name1, 0);
        this.player2 = new Player(name2, 0);
        this.die1 = new Die();
        this.die2 = new Die();
    }


    public void play() {
        boolean hasPlayerWon = false; 
        while (true) {
            if (hasPlayerWon) {
                gameEnds(player2, player1);
                break;
            }

            hasPlayerWon = playerTurn(player1);

            if (hasPlayerWon) {
                gameEnds(player1, player2);
                break;
            }

            hasPlayerWon = playerTurn(player2);
        }

        
    }


    private void rollDice() {
        die1.roll();
        die2.roll();
    }


    private boolean logic(Player player, int die1_value, int die2_value) {
        boolean isPlayerWon = false;
        boolean isEqual = die1_value == die2_value;
        int score = die1_value + die2_value;
        
        printDice(die1_value, die2_value, score);

        if (isEqual) {
            if (die1_value == 1 && die2_value == 1) {
                player.SetScore(0);
            }
            else {
                player.addToScore(score);
            }

            printScore(player);
            if (player.getScore() >= 40) {
                isPlayerWon = true;
            }
            else {
                rollDice();
                
                if (die1_value == 6 && die2_value == 6) {
                    if (die1.getFace() == 6 && die2.getFace() == 6) {
                        player.addToScore(score);
                        printDice(die1_value, die2_value, score);
                        printScore(player);
                        isPlayerWon = true;
                    }
                }
                else {
                    isPlayerWon = logic(player, die1.getFace(), die2.getFace());
                }
            }
        }

        if (!isPlayerWon && !isEqual) {
            player.addToScore(score);
            printScore(player);
        }

        return isPlayerWon;
    }


    public void gameEnds(Player winner, Player loser) {
        System.out.println("\n---------");
        System.out.println("Game over ");
        System.out.println("---------");
        System.out.println(winner.getName() + " Won!!!\n");
        System.out.println("1. " + winner.getName() + " got a score of: " + winner.getScore());
        System.out.println("2. " + loser.getName() + " got a score of: " + loser.getScore());
    }


    private boolean playerTurn(Player player) {
        System.out.println("\n" + player.getName() + "'s turn: ");
        rollDice();
        return logic(player, die1.getFace(), die2.getFace());
    }


    private void printDice(int value1, int value2, int sum) {
        System.out.println("\tdie1: " + value1);
        System.out.println("\tdie2: " + value2);
        System.out.println("\tSum: " + sum);
    }

    
    private void printScore(Player player) {
        System.out.println("\tScore: " + player.getScore());
        System.out.println("\t----------------------");
    }
}