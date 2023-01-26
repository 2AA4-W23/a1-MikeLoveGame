package pk;
import java.util.*;
/*deck is implemented as a linked list*/
public abstract class Deck {
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
    public Deck(Card head){
        this.head=new Node(head);
        end =this.head;
        size=1;
    }
    public void insertFront(Card card){
        Node head=this.head;
        this.head=new Node(card);
        this.head.next=head;
        size++;
    }
    public void insertEnd(Card card){
        end.next=new Node(card);
        end = end.next;
        size++;
    }

    public Card deal(){
        Node first=head;
        head=head.next;
        if(end ==first){
            end=head;
        }
        size--;
        return first.card;
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
    }

    private void cutDeck(Node ptr){
        end.next=this.head;
        this.head=ptr.next;
        ptr.next=null;
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
        int cut=1+r.nextInt(size)-1;

        while(i<size/4){
            step=1+r.nextInt(3);
            for (int j = 0; j < step; j++) {
                prev=ptr;
                ptr=ptr.next;
            }
            insertEnd(prev, ptr);
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

}
