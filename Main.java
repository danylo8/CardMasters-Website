import java.util.ArrayList;
import java.util.Collections;

public class Main
{
    static String[] cardNum = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    static String[] cardType = {"Heart", "Spades", "Clubs", "Diamonds"};
    static ArrayList<String> mainDeck = new ArrayList<>();
    static ArrayList<String> activeDeck = new ArrayList<>();
    public static void main(String[] args) 
    {
        intitalizeDeck(mainDeck);
        shuffleDeck(mainDeck);
        
    }

    //Try to figure out a way to fix the code
   public static void shuffleDeck(ArrayList<String> deck)
    {
        //String temp = "";
        //for(int i = 0; i < deck.length; i++)
        {
            //temp = deck[i];
            //deck[i] = deck[Math.random()*deck.length];
            //deck[Math.random()*deck.length] = temp;
            Collections.shuffle(deck);
        }
    }

    public static void intitalizeDeck(ArrayList<String> deck)
    {
        for(int i = 0; i < cardType.length; i++)
        {
            for(int j = 0;j < cardNum.length; j++)
            {
                deck.add(cardNum[j] + " of " + cardType[i]);
            }
        }
    }

    public String drawFromDeck(ArrayList<String> deck)
    {
        if(deck.size() == 0)
        {
            return null;
        }else{
            return deck.remove(0);
        }
    }
}
