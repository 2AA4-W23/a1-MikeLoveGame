package pk;

import java.util.Arrays;
import java.util.Random;

public class Player {
    private final Dice Dices[] = new Dice[8];
    private Faces faces[] = new Faces[Dices.length];
    private int score = 0;

    private int wins=0;

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



    //Accessor Methods
    public int getWins(){
        return wins;
    }

    public Faces[] getFaces() {
        return this.faces;
    }


    public int getScore() {
        return this.score;
    }

    public String getStrategy() {
        return this.Strategy;
    }

    public int getSkullCount(){
        int skullCount=0;
        for (Faces face: faces) {
            if(face==Faces.SKULL){
                skullCount++;
            }
        }
        return skullCount;
    }
    //Mutator Methods


    public void resetDice() {
        Arrays.fill(faces, null);
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void deduceScore(int score){
        this.score -= score;
    }
    public void addwin(){
        this.wins++;
    }
    public void clearWins(){
        this.wins=0;
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

    public int numDiceToRoll() {
        Random r = new Random();
        int usableDice=Dices.length-getSkullCount();

        int num = r.nextInt(usableDice-1)+2;
        return (num);
    }



}
