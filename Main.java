//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main
{
    static String[] cardNum = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    static String[] cardType = {"Hearts", "Spades", "Clubs", "Diamonds"};
    static ArrayList<String> mainDeck = new ArrayList<>();
    static ArrayList<String> activeDeck = new ArrayList<>();
    static Scanner scan = new Scanner(System.in);
    int playerCounts = 0;
    int oppsCounts = 0;
    public static void main(String[] args)
    {
        System.out.println("What card game would you like to play?");
        System.out.println("There is Cuarenta, Spit, Spoons, and BullSh!t");
        System.out.println("Type in the exact title of the game you would like to play");
        String choice = scan.nextLine().trim();
        if(choice.equals("Cuarenta"))
        {
            System.out.println("Sorry, i don't know how to speak Spanish, or Portuguese");
            Cuarenta gameCuarenta = new Cuarenta();
        }else if(choice.equals("Spit"))
        {
            Spit spitGame = new Spit();
        }else if(choice.equals("Spoons"))
        {
            System.out.println("Sorry, but someone took the last of the disposible spoons to some party last night");
            //Spoons spoonGame = new Spoons();
        }else if(choice.equals("BullSh!t"))
        {
            System.out.println("Hey! No curse words!");
            //Bullsht bsGame = new Bullsht();
        }
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

    //Try to figure out a way to fix the code
    public static void shuffleDeck(ArrayList<String> deck)
    {
        //String temp = "";
        //for(int i = 0; i < deck.length; i++)
        {
            //temp = deck[i];
            //deck[i] = deck[Math.random()*deck.length];
            //deck[Math.random()*deck.length] = temp;
            Collections.shuffle(deck);
        }
    }
}
