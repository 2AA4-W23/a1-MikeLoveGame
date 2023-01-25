package pk;

public class Deck {
    private Card[] deck;
    public Deck (){
        this(35);
        for (int i = 0; i < 6; i++) {
            deck[i]=Card.Sea_Battle;
        }
    }
    public Deck(int length){
        deck=new Card[length];
        for (int i = 0; i < deck.length; i++) {
            deck[i]=Card.nop;
        }
    }

    public static void Shuffle(Deck deck){
        
    }

}
