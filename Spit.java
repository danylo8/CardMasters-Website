import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Spit extends Main
{
    //i forgot why i made everything static tbh, probably nothing to worry about
    static String[] cardNum = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    static String[] cardType = {"Hearts", "Spades", "Clubs", "Diamonds"};
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
    String choice = "";

    public Spit()
    {
        //intro
        System.out.println("Welcome to Spit! If you need to learn the rules, you may take a moment to do so\n");
        intitalizeSpit();
        gameActive = true;
        //play loop
        while(gameActive)
        {
            //if someone wins
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
                System.out.println("\nWhat card would you like to play? (Type the card's full name)");
                System.out.println("If you wish to look for another card in your main deck to play, type in Other Card, if not then type in Skip");
                choice = scan.nextLine().trim();
                playCard(choice);
                System.out.println("What pile would you like to play the card in? (Type the corresponding number to the pile of your choice:\nPile One = 1, Pile Two = 2, Pile Three = 3, Pile Four = 4, Pile Five = 5, your center pile = 6, and the 2nd center pile = 7)");
                String pileChoice = scan.nextLine().trim();
                playCard(choice, pileChoice);
                oppsPlayCard();
                playerCards = calculatePlayerCards();
                oppsCards = calculateOppsCards();
                winCheck();
                //To display the decks to the player after cards have been played
                System.out.println("Your cards are as follows:\n" + "Pile 1: "+ playerOne.get(playerOne.size()-1)+"\nPile 2: " + playerTwo.get(playerTwo.size()-1) + "\nPile 3: " + playerThree.get(playerThree.size()-1)+ "\nPile 4: " + playerFour.get(playerFour.size()-1)+ "\nPile 5: " + playerFive.get(playerFive.size()-1) + "\nYour current hand: " + playerDeck.get(playerDeck.size()-1));
                System.out.println("Your center pile has the " + playerPile.get(playerPile.size()-1) + " and your opponents pile has the " + oppsPile.get(oppsPile.size()-1));
                if(playerCards == playerDeck.size() || oppsCards == oppsDeck.size())
                {
                    pileSlap();
                    reshuffleDeck();
                }
            }
        }

    }

    //sets up the decks for the game
    public void intitalizeSpit()
    {
        playerDeck.clear();
        oppsDeck.clear();
        intitalizeDeck(mainDeck);
        shuffleDeck(mainDeck);
        playerDeck.add("There's no cards in this pile");
        oppsDeck.add("There's no cards in this pile");
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
        playerOne.add("There's no cards in this pile");
        oppsOne.add("There's no cards in this pile");
        playerTwo.add("There's no cards in this pile");
        oppsTwo.add("There's no cards in this pile");
        playerThree.add("There's no cards in this pile");
        oppsThree.add("There's no cards in this pile");
        playerFour.add("There's no cards in this pile");
        oppsFour.add("There's no cards in this pile");
        playerFive.add("There's no cards in this pile");
        oppsFive.add("There's no cards in this pile");
        playerPile.add("There's no cards in this pile");
        oppsPile.add("There's no cards in this pile");
        playerOne.add(playerDeck.remove(1));
        oppsOne.add(oppsDeck.remove(1));
        for(int i = 0; i < 2; i++)
        {
            playerTwo.add(playerDeck.remove(1));
            oppsTwo.add(oppsDeck.remove(1));
        }
        for(int i = 0; i < 3; i++)
        {
            playerThree.add(playerDeck.remove(1));
            oppsThree.add(oppsDeck.remove(1));
        }
        for(int i = 0; i < 4; i++)
        {
            playerFour.add(playerDeck.remove(1));
            oppsFour.add(oppsDeck.remove(1));
        }
        for(int i = 0; i < 5; i++)
        {
            playerFive.add(playerDeck.remove(1));
            oppsFive.add(oppsDeck.remove(1));
        }
        playerPile.add(playerDeck.remove(1));
        oppsPile.add(oppsDeck.remove(1));
        System.out.println("Your cards are as follows:\n" + "Pile 1: "+ playerOne.get(playerOne.size()-1)+"\nPile 2: " + playerTwo.get(playerTwo.size()-1) + "\nPile 3: " + playerThree.get(playerThree.size()-1)+ "\nPile 4: " + playerFour.get(playerFour.size()-1)+ "\nPile 5: " + playerFive.get(playerFive.size()-1) + "\nYour current hand: " + playerDeck.get(playerDeck.size()-1));
        System.out.println("Your center pile has been set with the " + playerPile.get(playerPile.size()-1) + " and your opponents pile has been set with the " + oppsPile.get(oppsPile.size()-1));
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

    public void playCard(String answer)
    {
        if(answer.equals("Other Card"))
        {
            oppsPlayCard();
            String og = playerDeck.get(playerDeck.size()-1);
            int chance = (int)(Math.random() * playerDeck.size()-1);
            if(chance == 0)
            {
                chance++;
            }
            String temp = playerDeck.get(chance);
            playerDeck.set(chance, og);
            playerDeck.set(playerDeck.size()-1, temp);
            System.out.println("\nYour cards are as follows:\n" + "Pile 1: "+ playerOne.get(playerOne.size()-1)+"\nPile 2: " + playerTwo.get(playerTwo.size()-1) + "\nPile 3: " + playerThree.get(playerThree.size()-1)+ "\nPile 4: " + playerFour.get(playerFour.size()-1)+ "\nPile 5: " + playerFive.get(playerFive.size()-1) + "\nYour current hand: " + playerDeck.get(playerDeck.size()-1));
            System.out.println("Your center pile has the " + playerPile.get(playerPile.size()-1) + " and your opponents pile has the " + oppsPile.get(oppsPile.size()-1)+"\n");
            System.out.println("What card would you like to play? (Type the card's full name)");
            System.out.println("If you wish to look for another card in your main deck to play, type in Other Card, if not then type in Skip");
            String choice2 = scan.nextLine().trim();
            playCard(choice2);
            oppsPlayCard();
        }else if(answer.equals("Skip")){
            System.out.println("\nWhat card would you like to play? (Type the card's full name)");
            choice = scan.nextLine().trim();
        }
    }

    public void playCard(String choice, String pile)
    {
        if(choice.equals(playerOne.get(playerOne.size()-1)))
        {
            if(pile.equals("2") && checkCard(playerTwo, choice))
            {
                playerPile.add(playerOne.remove(playerOne.size()-1));
            }else if(pile.equals("3") && checkCard(playerThree, choice))
            {
                playerThree.add(playerOne.remove(playerOne.size()-1));
            }else if(pile.equals("4") && checkCard(playerFour, choice))
            {
                playerFour.add(playerOne.remove(playerOne.size()-1));
            }else if(pile.equals("5") && checkCard(playerFive, choice))
            {
                playerFive.add(playerOne.remove(playerOne.size()-1));
            }else if(pile.equals("6") && checkCard(playerPile, choice))
            {
                playerPile.add(playerOne.remove(playerOne.size()-1));
            }else if(pile.equals("7") && checkCard(oppsPile, choice))
            {
                oppsPile.add(playerOne.remove(playerOne.size()-1));
            }
        }else if(choice.equals(playerTwo.get(playerTwo.size()-1)))
        {
            if(pile.equals("1") && (checkCard(playerOne, choice)|| playerOne.size() == 1))
            {
                playerOne.add(playerTwo.remove(playerTwo.size()-1));
            }else if(pile.equals("3") && (checkCard(playerThree, choice)|| playerThree.size() == 1))
            {
                playerThree.add(playerTwo.remove(playerTwo.size()-1));
            }else if(pile.equals("4") && (checkCard(playerFour, choice)|| playerFour.size() == 1))
            {
                playerFour.add(playerTwo.remove(playerTwo.size()-1));
            }else if(pile.equals("5") && (checkCard(playerFive, choice)|| playerFive.size() == 1))
            {
                playerFive.add(playerTwo.remove(playerTwo.size()-1));
            }else if(pile.equals("6") && checkCard(playerPile, choice))
            {
                playerPile.add(playerTwo.remove(playerTwo.size()-1));
            }else if(pile.equals("7") && checkCard(oppsPile, choice))
            {
                oppsPile.add(playerTwo.remove(playerTwo.size()-1));
            }
        }else if(choice.equals(playerThree.get(playerThree.size()-1)))
        {
            if(pile.equals("1") && (checkCard(playerOne, choice)|| playerOne.size() == 1))
            {
                playerOne.add(playerThree.remove(playerThree.size()-1));
            }else if(pile.equals("2") && (checkCard(playerTwo, choice)|| playerTwo.size() == 1))
            {
                playerTwo.add(playerThree.remove(playerThree.size()-1));
            }else if(pile.equals("4") && (checkCard(playerFour, choice)|| playerFour.size() == 1))
            {
                playerFour.add(playerThree.remove(playerThree.size()-1));
            }else if(pile.equals("5") && (checkCard(playerFive, choice)|| playerFive.size() == 1))
            {
                playerFive.add(playerThree.remove(playerThree.size()-1));
            }else if(pile.equals("6") && checkCard(playerPile, choice))
            {
                playerPile.add(playerThree.remove(playerThree.size()-1));
            }else if(pile.equals("7") && checkCard(oppsPile, choice))
            {
                oppsPile.add(playerThree.remove(playerThree.size()-1));
            }
        }else if(choice.equals(playerFour.get(playerFour.size()-1)))
        {
            if(pile.equals("1") && (checkCard(playerOne, choice)|| playerOne.size() == 1))
            {
                playerOne.add(playerFour.remove(playerFour.size()-1));
            }else if(pile.equals("2") && (checkCard(playerTwo, choice)|| playerTwo.size() == 1))
            {
                playerTwo.add(playerFour.remove(playerFour.size()-1));
            }else if(pile.equals("3") && (checkCard(playerThree, choice)|| playerThree.size() == 1))
            {
                playerThree.add(playerFour.remove(playerFour.size()-1));
            }else if(pile.equals("5") && (checkCard(playerFive, choice)|| playerFive.size() == 1))
            {
                playerFive.add(playerFour.remove(playerFour.size()-1));
            }else if(pile.equals("6") && checkCard(playerPile, choice))
            {
                playerPile.add(playerFour.remove(playerFour.size()-1));
            }else if(pile.equals("7") && checkCard(oppsPile, choice))
            {
                oppsPile.add(playerFour.remove(playerFour.size()-1));
            }
        }else if(choice.equals(playerFive.get(playerFive.size()-1)))
        {
            if(pile.equals("1") && (checkCard(playerOne, choice)|| playerOne.size() == 1))
            {
                playerOne.add(playerFive.remove(playerFive.size()-1));
            }else if(pile.equals("2") && (checkCard(playerTwo, choice)|| playerTwo.size() == 1))
            {
                playerTwo.add(playerFive.remove(playerFive.size()-1));
            }else if(pile.equals("3") && (checkCard(playerThree, choice)|| playerThree.size() == 1))
            {
                playerThree.add(playerFive.remove(playerFive.size()-1));
            }else if(pile.equals("4") && (checkCard(playerFour, choice)|| playerFour.size() == 1))
            {
                playerFour.add(playerFive.remove(playerFive.size()-1));
            }else if(pile.equals("6") && checkCard(playerPile, choice))
            {
                playerPile.add(playerThree.remove(playerThree.size()-1));
            }else if(pile.equals("7") && checkCard(oppsPile, choice))
            {
                oppsPile.add(playerThree.remove(playerThree.size()-1));
            }
        }else if(choice.equals(playerDeck.get(playerDeck.size()-1)))
        {
            if(pile.equals("1") && (checkCard(playerOne, choice)|| playerOne.size() == 1))
            {
                playerOne.add(playerDeck.remove(playerDeck.size()-1));
            }else if(pile.equals("2") && (checkCard(playerTwo, choice)|| playerTwo.size() == 1))
            {
                playerTwo.add(playerDeck.remove(playerDeck.size()-1));
            }else if(pile.equals("3") && (checkCard(playerThree, choice)|| playerThree.size() == 1))
            {
                playerThree.add(playerDeck.remove(playerDeck.size()-1));
            }else if(pile.equals("4") && (checkCard(playerFour, choice)|| playerFour.size() == 1))
            {
                playerFour.add(playerDeck.remove(playerDeck.size()-1));
            }else if(pile.equals("5") && (checkCard(playerFive, choice)|| playerFive.size() == 1))
            {
                playerFive.add(playerDeck.remove(playerDeck.size()-1));
            }else if(pile.equals("6") && checkCard(playerPile, choice))
            {
                playerPile.add(playerDeck.remove(playerDeck.size()-1));
            }else if(pile.equals("7") && checkCard(oppsPile, choice))
            {
                oppsPile.add(playerDeck.remove(playerDeck.size()-1));
            }
        }else{
            System.out.println("Can't play that!, try another. If you wish to, you may type in Other Card");
            String choice2 = scan.nextLine().trim();
            playCard(choice2);
            System.out.println("What pile would you like to play the card in? (Type the corresponding number to the pile of your choice:\nPile One = 1, Pile Two = 2, Pile Three = 3, Pile Four = 4, Pile Five = 5, and your center pile = 6)");
            String pileChoice2 = scan.nextLine().trim();
            playCard(choice2, pileChoice2);
        }
    }

    //checks to be sure the card could be played or not(this took a while)
    public boolean checkCard(ArrayList<String> deck, String card)
    {
        int refer = 0;
        int temp = 0;
        if(deck.get(deck.size()-1).substring(0, 1).equals("Ace"))
        {
            refer = 1;
        }else if(deck.get(deck.size()-1).substring(0, 1).equals("2"))
        {
            refer = 2;
        }else if(deck.get(deck.size()-1).substring(0, 1).equals("3"))
        {
            refer = 3;
        }else if(deck.get(deck.size()-1).substring(0, 1).equals("4"))
        {
            refer = 4;
        }else if(deck.get(deck.size()-1).substring(0, 1).equals("5"))
        {
            refer = 5;
        }else if(deck.get(deck.size()-1).substring(0, 1).equals("6"))
        {
            refer = 6;
        }else if(deck.get(deck.size()-1).substring(0, 1).equals("7"))
        {
            refer = 7;
        }else if(deck.get(deck.size()-1).substring(0, 1).equals("8"))
        {
            refer = 8;
        }else if(deck.get(deck.size()-1).substring(0, 1).equals("9"))
        {
            refer = 9;
        }else if(deck.get(deck.size()-1).substring(0, 1).equals("10"))
        {
            refer = 10;
        }else if(deck.get(deck.size()-1).substring(0, 1).equals("J"))
        {
            refer = 11;
        }else if(deck.get(deck.size()-1).substring(0, 1).equals("Q"))
        {
            refer = 12;
        }else if(deck.get(deck.size()-1).substring(0, 1).equals("K"))
        {
            refer = 13;
        }
        if(card.substring(0, 1).equals("Ace"))
        {
            temp = 1;
        }else if(card.substring(0, 1).equals("2"))
        {
            temp = 2;
        }else if(card.substring(0, 1).equals("3"))
        {
            temp = 3;
        }else if(card.substring(0, 1).equals("4"))
        {
            temp = 4;
        }else if(card.substring(0, 1).equals("5"))
        {
            temp = 5;
        }else if(card.substring(0, 1).equals("6"))
        {
            temp = 6;
        }else if(card.substring(0, 1).equals("7"))
        {
            temp = 7;
        }else if(card.substring(0, 1).equals("8"))
        {
            temp = 8;
        }else if(card.substring(0, 1).equals("9"))
        {
            temp = 9;
        }else if(card.substring(0, 1).equals("10"))
        {
            temp = 10;
        }else if(card.substring(0, 1).equals("J"))
        {
            temp = 11;
        }else if(card.substring(0, 1).equals("Q"))
        {
            temp = 12;
        }else if(card.substring(0, 1).equals("K"))
        {
            temp = 13;
        }
        if(Math.abs(refer-temp) <= 1 || Math.abs(refer-temp) >= 12)
        {
            return true;
        }else{
            return false;
        }
    }

    //specifically for the computer, not to be shown to player
    private void oppsPlayCard()
    {
        if(oppsOne.size() != 1)
        {
            if(checkCard(oppsPile, oppsOne.get(1)))
            {
                oppsPile.add(oppsOne.remove(oppsOne.size()-1));
                System.out.println("Your opponent has played a " + oppsPile.get(oppsPile.size()-1));
            }else if(checkCard(playerPile, oppsOne.get(1)))
            {
                playerPile.add(oppsOne.remove(oppsOne.size()-1));
                System.out.println("Your opponent has played a " + playerPile.get(playerPile.size()-1));
            }
        }else if(oppsTwo.size() != 1){
            if(checkCard(oppsPile, oppsTwo.get(oppsTwo.size()-1)))
            {
                oppsPile.add(oppsTwo.remove(oppsTwo.size()-1));
                System.out.println("Your opponent has played a " + oppsPile.get(oppsPile.size()-1));
            }else if(checkCard(playerPile, oppsTwo.get(oppsTwo.size()-1)))
            {
                playerPile.add(oppsTwo.remove(oppsTwo.size()-1));
                System.out.println("Your opponent has played a " + playerPile.get(playerPile.size()-1));
            }
        }else if(oppsThree.size() != 1)
        {
            if(checkCard(oppsPile, oppsThree.get(oppsThree.size()-1)))
            {
                oppsPile.add(oppsThree.remove(oppsThree.size()-1));
                System.out.println("Your opponent has played a " + oppsPile.get(oppsPile.size()-1));
            }else if(checkCard(playerPile, oppsThree.get(oppsThree.size()-1)))
            {
                playerPile.add(oppsThree.remove(oppsThree.size()-1));
                System.out.println("Your opponent has played a " + playerPile.get(playerPile.size()-1));
            }
        }else if(oppsFour.size() != 1)
        {
            if(checkCard(oppsPile, oppsFour.get(oppsFour.size()-1)))
            {
                oppsPile.add(oppsFour.remove(oppsFour.size()-1));
                System.out.println("Your opponent has played a " + oppsPile.get(oppsPile.size()-1));
            }else if(checkCard(playerPile, oppsFour.get(oppsFour.size()-1)))
            {
                playerPile.add(oppsFour.remove(oppsFour.size()-1));
                System.out.println("Your opponent has played a " + playerPile.get(playerPile.size()-1));
            }
        }else if(oppsFive.size() != 1)
        {
            if(checkCard(oppsPile, oppsFive.get(oppsFive.size()-1)))
            {
                oppsPile.add(playerFive.remove(oppsFive.size()-1));
                System.out.println("Your opponent has played a " + oppsPile.get(oppsPile.size()-1));
            }else if(checkCard(playerPile, oppsFive.get(oppsFive.size()-1)))
            {
                playerPile.add(oppsFive.remove(oppsFive.size()-1));
                System.out.println("Your opponent has played a " + playerPile.get(playerPile.size()-1));
            }
        }else if(oppsDeck.size() != 1)
        {
            if(checkCard(oppsPile, oppsDeck.get(oppsDeck.size()-1)))
            {
                oppsPile.add(oppsDeck.remove(oppsDeck.size()-1));
                System.out.println("Your opponent has played a " + oppsPile.get(oppsPile.size()-1));
            }else if(checkCard(playerPile, oppsDeck.get(oppsDeck.size()-1)))
            {
                playerPile.add(oppsDeck.remove(oppsDeck.size()-1));
                System.out.println("Your opponent has played a " + playerPile.get(playerPile.size()-1));
            }
        }else{
            System.out.println("Your opponent has not played a card yet");
        }
    }

    public void winCheck()
    {
        if(playerCards == 6)
        {
            playerWin = true;
        }else if(oppsCards == 6)
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
            desiredPile = Integer.valueOf(choice);
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
        if(playerOne.get(0) != "There's no cards in this pile")
        {
            playerDeck.add(playerOne.remove(0));
        }
        if(oppsOne.get(0) != "There's no cards in this pile")
        {
            oppsDeck.add(oppsOne.remove(0));
        }
        for(int i = 0; i < 2; i++)
        {
            if(playerTwo.get(0) != "There's no cards in this pile")
            {
                playerDeck.add(playerTwo.remove(0));
            }
            if(oppsTwo.get(0) != "There's no cards in this pile")
            {
                oppsDeck.add(oppsTwo.remove(0));
            }
        }
        for(int i = 0; i < 3; i++)
        {
            if(playerThree.get(0) != "There's no cards in this pile")
            {
                playerDeck.add(playerThree.remove(0));
            }
            if(oppsThree.get(0) != "There's no cards in this pile")
            {
                oppsDeck.add(oppsThree.remove(0));
            }
        }
        for(int i = 0; i < 4; i++)
        {
            if(playerFour.get(0) != "There's no cards in this pile")
            {
                playerDeck.add(playerFour.remove(0));
            }
            if(oppsFour.get(0) != "There's no cards in this pile")
            {
                oppsDeck.add(oppsFour.remove(0));
            }
        }
        for(int i = 0; i < 5; i++)
        {
            if(playerFive.get(0) != "There's no cards in this pile")
            {
                playerDeck.add(playerFive.remove(0));
            }
            if(oppsFive.get(0) != "There's no cards in this pile")
            {
                oppsDeck.add(oppsFive.remove(0));
            }
        }
    }

    private int calculatePlayerCards()
    {
        int result = 0;
        result += playerDeck.size();
        result += playerOne.size();
        result += playerTwo.size();
        result += playerThree.size();
        result += playerFour.size();
        result += playerFive.size();
        return result;
    }

    private int calculateOppsCards()
    {
        int result = 0;
        result += oppsDeck.size();
        result += oppsOne.size();
        result += oppsTwo.size();
        result += oppsThree.size();
        result += oppsFour.size();
        result += oppsFive.size();
        return result;
    }

    public void reshuffleDeck()
    {
        shuffleDeck(playerDeck);
        shuffleDeck(oppsDeck);
        playerOne.add(playerDeck.remove(playerDeck.size()-1));
        oppsOne.add(oppsDeck.remove(oppsDeck.size()-1));
        for(int i = 0; i < 5; i++)
        {
            if(i <=1)
            {
                if(playerDeck.get(0) != "There's no cards in this pile")
                {
                    playerTwo.add(playerDeck.remove(playerDeck.size()-1));
                }
                if(oppsDeck.get(0) != "There's no cards in this pile")
                {
                    oppsTwo.add(oppsDeck.remove(oppsDeck.size()-1));
                }
            }
            if(i <= 2)
            {
                if(playerDeck.get(0) != "There's no cards in this pile")
                {
                    playerThree.add(playerDeck.remove(playerDeck.size()-1));
                }
                if(oppsDeck.get(0) != "There's no cards in this pile")
                {
                    oppsThree.add(oppsDeck.remove(oppsDeck.size()-1));
                }
            }
            if(i <= 3)
            {
                if(playerDeck.get(0) != "There's no cards in this pile")
                {
                    playerFour.add(playerDeck.remove(playerDeck.size()-1));
                }
                if(oppsDeck.get(0) != "There's no cards in this pile")
                {
                    oppsFour.add(oppsDeck.remove(oppsDeck.size()-1));
                }
            }
            if(i <= 4)
            {
                if(playerDeck.get(0) != "There's no cards in this pile")
                {
                    playerFive.add(playerDeck.remove(playerDeck.size()-1));
                }
                if(oppsDeck.get(0) != "There's no cards in this pile")
                {
                    oppsFive.add(oppsDeck.remove(oppsDeck.size()-1));
                }
            }
        }
        if(playerDeck.get(0) != "There's no cards in this pile")
        {
            playerPile.add(playerDeck.remove(playerDeck.size()-1));
        }
        if(oppsDeck.get(0) != "There's no cards in this pile")
        {
            oppsPile.add(oppsDeck.remove(oppsDeck.size()-1));
        }
    }
}
