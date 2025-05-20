import java.util.ArrayList;
import java.util.Collections;

public class Main
{
    static String[] cardNum = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    static String[] cardType = {"Hearts", "Spades", "Clubs", "Diamonds"};
    static ArrayList<String> mainDeck = new ArrayList<>();
    static ArrayList<String> activeDeck = new ArrayList<>();
    public static void main(String[] args) 
    {
        Cuarenta game = new Cuarenta();
    }

    //Try to figure out a way to fix the code
   //public static void shuffleDeck(ArrayList<String> deck)
    {
        //String temp = "";
        //for(int i = 0; i < deck.length; i++)
        {
            //temp = deck[i];
            //deck[i] = deck[Math.random()*deck.length];
            //deck[Math.random()*deck.length] = temp;
            //Collections.shuffle(deck);
        }
    }
}
