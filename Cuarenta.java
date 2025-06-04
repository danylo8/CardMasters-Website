import java.lang.reflect.Array;
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
        playerDeck.clear();
        oppsDeck.clear();
        initializeDeck(fourtyDeck);
        shuffleDeck(fourtyDeck);
        for(int i = 0; i < 5; i++)
        {
            playerDeck.add(fourtyDeck.removeLast());
        }
        for(int i = 0; i < 5; i++)
        {

            oppsDeck.add(fourtyDeck.removeLast());
        }
        System.out.println("Your cards are:");
        System.out.println(playerDeck);

        //initial 4OfAKind run
        if(!(fourOfAKind(playerDeck).isEmpty()))
        {
            System.out.println(fourOfAKind(playerDeck) + " You automatically win!");
        }else if(!(fourOfAKind(oppsDeck).isEmpty()))
        {
            System.out.println(fourOfAKind(oppsDeck) + " you automatically lose");
        }else if(!(fourOfAKind(playerDeck).isEmpty()) && !(fourOfAKind(oppsDeck).isEmpty())){
            System.out.println("It's a tie! Both players have gotten a four of a kind");
        }

        //first round; player's turn
        while(round)
        {
            while(playerTurn)
            {
                if(!(ronda(playerDeck).isEmpty()))
                {
                    System.out.println(ronda(playerDeck));
                    playerPoints += 4;
                }
                System.out.println(activeDeck);
                System.out.println("What card do you want to play? Type the full name of the card in question");
                String choice = scan.nextLine().trim();
                playCard(choice);
                System.out.println("Would you like to check for a match or addition?");
                String choice2 = scan.nextLine().trim();
                if(choice2.equals("match"))
                {
                    if(matching(choice2))
                    {
                        playerTrick.add(activeDeck.removeLast());
                        playerTrick.add(activeDeck.remove(activeDeck.size()-2));
                    }
                }else if(choice2.equals("addition"))
                {
                    System.out.println("What cards do you wanna check? Type in the name of the card you are playing and the cards you are gonna add. Separate them by commas.");
                    String choice3 = scan.nextLine().trim();
                    addition(choice3);
                }
                System.out.println(activeDeck);
                playerTurn = false;
                oppsTurn = true;
            }

            while(oppsTurn)
            {
                if(!(ronda(oppsDeck).isEmpty()))
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

    public ArrayList<String> getOppsDeck()
    {
        return oppsDeck;
    }

    public static void initializeDeck(ArrayList<String> deck)
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
                    j--;
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
        if(deck.isEmpty())
        {
            return "There are no more cards! The game is over!";
        }else{
            return deck.remove(0);
        }
    }

    public boolean matching(String card)
    {
        String temp1 = card.substring(0,1);
        String temp2 = activeDeck.getLast().substring(0,1);
        if(temp1.equals(temp2))
        {
            return true;
        }else{
            return false;
        }
    }
    public boolean addition(String cards)
    {
        int temp = 0;
        int dividend = 0;
        String regex = ",";
        String[] arr = cards.split(regex);
        for(int i = 0; i < arr.length; i--)
        {
            String card = arr[i];
            if(card.substring(0, 3).equals("Ace"))
            {
                temp += 1;
                if(i == 0)
                {
                    dividend = 1;
                }
            }else if(card.substring(0, 1).equals("2"))
            {
                temp += 2;
                if(i == 0)
                {
                    dividend = 2;
                }
            }else if(card.substring(0, 1).equals("3"))
            {
                temp += 3;
                if(i == 0)
                {
                    dividend = 3;
                }
            }else if(card.substring(0, 1).equals("4"))
            {
                temp += 4;
                if(i == 0)
                {
                    dividend = 4;
                }
            }else if(card.substring(0, 1).equals("5"))
            {
                temp += 5;
                if(i == 0)
                {
                    dividend = 5;
                }
            }else if(card.substring(0, 1).equals("6"))
            {
                temp += 6;
                if(i == 0)
                {
                    dividend = 6;
                }
            }else if(card.substring(0, 1).equals("7"))
            {
                temp += 7;
                if(i == 0)
                {
                    dividend = 7;
                }
            }else if(card.substring(0, 1).equals("J"))
            {
                temp += 11;
                if(i == 0)
                {
                    dividend = 11;
                }
            }else if(card.substring(0, 1).equals("Q"))
            {
                temp += 12;
                if(i == 0)
                {
                    dividend = 12;
                }
            }else if(card.substring(0, 1).equals("K")) {
                temp += 13;
                if(i == 0)
                {
                    dividend = 13;
                }
            }
        }
        if(temp / dividend == 2)
        {
            System.out.println("These cards shall be added to your trick pile");
            return true;
        }else{
            System.out.println("These cards do not work");
            return false;
        }
    }

    public void playCard(String answer)
    {
        for(int i = 0; i < playerDeck.size(); i++)
        {
            if(playerDeck.get(i).equals(answer))
            {
                activeDeck.add(playerDeck.remove(i));
            }
        }
    }

    public void restartCuarenta()
    {
        for(int i = 0; i < 5; i++)
        {
            playerDeck.add(fourtyDeck.removeLast());
        }
        for(int i = 0; i < 5; i++)
        {

            oppsDeck.add(fourtyDeck.removeLast());
        }
        System.out.println("Your cards are:");
        System.out.println(playerDeck);
    }

    public void sequence(String playedCard)
    {
        String result = "";
        boolean b = true;
        for(int i = activeDeck.indexOf(playedCard); (i < activeDeck.size()-1 && b); i++)
        {
            if(activeDeck.get(i+1)-activeDeck.get(i) <=1)
            {

            }else{
                b = false;
            }
        }
    }
}
