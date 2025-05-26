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

    public Spit()
    {
        //intro
        System.out.println("Welcome to Spit! If you need to learn the rules, you may read them at the bottom of the page\n");
        intitalizeSpit();
        
    }

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
        for(int i = 0; i < 2; i++)
        {
            playerTwo.add(playerDeck.remove(0));
            oppsTwo.add(oppsDeck.remove(0));
        }
        for(int i = 0; i < 3; i++)
        {
            playerThree.add(playerDeck.remove(0));
            oppsThree.add(oppsDeck.remove(0));
        }
        for(int i = 0; i < 4; i++)
        {
            playerFour.add(playerDeck.remove(0));
            oppsFour.add(oppsDeck.remove(0));
        }
        for(int i = 0; i < 5; i++)
        {
            playerFive.add(playerDeck.remove(0));
            oppsFive.add(oppsDeck.remove(0));
        }
        System.out.println("Your cards are as follows:\n" + "Pile 1: "+ playerOne.get(0)+"\nPile 2: " + playerTwo.get(0) + "\nPile 3: " + playerThree.get(0)+ "\nPile 4: " + playerFour.get(0)+ "\nPile 5: " + playerFive.get(0) + "\nYour current hand: " + playerDeck.get(0));
        System.out.println("\n What card would you like to play in your pile? (Type the card's full name)");
        String choice = scan.nextLine().trim();
        if(choice.equals(playerOne.get(0)))
        {
            playerPile.add(playerOne.remove(0));
        }else if(choice.equals(playerTwo.get(0)))
        {
            playerPile.add(playerTwo.remove(0));
        }else if(choice.equals(playerThree.get(0)))
        {
            playerPile.add(playerThree.remove(0));
        }else if(choice.equals(playerFour.get(0)))
        {
            playerPile.add(playerFour.remove(0));
        }else if(choice.equals(playerFive.get(0)))
        {
            playerPile.add(playerFive.remove(0));
        }else if(choice.equals(playerDeck.get(0)))
        {
            playerPile.add(playerDeck.remove(0));
        }
    }

    public boolean playCard(ArrayList<String> deck, String card)
    {
        int refer = 0;
        int temp = 0;
        if(deck.get(0).substring(0, 1).equals("Ace"))
        {
            refer = 1;
        }else if(deck.get(0).substring(0, 1).equals("J"))
        {
            refer = 11;
        }else if(deck.get(0).substring(0, 1).equals("Q"))
        {
            refer = 12;
        }else if(deck.get(0).substring(0, 1).equals("K"))
        {
            refer = 13;
        }else{
            refer = Integer.parseInt(deck.get(0).substring(0, 1));
        }
        if(card.substring(0, 1).equals("Ace"))
        {
            temp = 1;
        }else if(card.substring(0, 1).equals("J"))
        {
            temp = 11;
        }else if(card.substring(0, 1).equals("Q"))
        {
            temp = 12;
        }else if(card.substring(0, 1).equals("K"))
        {
            temp = 13;
        }else{
            temp = Integer.parseInt(card.substring(0, 1));
        }
        if(Math.abs(refer-temp)<= 1 || Math.abs(refer-temp)== 12 )
        {
            return true;
        }else{
            return false;
        }
    }
}
