import pk.*;
import pk.Player;

public class PiratenKarpen {

    public static void main(String[] args){
        System.out.println("Welcome to Piraten Karpen Simulator!");

        Player player1;
        Player player2;


        int numGames;
        try {
            numGames= Integer.parseInt(args[0]);
        }
        catch(Exception e){
            numGames=1;
        }

        boolean traceMode=false;
        try {
            traceMode= Boolean.parseBoolean(args[1]);
        }

        catch(Exception e){
            traceMode=true;
        }


        player1=new Player("player1", "smart");
        player2=new Player("player2");

        try{
            if("svd".equals(args[0])){
                player1=new Player("player1", "smart");
                player2=new Player("player2");
            }
        }
        catch(Exception e){
        }

        Player[] players={player1, player2};

        new pkGame(players, numGames, traceMode);

        int player1Wins=player1.getWins();
        int player2Wins=player2.getWins();


        double player1Winrate=player1Wins/(double)numGames*100;
        double player2Winrate=player2Wins/(double)numGames*100;
        System.out.println("total games played: "+numGames);
        System.out.printf("player1 Wins %5.2f %%\n", player1Winrate);
        System.out.printf("player2 Wins %5.2f %%\n", player2Winrate);
        System.out.println("That's all folks!");
    }

}


    

