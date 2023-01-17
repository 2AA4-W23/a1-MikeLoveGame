import pk.*;
import java.util.*;
public class PiratenKarpen {





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
            Game.round(player1);
            Game.round(player2);
            if(player1.getScore()>player2.getScore()){
                player1Wins++;
            }
            else if (player2.getScore()>player1.getScore()){
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


    

