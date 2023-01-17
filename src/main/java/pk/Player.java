package pk;

import java.util.Random;

public class Player {
    private  Dice Dices[]= new Dice[8];
    private  Faces faces[]= new Faces[Dices.length];
    private int usableDice=Dices.length;
    private int score=0;
    private String stretagy;
    public Player(){
        this("dead roll");
    }
    public Player(String Stretagy){
        for (int i = 0; i < Dices.length; i++) {
            Dices[i]= new Dice();
            faces[i]= Dices[i].roll();
        }
        stretagy=stretagy;
    }
    public int numDiceToRoll(){
        Random r=new Random();
        int num= r.nextInt(this.usableDice+1);
        return (num);
    }


    //Accessor Methods
    public Dice[] getDices(){
        return this.Dices;
    }

    public Faces[] getFaces(){
        return this.faces;
    }

    public int getUsableDice(){
        return this.usableDice;
    }

    public int getScore(){
        return this.score;
    }

    public String getStretagy(){
        return this.stretagy;
    }


    //Mutator Methods

    public void setUsableDice(int usableDice){
        this.usableDice = usableDice;
    }
    public void deleteOneDice(){

    }
    public void restoreDices(){

    }
    public void setScore(int score){
        this.score = score;
    }

    public void setStretagy(String stretagy){
        this.stretagy = stretagy;
    }
    public boolean ifEndRound(){
        return false;
    }//not implemented yet, wrote for future features

}
