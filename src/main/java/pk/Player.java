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
    } //dead roll is the default strategy for player

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
        if(brain.ifEndRound()&& traceMode==true){
            logger.log(Level.INFO, this.name+"decided to end round\n");
        }
        return brain.ifEndRound();

    }//not implemented yet, wrote for future features

    private void rollRandomDice() {
        int num = numDiceToRoll(this);
        int count=0;
        int i = 0;

        while (count < num) {

            if (faces[i] != Faces.SKULL) {
                faces[i]=Dices[i].roll();
                count++;

            }
            i++;
        }
    }

    private void smartRoll(){
        Integer[] dicesToRoll=this.brain.DicesToRoll();
        int i=0;
        if(dicesToRoll==null){ //this is added because if a smart player gets a magnificent hand in the first round,
            //it would be asked to end the game but roll the dice,
            // then it will be asked to how many will roll, if hte answer belows 2 it has a bug to fix
            return;
        }
        while(i<dicesToRoll.length && dicesToRoll[i]!=null ){
            int x=dicesToRoll[i];
            this.faces[x]=this.Dices[x].roll();
            i++;
        }
    }

    public void rollDice(){
        logger.log(Level.INFO, " before rolling the dice, "+this.getName()+" hold following faces"+ Faces.toString(faces)+"\n");
        if(this.brain.getStrategy()=="dead roll"){
            this.rollRandomDice();
        }
        else if(this.brain.getStrategy()=="smart"){
           this.smartRoll();
        }
        logger.log(Level.INFO, " after rolling the dice, "+this.getName()+" hold following faces"+ Faces.toString(faces)+"\n");
    }

    private static int numDiceToRoll(Player player) {
        Random r = new Random();
        int usableDice=player.Dices.length-player.getSkullCount();

        int num;

        num = player.brain.numDiceToRoll(usableDice);

        if(traceMode){
            logger.log( Level.INFO,"Player "+player.name+" decided to roll " + num+" dices\n");
        }

        return num;
    }






}
