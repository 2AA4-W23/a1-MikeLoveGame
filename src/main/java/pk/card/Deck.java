package pk.card;

import java.util.*;
/*deck is implemented as a linked list*/
public class Deck {
    private class Node{
        Card card;
        Node next;
        public Node(Card card){
            this.card=card;
        }
    }
    private Node head;
    private int size;
    private Node end;

    private Deck antideck;

    public Deck(){
        size=0;
    }
    public Deck(Card head){
        this.head=new Node(head);
        end =this.head;
        size=1;
        antideck=new Deck();
    }
    public void insertFront(Card card){
        if (head == null) {
            this.head=new Node(card);
            end =this.head;
            size=1;
            return;
        }

        Node head=this.head;
        this.head=new Node(card);
        this.head.next=head;

        size++;
    }
    public void insertEnd(Card card){
        if(head==null){
            this.head=new Node(card);
            end =this.head;
            size=1;
            return;
        }
        end.next=new Node(card);//bug appears on rare case where end is null, but head is not null
        end = end.next;
        size++;
    }

    public Card deal(){
        Card first=head.card;

        try{
            head=head.next;
        }
        catch(NullPointerException e){
            head=null;
        }

        size--;
        antideck.insertEnd(first);
        return first;
    }

    public void cutDeck(int pos){
        Node ptr=head;
        if(pos>size){
            return;
        }
        for (int i = 0; i < pos-1; i++) {
            ptr=ptr.next;
        }
        end.next=this.head;
        this.head=ptr.next;
        ptr.next=null;
        end=ptr;
    }

    private void cutDeck(Node ptr){
        end.next=this.head;
        this.head=ptr.next;
        ptr.next=null;
        end=ptr;
    }

    private void insertEnd(Node prev, Node ptr){
        if( prev==null){
            head=head.next;
            end.next=ptr;
            end=end.next;
            return;
        }
        prev.next=ptr.next;
        end.next=ptr;
        end=end.next;
    }
    public void quickShuffle(){
        Random r= new Random();
        Node prev=null;
        Node ptr=head;
        int i=0;
        int step;
        int cut=2+r.nextInt(size)-4;

        while(i<size/4){
            step=1+r.nextInt(3);
            for (int j = 0; j < step; j++) {
                prev=ptr;
                ptr=ptr.next;
            }
            insertEnd(prev, ptr);
            i++;
        }
        cutDeck(cut);

    }

    public Card getHead(){
        Card temp=head.card;
        return temp;
    }
    public Card getEnd(){
        Card temp=end.card;
        return temp;
    }
    public int getSize(){
        return size;
    }

    public Card get(int index){
        Node ptr=head;
        for (int i = 0; i <index; i++) {
            ptr=ptr.next;
        }
        return ptr.card;
    }

    public static void resetDeck(Deck deck){ //YESSS, IMMAA FUCKING GENIOUSSSSS
        if(deck.antideck.size==0){
            return;
        }
        if(deck.head==null){
            deck.head=deck.antideck.head;
            deck.antideck.head=null;
            return;
        }

        Deck newdeck= deck.antideck;
        newdeck.end.next=deck.head;
        if(newdeck.end!=null){
            newdeck.end=deck.end;
        }

        deck.head=newdeck.head;
        deck.size=deck.size+deck.antideck.size;
        deck.end.next=null;
        deck.antideck=new Deck();
    }


}
