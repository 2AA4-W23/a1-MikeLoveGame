import pk.*;
import java.util.*;
public class PiratenKarpen {





    public static void main(String[] args) throws Exception{
        System.out.println("Welcome to Piraten Karpen Simulator!");
        System.out.println("I'm rolling a dice");

        Player player1=new Player();
        Player player2=new Player("smart");
        int numGames;
        try {
            numGames= Integer.parseInt(args[0]);
        }
        catch(IndexOutOfBoundsException e){
            numGames=30;
        }

        Player.pkGame(player1, player2, numGames);

        int player1Wins=player1.getWins();
        int player2Wins=player2.getWins();
        int draw=numGames-player1Wins-player2Wins;


        double player1Winrate=player1Wins/(double)numGames*100;
        double player2Winrate=player2Wins/(double)numGames*100;
        double drawrate=draw/(double)numGames*100;

        System.out.printf("player1 Wins %5.2f %%\n", player1Winrate);
        System.out.printf("player2 Wins %5.2f %%\n", player2Winrate);
        System.out.printf("draws: %5.2f %%\n",drawrate);
        System.out.println("That's all folks!");
    }


}


    

