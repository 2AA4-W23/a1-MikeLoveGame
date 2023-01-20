package pk;

import java.util.*;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class Player {

    private final Dice Dices[] = new Dice[8];
    private Faces faces[] = new Faces[Dices.length];
    private final Brain brain;
    private int score = 0;
    public static boolean traceMode=false;
    private int wins=0;
    private final String name;
    private static final Logger logger= LogManager.getLogger(Player.class.getName());


    public Player(String name) {
        this(name,"dead roll");
    }

    public Player(String name,String Strategy) {
        for (int i = 0; i < Dices.length; i++) {
            Dices[i] = new Dice();
            faces[i]= Dices[i].roll();
        }
        this.name=name;
        brain=new Brain(this,Strategy);
    }



    //Accessor Methods
    public int getWins(){
        return wins;
    }

    public String getName(){
        return this.name;
    }
    public Faces[] getFaces() {//create a deep copy
        Faces[] facescp= new Faces[faces.length];
        for (int i = 0; i < faces.length; i++) {
            facescp[i]=faces[i];
        }
        return facescp;
    }


    public int getScore() {
        return this.score;
    }

    public String getStrategy() {
        return brain.getStrategy();
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


    public void resetDice() {// fix when Dice all roll to skull case
        for (int i = 0; i < Dices.length; i++) {
            do{faces[i]= Dices[i].roll();}while(Faces.SKULL==faces[i]); // if reset to a skull, do it again
        }
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void deduceScore(int score){
        this.score -= score;
    }

    public void resetScore(){
        score=0;
    }
    public void addwin(){
        this.wins++;
    }
    public void clearWins(){
        this.wins=0;
    }

    public boolean ifEndRound() {


        return brain.ifendGame();

    }//not implemented yet, wrote for future features

    public void rollDice() {
        int num = numDiceToRoll();
        int count=0;
        int i = 0;

        while (count < num) {

            if (faces[i] != Faces.SKULL) {
                faces[i]=Dices[i].roll();
                count++;
                if(traceMode){
                    logger.log(Level.INFO, this.name+" Dice rolled:"+faces[i]);
                }
            }

            i++;
        }

    }

    public int numDiceToRoll() {
        Random r = new Random();
        int usableDice=Dices.length-getSkullCount();

        int num;

        num = brain.numDiceToRoll(usableDice);

        if(traceMode){
            logger.log( Level.INFO,"Player "+this.name+" decided to roll " + num+" dices");
        }
        return num;
    }






}
