package pk;
import java.util.*;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Brain {
    private final String stretagy;
    private final Player player;

    private final Logger logger=LogManager.getLogger(Player.class.getName());
    protected Brain(Player player,String stretagy){
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

    public Integer[] DicesToRoll(){
        Faces[] faces=facesToRoll();
        Integer[] diceList=new Integer[8];
        int index=0;
        int count=0;

        for (Faces face1: player.getFaces() ) {
            for (Faces face2: faces) {
                if(face1==face2){
                    diceList[count]=index;
                    count++;
                }
            }
            index++;
        }
        if(count<2){
            return null;
        }
        logger.log( Level.INFO,"Player "+player.getName()+" decided to roll " + count + " dices");
        return diceList;
    }
    private Faces[] facesToRoll(){

        Faces[] faces=player.getFaces();

        Hashtable<Faces, Integer> faceList=new Hashtable();
        Faces[] facesNeedRoll= new Faces[8];
        int count=0;

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

        faceList.forEach((k,v)->{
            if (v<3){
                facesNeedRoll[count]=k;
            }
        }
        );

        return facesNeedRoll;

    }

    public boolean ifEndRound(){
        if(stretagy.equals("smart")){ //problems to fix
            int score=pkGame.score(player.getFaces());
            if(DicesToRoll()==null){
                return true;
            }
            if(score+player.getScore()>=1000){
                return true;
            }
        }
        else{

            Random r=new Random();

            if(r.nextInt()%2==0){
                return true;
            }
            else{
                return false;
            }
        }

        return false;
    }



}
