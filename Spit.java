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
    boolean gameActive = false;
    boolean playerWin = false;
    boolean oppsWin = false;
    int playerCards = 0;
    int oppsCards = 0;

    public Spit()
    {
        //intro
        System.out.println("Welcome to Spit! If you need to learn the rules, you may read them at the bottom of the page\n");
        intitalizeSpit();
        gameActive = true;
        //play loop
        while(gameActive)
        {
            if(playerWin || oppsWin)
            {
                gameActive = false;
                if(playerWin)
                {
                    System.out.println("Congratulations, you win the game!");
                }else if(oppsWin)
                {
                    System.out.println("Sorry, but it looks like your opponent won the game, better luck next time!");
                }
            }else{
                System.out.println("\nWhat card would you like to play in your pile? (Type the card's full name)");
                String choice = scan.nextLine().trim();
                playCard(choice);
                oppsPlayCard();
                winCheck();
                //To display the decks to the player after cards have been played
                System.out.println("Your cards are as follows:\n" + "Pile 1: "+ playerOne.get(0)+"\nPile 2: " + playerTwo.get(0) + "\nPile 3: " + playerThree.get(0)+ "\nPile 4: " + playerFour.get(0)+ "\nPile 5: " + playerFive.get(0) + "\nYour current hand: " + playerDeck.get(0));
                System.out.println("Your center pile has the " + playerPile.get(0) + " and your opponents pile has the " + oppsPile.get(0));
                if()
                {

                }
            }
        }

    }

    //sets up the decks for the game
    public void intitalizeSpit()
    {
        playerDeck.clear();
        oppsDeck.clear();
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
        //This took quite a bit of time to finalize
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
        playerPile.add(playerDeck.remove(0));
        oppsPile.add(oppsDeck.remove(0));
        System.out.println("Your cards are as follows:\n" + "Pile 1: "+ playerOne.get(0)+"\nPile 2: " + playerTwo.get(0) + "\nPile 3: " + playerThree.get(0)+ "\nPile 4: " + playerFour.get(0)+ "\nPile 5: " + playerFive.get(0) + "\nYour current hand: " + playerDeck.get(0));
        System.out.println("Your center pile has been set with the " + playerPile.get(0) + " and your opponents pile has been set with " + oppsPile.get(0));
    }

    public void playCard(String choice)
    {
        if(choice.equals(playerOne.get(0)))
        {
            if(checkCard(playerPile, choice))
            {
                playerPile.add(playerOne.remove(0));
            }
        }else if(choice.equals(playerTwo.get(0)))
        {
            if(checkCard(playerPile, choice))
            {
                playerPile.add(playerTwo.remove(0));
            }
        }else if(choice.equals(playerThree.get(0)))
        {
            if(checkCard(playerPile, choice))
            {
                playerPile.add(playerThree.remove(0));
            }
        }else if(choice.equals(playerFour.get(0)))
        {
            if(checkCard(playerPile, choice))
            {
                playerPile.add(playerFour.remove(0));
            }
        }else if(choice.equals(playerFive.get(0)))
        {
            if(checkCard(playerPile, choice))
            {
                playerPile.add(playerFive.remove(0));
            }
        }else if(choice.equals(playerDeck.get(0)))
        {
            if(checkCard(playerPile, choice))
            {
                playerPile.add(playerDeck.remove(0));
            }
        }else{
            System.out.println("Can't play that!, try another.");
            String choice2 = scan.nextLine().trim();
            playCard(choice2);
        }
    }

    //checks to be sure the card could be played or not(this took a while)
    public boolean checkCard(ArrayList<String> deck, String card)
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
    
    //specifically for the computer, not to be shown to player
    private void oppsPlayCard()
    {
        if(checkCard(oppsPile, oppsOne.get(0)))
        {
            oppsPile.add(oppsOne.remove(0));
        }else if(checkCard(oppsPile, oppsTwo.get(0)))
        {
            oppsPile.add(oppsTwo.remove(0));
        }else if(checkCard(oppsPile, oppsThree.get(0)))
        {
            oppsPile.add(oppsThree.remove(0));
        }else if(checkCard(oppsPile, oppsFour.get(0)))
        {
            oppsPile.add(oppsFour.remove(0));
        }else if(checkCard(oppsPile, oppsFive.get(0)))
        {
            oppsPile.add(playerFive.remove(0));
        }else if(checkCard(oppsPile, oppsDeck.get(0)))
        {
                oppsPile.add(oppsDeck.remove(0));
        }
    }

    public void winCheck()
    {
        if(playerDeck.size()==0)
        {
            playerWin = true;
        }else if(oppsDeck.size()==0)
        {
            oppsWin = true;
        }
    }

    public void pileSlap()
    {
        //int oppsWait = (int)(Math.random()*4);
        int desiredPile = 0;
        int otherPile = 0;
        int dSize = 0;
        int oSize = 0;
        System.out.println("First pile has " + playerPile.size() + " cards and the second has " + oppsPile.size() + " cards");
        System.out.println("Quick! Type 1 or 2 respectively for the pile you want to slap!");
        String choice = scan.nextLine().trim();
        if (Math.random()<0.3)
        {
            scan.close();
            if(playerPile.size() > oppsPile.size())
            {
                desiredPile = 1;
                otherPile = 2;
            }else{
                desiredPile = 2;
                otherPile = 1;
            }
            System.out.println("Too late, your opponent has already claimed Pile " +  desiredPile + ". You get pile " + otherPile);
        }else{
            desiredPile = Integer.parseInt(choice);
            otherPile = 3 - desiredPile;
        }

        if(desiredPile == 1)
        {
            dSize = playerPile.size();
            oSize = oppsPile.size();
            for(int i = 0; i < dSize; i++)
            {
                playerDeck.add(playerPile.remove(0));
            }
            for(int i = 0; i < oSize; i++)
            {
                oppsDeck.add(oppsPile.remove(0));
            }
        }else if(desiredPile == 2)
        {
            oSize = playerPile.size();
            dSize = oppsPile.size();
            for(int i = 0; i < oSize; i++)
            {
                playerDeck.add(oppsPile.remove(0));
            }
            for(int i = 0; i < dSize; i++)
            {
                oppsDeck.add(playerPile.remove(0));
            }
        }
    }

    private int calculateCards()
    {
        return 0;
    }
}
