package pk;
import java.util.*;
public class Brain {
    private final String stretagy;
    private final Player player;
    protected Brain(Player player,String stretagy){
        this.stretagy=stretagy;
        this.player=player;
    }

    public String getStrategy(){
        return this.stretagy;
    }
    public int numDiceToRoll(int usableDice){
        Random r= new Random();

        return r.nextInt(usableDice-2)+2;
    }

    public boolean ifendGame(){
        if(stretagy.equals("smart")){ //problems to fix
            int score=pkGame.score(player.getFaces());
            if(pkGame.winner(player)){
                return true;
            }
        }
        else{
            Random r=new Random();
            if(r.nextInt()%2==0){
                return true;
            }
            else{
                return false;
            }
        }

        return false;
    }



}
