package pk.card;

import pk.card.Card;

import java.util.*;

public class FortuneCard extends Card {



    private enum Face{Treasure_Chest, Captain, Sorceress, Sea_Battle, Gold, Diamond, Monkey_Bussiness, Skull, nop}
    private Face face;
    private int value=0;
    public FortuneCard(){
        face=Face.nop;
    } //in see battle, Saber and sword is the same shit

    public FortuneCard(int val){
        face=Face.values()[val];
        value=val;
        if(face==Face.Sea_Battle){
            Random r=new Random();
            value=100*(1+r.nextInt(10));
        }
    }

    public int getValue(){
        return value;
    }
    @Override
    public String getFace(){
        return this.face.toString();
    }
    @Override
    public int compareTo(Card o) {
        return this.value-o.getValue();
    }
}