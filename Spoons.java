import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;




public class Spoons {


    static Scanner scan = new Scanner(System.in);
    static String[] cardNum = {"A", "1", "2", "3", "4", "5", "6", "7", "J", "Q", "K"};
    static String[] cardType = {"Hearts", "Spades", "Clubs", "Diamonds"};
    static ArrayList<String> originalDeck = new ArrayList<>();
    static ArrayList<String> playerDeck = new ArrayList<>();
    static ArrayList<String> oppsDeck =  new ArrayList<>();
    static ArrayList<String> discardDeck = new ArrayList<>();
    static String oppCardTarget=null;
    static String cardToDiscard=null;
    static boolean isSpoonTaken=false;
    static int randomCardIndex=0;
    static int discardCardIndex=0;
    static boolean playerTurn=true;
    static boolean oppsTurn=false;
    static int cardsThatMatch=0;


    public Spoons() {
        System.out.println("Welcome to Spoons! If you need to learn the rules, you may read them at the bottom of the page\n");
        intitalizeDeck(originalDeck);
        shuffleDeck(originalDeck);




        for (int i = 0; i < 8; i++) {
            if (i % 2 == 0) {
                playerDeck.add(originalDeck.get(i));
            } else {
                oppsDeck.add(originalDeck.get(i));
            }
        }


        while(isSpoonTaken==false) {
            if (playerTurn == true) {
                playerMove();
            }

            if (oppsTurn == true) {
                oppMove();
            }
        }
    }


    public static void intitalizeDeck(ArrayList<String> originalDeck)
    {
        for(int i = 0; i < cardType.length; i++)
        {
            for(int j = 0;j < cardNum.length; j++)
            {
                originalDeck.add(cardNum[j] + " of " + cardType[i]);
            }
        }
    }


    public static void shuffleDeck(ArrayList<String>shuffledDeck)
    {
        Collections.shuffle(shuffledDeck);
    }

    public void playerMove()
    {

        System.out.println("Your turn. Your cards: " + playerDeck);

        String firstCardinPlayerDeck = playerDeck.get(0);
        String firstValue = firstCardinPlayerDeck.substring(0, firstCardinPlayerDeck.indexOf(" "));
        cardsThatMatch = 1;

        for (int i=1; i<playerDeck.size(); i++)
        {
            String nextCardinPlayerDeck = playerDeck.get(i);
            String nextValue = nextCardinPlayerDeck.substring(0, nextCardinPlayerDeck.indexOf(" "));

            if (nextValue.equals(firstValue))
            {
                cardsThatMatch+=1;
            }
        }

        if (cardsThatMatch==4) {
            System.out.println("You picked up a spoon. You win.");
            isSpoonTaken=true;
            playerTurn = false;
            oppsTurn = false;
        }
        else {
            for (int i=0; i<playerDeck.size(); i++) {
                System.out.println(i + ": " + playerDeck.get(i));
            }
            System.out.println("Type in the number of the card you want to pass to opp: ");
            String choice = scan.nextLine().trim();
            String playedCard = playerDeck.remove(Integer.parseInt(choice));
            oppsDeck.add(playedCard);
            System.out.println("You passed: " + playedCard);
            playerDeck.add(originalDeck.remove(0));

            playerTurn = false;
            oppsTurn = true;
        }
        }

    public void oppMove() {

        System.out.println("Opp's turn.");

        String firstCardinOppDeck = oppsDeck.get(0);
        String firstValue = firstCardinOppDeck.substring(0, firstCardinOppDeck.indexOf(" "));
        cardsThatMatch = 1;
        for (int i=1; i<oppsDeck.size(); i++) {
            String nextCardinOppDeck = oppsDeck.get(i);
            String nextValue = nextCardinOppDeck.substring(0, nextCardinOppDeck.indexOf(" "));

            if (nextValue.equals(firstValue))
            {
                cardsThatMatch+=1;
            }

            if (!nextValue.equals(firstValue))
            {
                cardToDiscard=nextCardinOppDeck;
                discardCardIndex=i;
            }


        }

        if (cardsThatMatch>=2) {

            oppCardTarget=firstValue;
        }

        if (cardsThatMatch==4) {

            System.out.println("Opp picked up a spoon. Opp wins.");
            isSpoonTaken=true;
            playerTurn = false;
            oppsTurn = false;
        }

        if (cardToDiscard==null) {

            randomCardIndex=(int)(Math.random()*oppsDeck.size());
            cardToDiscard=oppsDeck.remove(randomCardIndex);
        }
        else {
            oppsDeck.remove(discardCardIndex);
        }
        discardDeck.add(cardToDiscard);
        System.out.println("Opp discard: " + cardToDiscard);
        oppsTurn = false;
        playerTurn = true;
    }
    }

