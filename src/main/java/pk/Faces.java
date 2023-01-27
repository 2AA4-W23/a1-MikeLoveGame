package pk;
import java.util.ArrayList;
public enum Faces {
    MONKEY, PARROT, GOLD, DIAMOND, SABER, SKULL, None;
    public static String toString(Faces[] faces){
        String out="";
        for (int i = 0; i < faces.length; i++) {
            out+= " "+faces[i];
        }
        return out;
    }
}

