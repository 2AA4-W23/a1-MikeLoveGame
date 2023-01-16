import pk.Dice;
import pk.Faces;
import java.util.*;
public class PiratenKarpen {

    static class Player{
        public  Dice Dices[]= new Dice[8];
        public  Faces faces[]= new Faces[Dices.length];
        public int usableDice=Dices.length;
        public int score=0;
        public String stretagy;
        public Player(){
            this("dead roll");
        }
        public Player(String Stretagy){
            for (int i = 0; i < Dices.length; i++) {
                Dices[i]= new Dice();
                faces[i]= Dices[i].roll();
            }
            stretagy=stretagy;
        }
        public int numDiceToRoll(){
            Random r=new Random();
            int num= r.nextInt(this.usableDice+1);
            return (num);
        }
        public boolean ifEndRound(){
            return false;
        }//not implemented yet, wrote for future features

    }


    public static void round(Player player) {
        int skullCount = 0;

        boolean endRound = false;

        while (!endRound) {

            for (int i = 0; i < (player.numDiceToRoll()); ) {
                if (player.Dices[i] != null) {
                    player.faces[i] = player.Dices[i].roll();
                    i++;
                    if (player.faces[i] == Faces.SKULL) {
                        player.Dices[i] = null;
                        skullCount++;
                        player.usableDice--;
                    }
                }
            }

            endRound=player.ifEndRound();
            if (skullCount >= 3) {
                endRound = true;
            }

        }

        //make sure give back dices
        for (int i = 0; i < player.Dices.length; i++) {
            player.Dices[i]=new Dice();
            player.faces[i]=player.Dices[i].roll();
        }
        player.usableDice=player.Dices.length;

        player.score=score(player.faces);
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


    public static void main(String[] args) {
        System.out.println("Welcome to Piraten Karpen Simulator!");
        System.out.println("I'm rolling a dice");

        Dice Dices[]= new Dice[8];
        Faces faces[]= new Faces[Dices.length];
        for (Dice dice:Dices ) {
            dice= new Dice();
        }
        Player player1=new Player();
        Player player2=new Player();
        int numGames;
        try {
            numGames= Integer.parseInt(args[0]);
        }
        catch(IndexOutOfBoundsException e){
            numGames=42;
        }

        int gameCount=0;

        int player1Wins=0;
        int player2Wins=0;
        int draw=0;

        while(gameCount<numGames){
            round(player1);
            round(player2);
            if(player1.score>player2.score){
                player1Wins++;
            }
            else if (player2.score>player1.score){
                player2Wins++;
            }
            else{
                draw++;
            }

            gameCount++;
        }
        double player1Winrate=player1Wins/(double)numGames*100;
        double player2Winrate=player2Wins/(double)numGames*100;
        double drawrate=draw/(double)numGames*100;

        System.out.printf("player1 Wins %5.2f %%\n", player1Winrate);
        System.out.printf("player2 Wins %5.2f %%\n", player2Winrate);
        System.out.printf("draws: %5.2f %%\n",drawrate);
        System.out.println("That's all folks!");
    }


}


    

