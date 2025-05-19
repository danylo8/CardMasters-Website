import java.util.ArrayList;

public class Cuarenta extends Main
{
    static String[] cardNum = {"1", "2", "3", "4", "5", "6", "7", "J", "Q", "K"};
    static String[] cardType = {"Heart", "Spades", "Clubs", "Diamonds"};
    static ArrayList<String> playerDeck = new ArrayList<>();
    static ArrayList<String> oppsDeck =  new ArrayList<>();
    static ArrayList<String> fourtyDeck = new ArrayList<>();
    private boolean fourOfAKind = false;
    private boolean ronda = false;

    public Cuarenta()
    {
        
        intitalizeDeck(fourtyDeck);
        super.shuffleDeck(fourtyDeck);
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

    
}
