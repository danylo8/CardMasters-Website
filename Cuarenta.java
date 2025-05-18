import java.util.ArrayList;

public class Cuarenta extends Main
{
    static String[] cardNum = {"1", "2", "3", "4", "5", "6", "7", "J", "Q", "K"};
    static String[] cardType = {"Heart", "Spades", "Clubs", "Diamonds", "Joker"};
    ArrayList<String> fourtyDeck = new ArrayList<>();
    public Cuarenta()
    {
        intitalizeDeck(fourtyDeck);
        super.shuffleDeck(fourtyDeck);
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
