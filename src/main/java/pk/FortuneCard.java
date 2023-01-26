package pk;

import java.util.*;

public class FortuneCard extends Card implements Comparator<FortuneCard>{



    private enum Face{Treasure_Chest, Captain, Sorceress, Sea_Battle, Gold, Diamond, Monkey_Bussiness, Skull, nop}

    private Face FaceValue;
    public FortuneCard(){
        FaceValue=Face.nop;
    }

    @Override
    public int compare(FortuneCard o1, FortuneCard o2) {
        return 0;
    }

    @Override
    public int compareTo(Card o) {
        return 0;
    }
}