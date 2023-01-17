package pk;

import java.util.Random;

public class Player {
    private  Dice Dices[]= new Dice[8];
    private  Faces faces[]= new Faces[Dices.length];
    private int score=0;
    private String stretagy;
    public Player(){
        this("dead roll");
    }
    public Player(String Stretagy){
        for (int i = 0; i < Dices.length; i++) {
            Dices[i]= new Dice();
        }
        stretagy=stretagy;
    }
    public int numDiceToRoll(){
        Random r=new Random();
        int count=0;
        for (int i = 0; i < faces.length; i++) {
            if(faces[i]==Faces.SKULL){
                count++;
            }
        }
        int usableDice=Dices.length-count;
        int num= r.nextInt(usableDice+1);
        return (num);
    }


    //Accessor Methods


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



    public void resetDice(){
        for (int i = 0; i < faces.length; i++) {
            faces[i]=null;
        }
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

    public void rollDice(){
        int num=numDiceToRoll();

        int i=0;

        while(i<num){
            if(faces[i]==Faces.SKULL){
                continue;
            }
            Dices[i].roll();
            i++;
        }
    }

}
