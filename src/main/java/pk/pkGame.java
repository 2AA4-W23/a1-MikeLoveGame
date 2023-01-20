package pk;

import java.util.Hashtable;
import java.util.Map;

public class pkGame {
    public pkGame(Player[] players, int numGames, boolean traceMode){

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

        int score;

        while (!endRound) {
            player.rollDice();

            endRound=player.ifEndRound();

            skullCount=player.getSkullCount();

            if (skullCount >= 3) {
                endRound=true;
            }
        }
        score=score(player.getFaces());

        player.addScore(score);
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
