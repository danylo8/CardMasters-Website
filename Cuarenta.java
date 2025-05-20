import java.util.ArrayList;
import java.util.Collections;

public class Cuarenta extends Main
{
    static String[] cardNum = {"1", "2", "3", "4", "5", "6", "7", "J", "Q", "K"};
    static String[] cardType = {"Hearts", "Spades", "Clubs", "Diamonds"};
    static ArrayList<String> playerDeck = new ArrayList<>();
    static ArrayList<String> oppsDeck =  new ArrayList<>();
    static ArrayList<String> fourtyDeck = new ArrayList<>();
    static ArrayList<String> activeDeck = new ArrayList<>();
    static int playerPoints = 0;
    static int oppPoints = 0; 

    public Cuarenta()
    {
        //intro
        System.out.println("Welcome to Cuarenta! If you need to learn the rules, you may read them at the bottom of the page\n");
       
        //set up of the decks 
        intitalizeDeck(fourtyDeck);
        shuffleDeck(fourtyDeck);
        for(int i = 0; i < 5; i++)
        {
            String temp = fourtyDeck.get(i);
            playerDeck.add(temp);
        }
        for(int i = 0; i < 5; i++)
        {
            fourtyDeck.remove(0);
        }
        for(int i = 0; i < 5; i++)
        {
            String temp = fourtyDeck.get(i);
            oppsDeck.add(temp);
        }
        for(int i = 0; i < 5; i++)
        {
            fourtyDeck.remove(0);
        }
        System.out.println(playerDeck);
        if(!(fourOfAKind(playerDeck).equals(null)))
        {
            System.out.println(fourOfAKind(playerDeck) + " You automatically win!");
        }else if(!(fourOfAKind(oppsDeck).equals(null)))
        {
            System.out.println(fourOfAKind(oppsDeck) + " you automatically lose");
        }else{
            
        }
    }

    public ArrayList<String> getDeck()
    {
        return fourtyDeck;
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

    public String fourOfAKind(ArrayList<String> deck)
    {
        int temp = 0;
        for(int i = 0; i < deck.size(); i++)
        {
            String match = deck.get(i).substring(0,1);
            for(int j = 0; j < deck.size(); j++)
            {
                if(j==i)
                {
                    j++;
                }
                if(deck.get(j).substring(0,1).equals(match))
                {
                    temp++;
                }
            }
             if(temp >= 4)
            {
                return "Four of a Kind! There are 4 cards of " + match;
            }else{
                temp = 0;
            }
        }
        return null;
    }

    public String ronda(ArrayList<String> deck)
    {
        int temp = 0;
        for(int i = 0; i < deck.size(); i++)
        {
            String match = deck.get(i).substring(0,1);
            for(int j = 0; j < deck.size(); j++)
            {
                if(j==i)
                {
                    j++;
                }
                if(deck.get(j).substring(0,1).equals(match))
                {
                    temp++;
                }
            }
            if(temp == 3)
            {
                return "Ronda! 3 points awarded!";
            }
        }
        return null;
    }

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
