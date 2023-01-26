package pk;

public class FortuneDeck extends Deck {

    private Card head;
    private FortuneDeck next;
    private int size;

    public FortuneDeck(){

    }

    public FortuneDeck(int size){
        int i=0;
        Card ptr;
        head=new FortuneCard();
        next=new FortuneDeck();
        while(i<size){
        }
    }

    public void Shuffle(){
        int length=size;
        int start=0;
        int end=length-1;

        int cut=start+(int)(Math.random()*(end-start));
        int shuffleTime=length*2;
        int i=0;
        while(i<shuffleTime){

        }

    }

}
