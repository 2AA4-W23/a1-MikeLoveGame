package pk;

import java.util.Hashtable;
import java.util.Map;

public class Game {

    public static void round(Player player) {
        int skullCount = 0;

        boolean endRound = false;

        Faces[] faces= player.getFaces();

        while (!endRound) {

            player.rollDice();
            faces=player.getFaces();


            endRound=player.ifEndRound();
            if (skullCount >= 3) {
                endRound = true;
            }

        }

        //make sure give back dices
        for (int i = 0; i < Dices.length; i++) {
            Dices[i]=new Dice();
            faces[i]=Dices[i].roll();
        }
        player.setUsableDice(Dices.length);

        player.setScore(score(faces));
    }



    public static int score(Faces faces[]){
        Hashtable<String, Integer> Rules= new Hashtable(Map.of("3 of a kind",100, "4 of a kind", 200, "5 of a kind",
                500 ,"6 of a kind", 1000, "7 of a kind", 2000, "8 of a kind", 4000, "Diamond", 100, "Gold", 100));
        int score[] = new int[1];//variables in lambda must be final
        Hashtable<Faces, Integer> faceList=new Hashtable();

        for (Faces face: faces) {
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
