package pk.card;

import java.util.ArrayList;
import java.util.*;

public class Deck {
    ArrayList<Card> cards = new ArrayList<Card>();
    ArrayList<Card> antideck = new ArrayList<Card>();

    public Deck(){

    }

    public Deck(Card head){
        cards.add(head);
    }

    public void insertFront(Card card){
        cards.add(0, card);
    }

    public void insertEnd(Card card){
        cards.add(card);
    }

    public Card deal(){
        Card first = cards.get(0);
        antideck.add(first);
        cards.remove(0);
        return first;
    }

    public void cutDeck(int pos){
        if (pos > cards.size()){
            return;
        }
        Card temp = cards.get(pos-1);
        cards.remove(pos-1);
        cards.add(temp);
    }

    public void quickShuffle(){
        Random r= new Random();
        int step =r.nextInt(2);
        while(step < cards.size()){
            Card temp = cards.get(step);
            cards.remove(step);
            cards.add(temp);
            step += 1 + r.nextInt(2);
        }
        for (int i = 0; i < 7; i++){
            int cut = 3 + r.nextInt(cards.size())-2;
            if (cut >= 0){
                cutDeck(cut);
            }
        }
    }

    public void longShuffle(){
        for (int i = 0; i < 10; i++) {
            quickShuffle();
        }
    }

    public Card getHead(){
        if (cards.size() == 0){
            return null;
        }
        return cards.get(0);
    }

    public Card getEnd(){
        if (cards.size() == 0){
            return null;
        }
        return cards.get(cards.size()-1);
    }

    public int getSize(){
        return cards.size();
    }

    public Card get(int index){
        return cards.get(index);
    }

    public static void resetDeck(Deck deck){
        if(deck.antideck.size() == 0){
            return;
        }
        if(deck.cards.size() == 0){
            deck.cards = deck.antideck;
            deck.antideck = new ArrayList<Card>();
            return;
        }
        deck.antideck.addAll(deck.cards);
        deck.cards = deck.antideck;
        deck.antideck = new ArrayList<Card>();

    }

}