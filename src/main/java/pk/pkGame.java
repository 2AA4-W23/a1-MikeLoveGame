package pk;

import java.util.Hashtable;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class pkGame {

    private static final Logger logger= LogManager.getLogger(Player.class.getName());
    public pkGame(Player[] players, int numGames, boolean traceMode){

        int gameCount=0;
        Player.traceMode=traceMode;


        while(gameCount<numGames){
            boolean endround=false;

            for (Player player: players) {
                player.resetScore();
            }

            while(!endround) {
                for (Player player : players) {
                    round(player);
                    if (winner(player)) {
                        endround=true;
                        logger.log(Level.INFO, player.getName()+" Win\n");
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

        int score=0;

        while (!player.ifEndRound()) {
            player.rollDice();
            skullCount=player.getSkullCount();
            if (skullCount >= 3) {
                if(Player.traceMode){logger.log(Level.INFO,player.getName()+" ended round bec 3 or more skull\n");}
                break;
            }
        }

        if (player.getSkullCount() < 3) {
            score=score(player.getFaces());
            player.addScore(score);
        }
        if(Player.traceMode){logger.log(Level.INFO, player.getName()+" scored "+score+" points this round\n");}
        //make sure give back dices
        player.resetDice();

    }

    protected static boolean winner(Player player){
        if(player.getScore()>=6000){
            player.addwin();
            return true;
        }
        return false;
    }


    public static int score(Faces faces[]){

        //key is the type, value is the score to the type

        Hashtable<Integer, Integer> Rules = new Hashtable<>();
        Rules.put(3,100);//3 of a kind
        Rules.put(4, 200);//4 of a kind
        Rules.put(5,500);//5 of a kind
        Rules.put(6, 1000);//6 of a kind
        Rules.put(7, 2000);//7 of a kind
        Rules.put(8, 4000);//8 of a kind

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
        int count=0;
        for(Faces k: faceList.keySet()){
            int v=faceList.get(k);
            if(v>2){
                count+=v;
                score+=Rules.get(v);
            }

            if(k==Faces.DIAMOND|| k==Faces.GOLD){
                score+=100*v;
            }

        }
        if(count==8){//full chest
            score+=500;
        }
        return score;
    }

}
