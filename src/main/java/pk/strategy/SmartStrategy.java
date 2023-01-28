package pk.strategy;
import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pk.card.Card;
import pk.dice.Faces;
import pk.Player;
import pk.pkGame;

/**
 The Desciption of the method to explain what this object do:
 This object is the decision headquarter for the player where it tells
 the player what to do when the player's strategy is combo driven

 */
public class SmartStrategy implements Strategy{
    private final String stretagy;
    private final Player player;

    private final Logger logger=LogManager.getLogger(Player.class.getName());
    public SmartStrategy(Player player, String stretagy){
        this.stretagy=stretagy;
        this.player=player;
    }

    public String getStrategy(){
        return this.stretagy;
    }
    public int numDiceToRoll(int usableDice){
        Random r= new Random();
        return r.nextInt(usableDice-2)+2;

    }

    public Integer[] DicesToRoll(Card card){
        LinkedList<Faces> faces=facesToRoll(card);
        Integer[] diceList=new Integer[8];
        int index=0;
        int count=0;

        for (Faces face1: player.getFaces() ) {
            for (Faces face2: faces) {
                if(face1==face2){
                    diceList[count]=index;
                    count++;
                    break;
                }
            }
            index++;
        }
        return diceList;
    }
    public boolean ifEndRound(Card card){

        if(stretagy.equals("smart")){//problems to fix
            int score= pkGame.score(player.getFaces());
            try{
                Integer[] x=DicesToRoll(card);
                int a=x[1];
            }
            catch(NullPointerException e){
                return true;
            }
            if (score>=1000){
                return true;
            }
            if(score+player.getScore()>=6000){
                return true;
            }
        }
        else{

            Random r=new Random();

            if(r.nextInt()%2==0){
                return true;
            }
        }

        return false;
    }
    private LinkedList<Faces> facesToRoll(Card card){

        Faces[] faces=player.getFaces();
        Hashtable<Faces, Integer> faceList=new Hashtable();
        LinkedList<Faces> facesNeedRoll= new LinkedList<>();

        if(card.getFace().equals("Sea_Battle")){
            facesNeedRoll=this.SeaBattleRoll();
        }
        else{
            facesNeedRoll=this.NormalRoll();
        }

        return facesNeedRoll;
    }

    private LinkedList<Faces> SeaBattleRoll(){
        Faces[] faces=player.getFaces();
        Hashtable<Faces, Integer> faceList=new Hashtable();
        LinkedList<Faces> facesNeedRoll= new LinkedList<>();

        for (Faces face: faces) {
            if(face==Faces.None || face== Faces.SKULL || face==Faces.SABER){ //none don't count as face, but to dodge null pointer exception
                continue;
            }
            else if (!faceList.containsKey(face)){
                faceList.put(face,1);
            }
            else{
                faceList.put(face,(faceList.get(face)+1));
            }
        }

        faceList.forEach((k,v)->{
                        facesNeedRoll.add(k);
                }
        );



        return facesNeedRoll;
    }

    private LinkedList<Faces> NormalRoll(){
        Faces[] faces=player.getFaces();
        Hashtable<Faces, Integer> faceList=new Hashtable();
        LinkedList<Faces> facesNeedRoll= new LinkedList<>();

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

        for (Faces face: faceList.keySet()){
            if (faceList.get(face)<3){
                facesNeedRoll.add(face);
            }
        }

        return facesNeedRoll;
    }



}
