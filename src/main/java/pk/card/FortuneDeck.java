package pk.card;

public class FortuneDeck extends Deck {

    public FortuneDeck(){
        super(new FortuneCard());
        for (int i = 0; i <6; i++) {
            this.insertFront(new FortuneCard(3));
        }
        for (int i = 0; i <24; i++) {
            this.insertEnd(new FortuneCard());
        }
        for (int i = 0; i < 4; i++) {
            this.insertEnd(new FortuneCard(6));
        }

    }

}
