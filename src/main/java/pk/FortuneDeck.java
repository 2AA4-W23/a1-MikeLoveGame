package pk;

public class FortuneDeck extends Deck {

    private Card head;
    private Card next;
    private int size;

    public FortuneDeck(int size){
        int i=0;
        Card ptr;
        while(i<size){
            head=new FortuneCard();
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
