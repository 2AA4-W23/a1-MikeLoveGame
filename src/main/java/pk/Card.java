package pk;

public abstract class Card implements Comparable<Card>{
    private String face;
    private int value;

    public String getFace(){
        return this.face;
    }
    public abstract int getValue();

}
