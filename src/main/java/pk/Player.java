package pk;

import java.util.*;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pk.card.*;
import pk.dice.*;
import pk.strategy.*;


public class Player {

    private final Dice Dices[] = new Dice[8];
    private Faces faces[] = new Faces[Dices.length];
    private final SmartStrategy smartStrategy;
    private int score = 0;
    public static boolean traceMode=false;
    private int wins=0;
    private final String name;
    private static final Logger logger= LogManager.getLogger(Player.class.getName());


    public Player(String name) {
        this(name,"dead roll");
    } //dead roll is the default strategy for player who
    //does not use their brain but relies on probability

    public Player(String name,String Strategy) {
        for (int i = 0; i < Dices.length; i++) {
            Dices[i] = new Dice();
            faces[i]= Dices[i].roll();
        }
        this.name=name;
        smartStrategy =new SmartStrategy(this,Strategy);
        this.resetDice();
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

    public static Hashtable<Faces, Integer> getFaceCount(Faces[] faces){

        int score = 0;//variables in lambda must be final
        Hashtable<Faces, Integer> faceList=new Hashtable();

        for (Faces face: faces) {
            if(face==Faces.None || face== Faces.SKULL){ //none don't count as face, but to dodge null pointer exception
                continue;
            }
            else if (!faceList.containsKey(face)){
                faceList.put(face,1);
            }
            else{
                faceList.put(face,(faceList.get(face)+1));
            }
        }


        return faceList;
    }


    public int getScore() {
        return this.score;
    }

    public String getStrategy() {
        return smartStrategy.getStrategy();
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

    public boolean ifEndRound(Card card) {
        if(smartStrategy.ifEndRound(card)&& traceMode==true){
            logger.log(Level.INFO, this.name+"decided to end round\n");
        }
        return smartStrategy.ifEndRound(card);
    }


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

    private void smartRoll(Card card){
        Integer[] dicesToRoll=this.smartStrategy.DicesToRoll(card);
        int i=0;
        String dicesChosen="dice number:";

        while(i<dicesToRoll.length && dicesToRoll[i]!=null){
            int x;
            x=dicesToRoll[i];
            dicesChosen+= Integer.toString(x+1)+" ";
            this.faces[x]=this.Dices[x].roll();
            i++;
        }
        if(traceMode) {
            logger.log(Level.INFO, "Player " + this.getName() + " decided to roll " + dicesChosen + " dices\n");
        }
    }

    public void rollDice(Card card){
        if(traceMode){
            logger.log(Level.INFO, " before rolling the dice, "+this.getName()+" hold following faces"+ Faces.toString(faces)+"\n");
        }
        if(this.smartStrategy.getStrategy()=="dead roll"){
            this.rollRandomDice();
        }
        else {
           this.smartRoll(card);
        }
        if(traceMode) {
            logger.log(Level.INFO, " after rolling the dice, " + this.getName() + " hold following faces" + Faces.toString(faces) + "\n");
        }
    }



    private static int numDiceToRoll(Player player) {
        Random r = new Random();
        int usableDice=player.Dices.length-player.getSkullCount();

        int num;

        num = player.smartStrategy.numDiceToRoll(usableDice);

        if(traceMode){
            logger.log( Level.INFO,"Player "+player.name+" decided to roll " + num+" dices\n");
        }

        return num;
    }

    public static Hashtable<Faces, Integer> getFaceCountMB(Faces[] faces) {

        int score = 0;//variables in lambda must be final
        Hashtable<Faces, Integer> faceList = new Hashtable();
        Faces[] newFaces=new Faces[faces.length];
        for (int i = 0; i < faces.length; i++) {
            if(faces[i]==Faces.PARROT){
                newFaces[i]=Faces.MONKEY;
            }
            else{
                newFaces[i]=faces[i];
            }
        }

        for (Faces face : newFaces) {
            if (face == Faces.None || face == Faces.SKULL) { //none don't count as face, but to dodge null pointer exception
                continue;
            } else if (!faceList.containsKey(face)) {
                faceList.put(face, 1);
            } else {
                faceList.put(face, (faceList.get(face) + 1));
            }
        }

        return faceList;
    }
    
}
