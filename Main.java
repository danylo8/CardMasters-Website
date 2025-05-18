import java.util.ArrayList;

public class Main
{
    public static void main(String[] args) 
    {
        String[] cardNum = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] cardType = {"Heart", "Spades", "Clubs", "Diamonds", "Joker"};
        ArrayList<String> playerDeck = new ArrayList<>();
        ArrayList<String> oppsDeck =  new ArrayList<>();
        ArrayList<String> mainDeck = new ArrayList<>();
    }

    public void shuffleDeck()
    {
        String temp = "";
        for(int i = 0; i < mainDeck.length; i++)
        {
            temp = mainDeck[i];
            mainDeck[i] = mainDeck[Math.random()*mainDeck.length];
            mainDeck[Math.random()*mainDeck.length] = temp;
        }
    }

    public void intitalizeDeck()
    {
        for(int i = 0; i < cardType.length; i++)
        {
            for(int j = 0;j < cardNum.length; j++)
            {
                mainDeck.add(cardNum[j] + " of " + cardType[i]);
            }
        }
    }

    public String drawFromDeck()
    {
        if(mainDeck.length = 0)
        {
            return null;
        }else{
            return deck.remove[0];
        }
    }
}
