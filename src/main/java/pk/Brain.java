package pk;
import java.util.*;
public class Brain {
    private final String stretagy;
    private final Player player;
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
    
    public Faces[] facesToRoll(){

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

    public boolean ifendGame(){
        if(stretagy.equals("smart")){ //problems to fix
            int score=pkGame.score(player.getFaces());
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
