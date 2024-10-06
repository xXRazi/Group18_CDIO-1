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
            // Player 1 turn
            hasPlayerWon = playerTurn(player1);
            if (hasPlayerWon) {
                gameEnds(player1, player2);
                break;
            }

            // Player 2 turn
            hasPlayerWon = playerTurn(player2);
            if (hasPlayerWon) {
                gameEnds(player2, player1);
                break;
            }
        }        
    }


    // Rolls dice
    private void rollDice() {
        die1.roll();
        die2.roll();
    }


    // Controls what happens after a player has rolled
    private boolean logic(Player player, int die1_value, int die2_value) {
        boolean isPlayerWon = false;
        boolean isEqual = die1_value == die2_value;
        int score = die1_value + die2_value;
        
        printDice(die1_value, die2_value, score);

        // Controls what happens if the dice is a par
        if (isEqual) {
            // Checks if dice is a par of 1's
            if (die1_value == 1 && die2_value == 1) {
                player.SetScore(0);
            }
            else {
                player.addToScore(score);
            }

            printScore(player);
            // Checks if player won
            if ((player.getScore() - score) >= 40) {
                isPlayerWon = true;
            }
            // Rolls dice again because there was rolled a par
            else {
                rollDice();
                
                // Checks if there was rolled a par of 6 two times in a row
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
        else {
            player.addToScore(score);
            printScore(player);
        }

        return isPlayerWon;
    }


    // Displays end game results
    private void gameEnds(Player winner, Player loser) {
        System.out.println("\n---------");
        System.out.println("Game over ");
        System.out.println("---------");
        System.out.println(winner.getName() + " Won!!!\n");
        System.out.println("1. " + winner.getName() + " got a score of: " + winner.getScore());
        System.out.println("2. " + loser.getName() + " got a score of: " + loser.getScore());
    }


    // Controls the flow of a players turn
    private boolean playerTurn(Player player) {
        System.out.println("\n" + player.getName() + "'s turn: ");
        rollDice();
        return logic(player, die1.getFace(), die2.getFace());
    }


    // Displays the dice
    private void printDice(int value1, int value2, int sum) {
        System.out.println("\tdie1: " + value1);
        System.out.println("\tdie2: " + value2);
        System.out.println("\tSum: " + sum);
    }

    
    // Displays a players score
    private void printScore(Player player) {
        System.out.println("\tScore: " + player.getScore());
        System.out.println("\t----------------------");
    }
}