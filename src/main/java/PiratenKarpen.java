import pk.Dice;

public class PiratenKarpen {

    public static void main(String[] args) {
        System.out.println("Welcome to Piraten Karpen Simulator!");
        System.out.println("I'm rolling a dice");
        Dice myDice = new Dice();

        Dice Dices[]= new Dice[8];

        for (Dice dice:Dices ) {
            dice= new Dice();
            dice.roll();
        }

        int numGames= Integer.parseInt(args[0]);

        System.out.println(myDice.roll());

        System.out.println("That's all folks!");

    }



    
}
