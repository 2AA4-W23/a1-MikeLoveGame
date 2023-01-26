package pk;

import java.util.*;

public class FortuneCard extends Card{



    private enum Face{Treasure_Chest, Captain, Sorceress, Sea_Battle, Gold, Diamond, Monkey_Bussiness, Skull, nop}

    private Face face;
    public FortuneCard(){
        face=Face.nop;
    }

    public FortuneCard(int val){
        face=Face.values()[val];
    }

    @Override
    public String getFace(){
        return this.face.toString();
    }
    @Override
    public int compareTo(Card o) {
        return face.compareTo(Face.valueOf(o.getFace()));
    }
}