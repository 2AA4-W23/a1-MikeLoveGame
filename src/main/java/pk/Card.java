package pk;

public abstract class Card implements Comparable<Card>{
    private String face;

    public String getFace(){
        return this.face;
    }
}
