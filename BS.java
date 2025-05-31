import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BS
{
    static Scanner scan = new Scanner(System.in);
    static String[] cardNum = {"A", "1", "2", "3", "4", "5", "6", "7", "J", "Q", "K"};
    static String[] cardType = {"Hearts", "Spades", "Clubs", "Diamonds"};
    static ArrayList<String> originalDeck = new ArrayList<>();
    static ArrayList<String> playerDeck = new ArrayList<>();
    static ArrayList<String> oppsDeck =  new ArrayList<>();
    static ArrayList<String> activeDeck = new ArrayList<>();
    static int expectedCard = 0;
    static int lastExpectedCard=0;
    static String lastCard="";
    static String trueCard="";
    static boolean playerTurn = true;
    static boolean oppsTurn = false;
    static boolean PlayerCallBS = false;
    static boolean OppCallBS = false;

    public BS()
    {
        System.out.println("Welcome to BS! If you need to learn the rules, you may read them at the bottom of the page\n");
        intitalizeDeck(originalDeck);
        shuffleDeck(originalDeck);


        for (int i=0; i<40; i++)
        {
            if (i%2==0){
                playerDeck.add(originalDeck.get(i));
            }
            else{
                oppsDeck.add(originalDeck.get(i));
                }
        }


        
        while(playerDeck.size()!=0 && oppsDeck.size()!=0)
        {
            if (activeDeck.size()==0)
            {
                whoHasAce();

            }
            if (playerTurn==true)
            {
                playerMove();
            }

            if(oppsTurn==true)
            {
                oppMove();
            }
        }

        if (playerDeck.size()==0) {
            System.out.println("Opp wins, player loses");
        }

        if (oppsDeck.size()==0) {
            System.out.println("Player wins, opp loses");
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

        public static void whoHasAce()
        {

            boolean playerHasAce=false;
            boolean oppHasAce=false;

            for (int i=0; i<playerDeck.size(); i++)
            {
                if (playerDeck.get(i).equals("A of Spades"))
                {
                    playerHasAce=true;
                }
            }

            for (int i=0; i<oppsDeck.size(); i++)
            {
                if (oppsDeck.get(i).equals("A of Spades"))
                {
                    oppHasAce=true;
                }
            }

            if (playerHasAce==true)
            {
                System.out.println("Player has Ace of Spades and will start");
                playerTurn=true;
                oppsTurn=false;
                OppCallBS=false;
                PlayerCallBS=false;
            }

            if(oppHasAce==true)
            {
                System.out.println("Opp has Ace of Spades and will start");
                playerTurn=false;
                oppsTurn=true;
                PlayerCallBS=false;
                OppCallBS=false;
            }

        }

        public void playerMove()
        {
            OppCallBS=false;
            PlayerCallBS=true;

            System.out.println("Your turn. Your cards: " + playerDeck);
            System.out.println("Do you want to place down a card or call BS?");
            String choice = scan.nextLine().trim();
            if (choice.equals("card"))
            {   for (int i=1; i<playerDeck.size(); i++)
                {
                System.out.println(i + ": " + playerDeck.get(i));
                }
                System.out.println("Type in the number of the card you want to place");
                String choice1 = scan.nextLine().trim();
                String playedCard = playerDeck.remove(Integer.parseInt(choice1));
                activeDeck.add(playedCard);
                System.out.println("You put down: " + playedCard);
                expectedCard = expectedCard + 1;
                playerTurn = false;
                PlayerCallBS=false;
                oppsTurn = true;
            }

            if (choice.equals("BS") && PlayerCallBS==true)
            {
                lastCard = activeDeck.get(activeDeck.size() - 1);
                if (expectedCard != 0) {
                    lastExpectedCard = cardNum.length - 1;
                } else {
                    lastExpectedCard = expectedCard - 1;
                }

                trueCard = lastCard.substring(0, lastCard.indexOf(" "));

                if (!(trueCard.equals(lastExpectedCard))) {
                    System.out.println("Opp was BSing their card. Opp picks up the deck");
                    System.out.println("ExpectedCard: " + lastExpectedCard);
                    System.out.println("True card:" + trueCard);
                    lastExpectedCard=0;
                    playerTurn = false;
                    oppsTurn = true;
                    for (int i = 0; i < activeDeck.size(); i++) {
                        oppsDeck.add(activeDeck.get(i));
                        activeDeck.clear();
                    }
                    System.out.println("They now have " + oppsDeck);
                } else {
                    System.out.println("Opp put down the right card. Player picks up the deck.");
                    lastExpectedCard=0;
                    playerTurn = false;
                    oppsTurn = true;
                    for (int i = 0; i < activeDeck.size(); i++) {
                        playerDeck.add(activeDeck.get(i));
                        activeDeck.clear();
                    }
                    PlayerCallBS=false;
                    System.out.println("You now have " + playerDeck);
            }}}

            public static void oppMove()
            {
                OppCallBS=true;
                PlayerCallBS=false;
                if (Math.random()<0.3 && OppCallBS==true) {
                    OppCallBS=false;
                    System.out.println("Opp calls BS");
                    lastCard = activeDeck.get(activeDeck.size() - 1);

                    if (expectedCard != 0) {
                        lastExpectedCard = cardNum.length - 1;
                    } else {
                        lastExpectedCard = expectedCard - 1;
                    }

                    trueCard = lastCard.substring(0, lastCard.indexOf(" "));

                    if (!(trueCard.equals(lastExpectedCard))) {
                        System.out.println("Player was BSing their card. Player picks up the deck");
                        System.out.println("ExpectedCard: " + lastExpectedCard);
                        System.out.println("True card:" + trueCard);
                        lastExpectedCard = 0;
                        playerTurn = true;
                        oppsTurn = false;
                        for (int i = 0; i < activeDeck.size(); i++) {
                            playerDeck.add(activeDeck.get(i));
                            activeDeck.clear();
                        }
                        System.out.println("You now have " + playerDeck);
                    } else {
                        System.out.println("Player put the right card. Opp picks up the deck.");
                        lastExpectedCard = 0;
                        for (int i = 0; i < activeDeck.size(); i++) {
                            oppsDeck.add(activeDeck.get(i));
                            activeDeck.clear();
                        }
                        playerTurn = true;
                        oppsTurn = false;
                        System.out.println("They now have " + oppsDeck);
                    }}

                        System.out.println("Opp is placing a card. The expected card is: " + cardNum[expectedCard]);
                        String playedCard = playerDeck.remove(0);
                        activeDeck.add(playedCard);
                        playerTurn = true;
                        oppsTurn = false;
                        OppCallBS=true;


                }

            }







