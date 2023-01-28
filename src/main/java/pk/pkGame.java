package pk;

import java.util.Hashtable;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pk.card.*;
import pk.dice.*;
import pk.strategy.*;


public class pkGame {

    private static final Logger logger= LogManager.getLogger(Player.class.getName());

    public pkGame(Player[] players, int numGames, boolean traceMode){

        int gameCount=0;
        Player.traceMode=traceMode;
        FortuneDeck deck=new FortuneDeck();
        deck.quickShuffle();
        FortuneCard card;

        while(gameCount<numGames){
            boolean endround=false;

            for (Player player: players) {
                player.resetScore();
            }

            while(!endround) {
                for (Player player : players) {
                    if(deck.getHead()!=null) {
                        card = (FortuneCard) deck.deal();
                    }
                    else{
                        Deck.resetDeck(deck);
                        card=(FortuneCard) deck.deal();
                    }
                    logger.log(Level.INFO, "card Drawn is "+card.getFace());
                    if(card.getFace().equals("Sea_Battle")){
                        if(traceMode){
                            logger.log(Level.INFO,player.getName()+" is in a sea battle");
                        }
                        seaBattleRound(player, card);
                    }
                    else{normalRound(player, card);}
                    if (winner(player)) {
                        endround=true;
                        if(traceMode){logger.log(Level.INFO, player.getName()+" Win\n");}
                        break;
                    }
                }
            }
            gameCount++;
            Deck.resetDeck(deck);
        }

    }

    private static void normalRound(Player player, Card card)  {

        int skullCount = 0;

        int score=0;

        do{
            player.rollDice(card);
            skullCount=player.getSkullCount();
            if (skullCount >= 3) {
                if(Player.traceMode){logger.log(Level.INFO,player.getName()+" ended round bec 3 or more skull\n");}
                break;
            }
        }while (!player.ifEndRound(card));

        if (player.getSkullCount() < 3) {
            score=score(player.getFaces());
            player.addScore(score);
        }
        if(Player.traceMode){logger.log(Level.INFO, player.getName()+" scored "+score+" points this round\n");}
        //make sure give back dices
        player.resetDice();

    }

    private static void seaBattleRound(Player player, FortuneCard card){
        int reward=card.getValue();
        int skullCount = 0;
        int saberCount=0;
        int score=0;

        boolean endRound=false;

        while (!endRound) {
            player.rollDice(card);
            skullCount=player.getSkullCount();
            if (skullCount >= 3) {
                if(Player.traceMode){logger.log(Level.INFO,player.getName()+" lost sea battle since have 3 skull\n");}
                break;
            }
            else{
                Hashtable<Faces, Integer> faceList=Player.getFaceCount(player.getFaces());
                if(faceList.containsKey(Faces.SABER)){
                    saberCount=faceList.get(Faces.SABER);
                }
                if(saberCount>=4){
                    break;
                }
            }
        }

        if (player.getSkullCount() < 3) {
            score=score(player.getFaces());
            player.addScore(score);
            player.addScore(reward);
        }
        else{
            player.deduceScore(reward);
            score=score-reward;
        }

        if(Player.traceMode){logger.log(Level.INFO, player.getName()+" scored "+score+" points this round\n");}
        //make sure give back dices
        player.resetDice();
    }

    public static void resetGame(){

    }

    protected static boolean winner(Player player){
        if(player.getScore()>=6000){
            player.addwin();
            return true;
        }
        return false;
    }


    public static int score(Faces faces[]){

        //key is the type, value is the score to the type

        Hashtable<Integer, Integer> Rules = new Hashtable<>();
        Rules.put(3,100);//3 of a kind
        Rules.put(4, 200);//4 of a kind
        Rules.put(5,500);//5 of a kind
        Rules.put(6, 1000);//6 of a kind
        Rules.put(7, 2000);//7 of a kind
        Rules.put(8, 4000);//8 of a kind

        int score = 0;//variables in lambda must be final
        Hashtable<Faces, Integer> faceList= Player.getFaceCount(faces);
        int count=0;
        for(Faces k: faceList.keySet()){
            int v=faceList.get(k);
            if(v>2){
                count+=v;
                score+=Rules.get(v);
            }

            if(k==Faces.DIAMOND|| k==Faces.GOLD){
                score+=100*v;
            }

        }
        if(count==8){//full chest
            score+=500;
        }
        return score;
    }




}
