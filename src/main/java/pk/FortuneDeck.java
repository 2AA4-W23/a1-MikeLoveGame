package pk;

public class FortuneDeck extends Deck {

    public FortuneDeck(){
        super(new FortuneCard());
        for (int i = 0; i <6; i++) {
            this.insertFront(new FortuneCard(4));
        }
        for (int i = 0; i <28; i++) {
            this.insertEnd(new FortuneCard());
        }

    }

}
