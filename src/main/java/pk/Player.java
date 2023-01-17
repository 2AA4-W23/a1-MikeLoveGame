package pk;

import java.util.Arrays;
import java.util.Random;

public class Player {
    private final Dice Dices[] = new Dice[8];
    private Faces faces[] = new Faces[Dices.length];
    private int score = 0;

    private int skullCount = 0;
    private final String Strategy;

    public Player() {
        this("dead roll");
    }

    public Player(String Strategy) {
        for (int i = 0; i < Dices.length; i++) {
            Dices[i] = new Dice();
        }
        this.Strategy = Strategy;
    }

    public int numDiceToRoll() {
        Random r = new Random();
        int count = 0;
        for (Faces face : faces) {
            if (face == Faces.SKULL) {
                count++;
            }
        }
        int usableDice = Dices.length - count;
        int num = r.nextInt(usableDice + 1);
        return (num);
    }


    //Accessor Methods


    public Faces[] getFaces() {
        return this.faces;
    }
    

    public int getScore() {
        return this.score;
    }

    public String getStrategy() {
        return this.Strategy;
    }

    //Mutator Methods


    public void resetDice() {
        Arrays.fill(faces, null);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setStrategy(String strategy) {
        this.Strategy = strategy;
    }

    public boolean ifEndRound() {
        return false;
    }//not implemented yet, wrote for future features

    public void rollDice() {
        int num = numDiceToRoll();
        int i = 0;

        while (i < num) {
            if (faces[i] == Faces.SKULL) {
                continue;

            }
            Dices[i].roll();
            i++;
        }
    }

}
