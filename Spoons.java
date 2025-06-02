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
    static ArrayList<String> activeDeck = new ArrayList<>();
    static String playerCardTarget;
    static String oppCardTarget;
    static boolean isSpoonTaken=false;


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




}

