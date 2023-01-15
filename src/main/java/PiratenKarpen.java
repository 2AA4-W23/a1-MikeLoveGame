import pk.Dice;
import pk.Faces;
public class PiratenKarpen {
    static class Player{
        public final Dice Dices[]= new Dice[8];
        public final Faces faces[]= new Faces[Dices.length];

        public String stretagy;
        public Player(){
            this("dead roll");
        }
        public Player(String Stretagy){
            stretagy=stretagy;
        }

        public boolean ifEndRound(){
            switch (this.stretagy){
                case("dead roll"):
                    return false;
                default:
                    return false;
            }
        }

    }
    public static void round(Player player) {
        int count = 0;
        int skullCount = 0;

        boolean endRound = false;

        while (!endRound) {

            for (int i = 0; i < player.Dices.length; i++) {
                if (player.Dices[i] != null) {
                    player.faces[i] = player.Dices[i].roll();
                }
                if (player.faces[i] == Faces.SKULL) {
                    player.Dices[i] = null;
                    skullCount++;
                }
            }
            endRound=player.ifEndRound();

            if (skullCount >= 3) {
                endRound = true;
            }
        }
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

        int numGames= Integer.parseInt(args[0]);
        int gameCount=0;

        while(gameCount<=numGames){
            round(player1);
            round(player2);
            gameCount++;
        }


        System.out.println("That's all folks!");
    }


}


    

