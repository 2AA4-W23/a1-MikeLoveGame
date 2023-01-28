package pk.strategy;

import pk.card.Card;

public interface Strategy {
    public Integer[] DicesToRoll(Card card);
    public boolean ifEndRound(Card card);
}
