package pk.card;

import pk.card.Deck;
import pk.card.FortuneCard;

public class FortuneDeck extends Deck {

    public FortuneDeck(){
        super(new FortuneCard());
        for (int i = 0; i <6; i++) {
            this.insertFront(new FortuneCard(3));
        }
        for (int i = 0; i <28; i++) {
            this.insertEnd(new FortuneCard());
        }

    }

}
