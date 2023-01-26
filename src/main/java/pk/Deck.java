package pk;
/*deck is implemented as a linked list*/
public abstract class Deck {
    private class Node{
        Card card;
        Node next;
        public Node(Card card){
            this.card=card;
        }
    }
    protected Node head;
    protected int size;
    protected Node end;
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
        for (int i = 0; i < pos-1; i++) {
            ptr=ptr.next;
        }
        end.next=this.head;
        this.head=ptr.next;
        ptr.next=null;
    }

    public void quickShuffle(){

       



    }

}
