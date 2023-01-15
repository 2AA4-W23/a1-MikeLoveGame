import pk.Dice;
import pk.Faces;
public class PiratenKarpen {

    public static void main(String[] args) {
        System.out.println("Welcome to Piraten Karpen Simulator!");
        System.out.println("I'm rolling a dice");

        Dice Dices[]= new Dice[8];
        Faces faces[]= new Faces[Dices.length];
        for (Dice dice:Dices ) {
            dice= new Dice();
        }

        int numGames= Integer.parseInt(args[0]);
        int count=0;
        int skullCount=0;

        boolean endgame=false;


        while(!endgame && count<numGames){

            for (int i = 0; i <Dices.length; i++) {
                if(Dices[i]!=null){
                    faces[i]=Dices[i].roll();
                }
                if (faces[i]==Faces.SKULL){
                    Dices[i]=null;
                    skullCount++;
                }
            }
            if(skullCount>=3){
                endgame=true;
            }
        }
        System.out.println("That's all folks!");
    }

    class Player{

    }

    
}
