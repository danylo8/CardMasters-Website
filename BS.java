import java.util.ArrayList;
import java.util.Collections;

public class BS extends Main
{
    static String[] cardNum = {"1", "2", "3", "4", "5", "6", "7", "J", "Q", "K"};
    static String[] cardType = {"Hearts", "Spades", "Clubs", "Diamonds"};
    static ArrayList<String> originalDeck = new ArrayList<>();
    static ArrayList<String> playerDeck = new ArrayList<>();
    static ArrayList<String> oppsDeck =  new ArrayList<>();
    static ArrayList<String> activeDeck = new ArrayList<>();
    static int expectedCard = 0;
    static boolean playerTurn = true;
    static boolean oppsTurn = false;
    static boolean callBS = false;

    public BS()

    {
        System.out.println("Welcome to BS! If you need to learn the rules, you may read them at the bottom of the page\n");
        intitalizeDeck(originalDeck);
        shuffleDeck(shuffleDeck);

        for (int i=0; i<20; i++)
        {
            playerDeck.add(deck.get(i));
        }

        for (int i=0; i<20; i++)
        {
            oppsDeck.add(deck.get(i));
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

        public static void shuffleDeck(ArrayList<String> deck)
        {

        }

        public static void whoHasAce()
        {

            boolean playerHasAce=false;
            boolean oppHasAce=false;

            for (int i=0; i<playerDeck.length(); i++)
            {
                if (i.equals("A of Spades"))
                {
                    playerHasAce=true;
                }
            }

            for (int i=0; i<oppsDeck.length(); i++)
            {
                if (i.equals("A of Spades"))
                {
                    oppHasAce=true;
                }
            }
        }
    }