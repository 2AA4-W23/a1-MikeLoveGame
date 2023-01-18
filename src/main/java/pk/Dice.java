package pk;
import java.util.Arrays;
import java.util.Random;

public class Dice {
    public Dice(){
    }


    public Faces roll() {
        int howManyFaces = Faces.values().length-1;
        Random bag = new Random();
        return Faces.values()[bag.nextInt(howManyFaces)];
    }

    
}
