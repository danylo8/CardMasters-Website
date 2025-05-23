import java.util.ArrayList;
import java.util.Scanner;

public class Spit extends Main
{
    static ArrayList<String> mainDeck = new ArrayList<>();
    static ArrayList<String> playerDeck = new ArrayList<>();
    static ArrayList<String> oppsDeck =  new ArrayList<>();
    static ArrayList<String> playerPile = new ArrayList<>();
    static ArrayList<String> oppsPile =  new ArrayList<>();
    static ArrayList<String> playerOne = new ArrayList<>();
    static ArrayList<String> oppsOne =  new ArrayList<>();
    static ArrayList<String> playerTwo = new ArrayList<>();
    static ArrayList<String> oppsTwo =  new ArrayList<>();
    static ArrayList<String> playerThree = new ArrayList<>();
    static ArrayList<String> oppsThree =  new ArrayList<>();
    static ArrayList<String> playerFour = new ArrayList<>();
    static ArrayList<String> oppsFour =  new ArrayList<>();
    static ArrayList<String> playerFive = new ArrayList<>();
    static ArrayList<String> oppsFive =  new ArrayList<>();
    static Scanner scan = new Scanner(System.in);

    public void intitalizeSpit()
    {
        super.intitalizeDeck(mainDeck);
        super.shuffleDeck(mainDeck);
        for(int i = 0; i < mainDeck.size();i++)
        {
            if(i % 2 == 0)
            {
                playerDeck.add(mainDeck.get(i));
            }else{
                oppsDeck.add(mainDeck.get(i));
            }
        }
        playerOne.add(playerDeck.remove(0));
        oppsOne.add(oppsDeck.remove(0));
        playerTwo.add(playerDeck.remove(0));
        oppsTwo.add(oppsDeck.remove(0));
        playerTwo.add(playerDeck.remove(0));
        oppsTwo.add(oppsDeck.remove(0));
        playerThree.add(playerDeck.remove(0));
        oppsThree.add(oppsDeck.remove(0));
        playerThree.add(playerDeck.remove(0));
        oppsThree.add(oppsDeck.remove(0));
        playerThree.add(playerDeck.remove(0));
        oppsThree.add(oppsDeck.remove(0));
        playerFour.add(playerDeck.remove(0));
        oppsFour.add(oppsDeck.remove(0));
        playerFour.add(playerDeck.remove(0));
        oppsFour.add(oppsDeck.remove(0));
        playerFour.add(playerDeck.remove(0));
        oppsFour.add(oppsDeck.remove(0));
        playerFour.add(playerDeck.remove(0));
        oppsFour.add(oppsDeck.remove(0));
        playerFive.add(playerDeck.remove(0));
        oppsFive.add(oppsDeck.remove(0));
        playerFive.add(playerDeck.remove(0));
        oppsFive.add(oppsDeck.remove(0));
        playerFive.add(playerDeck.remove(0));
        oppsFive.add(oppsDeck.remove(0));
        playerFive.add(playerDeck.remove(0));
        oppsFive.add(oppsDeck.remove(0));
        playerFive.add(playerDeck.remove(0));
        oppsFive.add(oppsDeck.remove(0));
        playerPile.add(playerDeck.remove(0));
        oppsPile.add(oppsDeck.remove(0));
    }

    public void playCard(ArrayList<String> deck, String card)
    {
        int refer = Integer.parseInt(deck.get(0).substring(0, 1));
        int temp = Integer.parseInt(card.substring(0, 1));
        if(Math.ab)
    }
}
