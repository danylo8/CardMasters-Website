import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Cuarenta extends Main
{
    static String[] cardNum = {"Ace", "2", "3", "4", "5", "6", "7", "J", "Q", "K"};
    static String[] cardType = {"Hearts", "Spades", "Clubs", "Diamonds"};
    static ArrayList<String> playerDeck = new ArrayList<>();
    static ArrayList<String> oppsDeck =  new ArrayList<>();
    static ArrayList<String> playerTrick = new ArrayList<>();
    static ArrayList<String> oppsTrick =  new ArrayList<>();
    static ArrayList<String> fourtyDeck = new ArrayList<>();
    static ArrayList<String> activeDeck = new ArrayList<>();
    static Scanner scan = new Scanner(System.in);
    static int playerPoints = 0;
    static boolean playerTurn = true;
    static boolean oppsTurn = false;
    static boolean round = true;
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
        System.out.println("Your cards are:");
        System.out.println(playerDeck);

        //initial 4OfAKind run
        String temp = "";
        if(!(fourOfAKind(playerDeck).equals(temp)))
        {
            System.out.println(fourOfAKind(playerDeck) + " You automatically win!");
        }else if(!(fourOfAKind(oppsDeck).equals(temp)))
        {
            System.out.println(fourOfAKind(oppsDeck) + " you automatically lose");
        }else if(!(fourOfAKind(playerDeck).equals(temp)) && !(fourOfAKind(oppsDeck).equals(temp))){
            System.out.println("It's a tie! Both players have gotten a four of a kind");
        }

        //first round; player's turn
        while(round)
        {
            while(playerTurn)
            {
                if(!(ronda(playerDeck).equals("")))
                {
                    ronda(playerDeck);
                    playerPoints += 4;
                }
                System.out.println("What card do you want to play? Type the full name of the card in question");
                String choice = scan.nextLine().trim();
                playerDeck.remove(choice);
                activeDeck.add(choice);
                System.out.println(activeDeck);
                playerTurn = false;
                oppsTurn = true;
            }

            while(oppsTurn)
            {
                if(!(ronda(oppsDeck).equals("")))
                {
                    ronda(oppsDeck);
                    oppPoints += 4;
                }
                oppsTurn = false;
                playerTurn = true;
            }
        }
    }

    public ArrayList<String> getDeck()
    {
        return fourtyDeck;
    }

    public ArrayList<String> getPlayerDeck()
    {
        return playerDeck;
    }

    public ArrayList<String> getoppsDeck()
    {
        return oppsDeck;
    }
    
    public static void intitalizeDeck(ArrayList<String> deck)
    {
        deck.clear();
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
                }else if(deck.get(j).substring(0,1).equals(match))
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
        return "";
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
                }else if(deck.get(j).substring(0,1).equals(match))
                {
                    temp++;
                }
            }
            if(temp == 3)
            {
                return "Ronda! 3 points awarded!";
            }
        }
        return "";
    }

    public static void shuffleDeck(ArrayList<String> deck)
    {
        //String temp = "";
        //for(int i = 0; i < deck.length; i++)
        //{
            //temp = deck[i];
            //deck[i] = deck[Math.random()*deck.length];
            //deck[Math.random()*deck.length] = temp;
            Collections.shuffle(deck);
        //}
    }

    public static void matchingPlayer(ArrayList<String> deck, ArrayList<String> userDeck, int temp)
    {
        if(deck.get(0).equals(userDeck.get(temp)))
        {
            playerTrick.add(deck.remove(0));
            playerTrick.add(userDeck.remove(temp));
        }
    }

     public static void matchingOpp(ArrayList<String> deck, ArrayList<String> userDeck, int temp)
    {
        if(deck.get(0).equals(userDeck.get(temp)))
        {
            oppsTrick.add(deck.remove(0));
            oppsTrick.add(userDeck.remove(temp));
        }
    }

    public String drawFromDeck(ArrayList<String> deck)
    {
        if(deck.size() == 0)
        {
            return "There are no more cards! The game is over!";
        }else{
            return deck.remove(0);
        }
    }

    public boolean matching(String card)
    {
        if(card.equals(activeDeck.get(activeDeck.size()-1)))
        {
            return true;
        }else{
            return false;
        }
    }
    public String additon(String card)
    {
        int temp = 0;
        int refer = Integer.parseInt(card.substring(0, 1));
        for(int i = activeDeck.size()-1; temp < refer; i--)
        {
            if(!(activeDeck.get(i).substring(0, 1).equals("J") || activeDeck.get(i).substring(0, 1).equals("Q") || activeDeck.get(i).substring(0, 1).equals("K")))
            {
                temp += Integer.parseInt(activeDeck.get(i).substring(0, 1));
            }
        } 
        if(temp == refer)
        {
            return "";
        }else{
            return "";
        }
    }
}
