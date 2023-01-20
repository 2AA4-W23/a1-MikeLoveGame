package pk;

import java.util.*;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class Player {

    private final Dice Dices[] = new Dice[8];
    private Faces faces[] = new Faces[Dices.length];
    private int score = 0;

    public static boolean traceMode=false;
    private int wins=0;
    private final String Strategy;
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
        this.Strategy = Strategy;
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

        if(Strategy.equals("dead roll")){
            return false;
        }
        if(Strategy.equals("smart")){
            if(score(faces)>=2000){
                return true;
            }
        }
        return false;

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

        int num=usableDice;

        if (Objects.equals(Strategy, "dead roll")){
            num = r.nextInt(usableDice-2)+2;
        }

        if(traceMode){
            logger.log( Level.INFO,"Player "+this.name+" decided to roll " + num+" dices");
        }
        return num;
    }

    private static boolean winner(Player player){
        if(player.score>=6000){
            player.addwin();
            return true;
        }
        return false;
    }
    public static void pkGame(Player[] players, int numGames, boolean traceMode){

        int gameCount=0;
        Player.traceMode=traceMode;


        while(gameCount<numGames){
            boolean endround=false;

            for (Player player: players) {
                player.resetScore();
                player.resetDice();
            }

            while(!endround) {
                for (Player player : players) {
                    round(player);
                    if (winner(player)) {
                        endround=true;
                        break;
                    }
                }
            }
            gameCount++;
        }

    }

    private static void round(Player player)  {

        int skullCount = 0;

        boolean endRound = false;

        Faces[] faces;

        while (!endRound) {
            player.rollDice();

            endRound=player.ifEndRound();

            skullCount=player.getSkullCount();

            if (skullCount >= 3) {
                endRound=true;
                player.resetScore();
            }
        }
        int score=score(player.getFaces());

        player.addScore(score);
        //make sure give back dices
        player.resetDice();

    }



    public static int score(Faces faces[]){

        //key is the type, value is the score to the type

        Hashtable<String, Integer> Rules= new Hashtable(Map.of("3 of a kind",100, "4 of a kind", 200, "5 of a kind",
                500 ,"6 of a kind", 1000, "7 of a kind", 2000, "8 of a kind", 4000, "Diamond", 100, "Gold", 100));

        int score[] = new int[1];//variables in lambda must be final
        Hashtable<Faces, Integer> faceList=new Hashtable();

        for (Faces face: faces) {
            if(face==Faces.None || face== Faces.SKULL){ //none don't count as face, but to dodge null pointer exception
                continue;
            }
            if (!faceList.containsKey(face)){
                faceList.put(face,1);
            }
            else{
                faceList.put(face,(faceList.get(face)+1));
            }
        }

        faceList.forEach((k,v)->{
            switch(v) {
                case 3:
                    score[0]+=Rules.get("3 of a kind");
                    break;
                case 4:
                    score[0]+=Rules.get("4 of a kind");
                    break;
                case 5:
                    score[0]+=Rules.get("5 of a kind");
                    break;
                case 6:
                    score[0]+=Rules.get("6 of a kind");
                    break;
                case 7:
                    score[0]+=Rules.get("7 of a kind");
                    break;
                default:
                    break;
            }
        });
        return score[0];
    }



}
