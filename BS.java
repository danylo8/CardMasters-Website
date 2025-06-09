import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BS
{
    static Scanner scan = new Scanner(System.in);
    static String[] cardNum = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "J", "Q", "K"};
    static String[] cardType = {"Hearts", "Spades", "Clubs", "Diamonds"};
    static ArrayList<String> originalDeck = new ArrayList<>();
    static ArrayList<String> playerDeck = new ArrayList<>();
    static ArrayList<String> oppsDeck =  new ArrayList<>();
    static ArrayList<String> activeDeck = new ArrayList<>();
    static int expectedCard = 0;
    static int lastExpectedCard=0;
    static int widthOfCards=0;
    static int spacingOfCards=0;
    static String lastCard="";
    static String trueCard="";
    static boolean playerTurn = false;
    static boolean oppsTurn = false;
    static boolean PlayerCallBS = false;
    static boolean OppCallBS = false;
    static boolean checkBS=false;


    static JTextArea log=new JTextArea();
    static JButton button= new JButton("Call BS");
    static JLabel activeDeckUI=new JLabel();
    static JPanel playerDeckUI=new JPanel();
    static JLabel mascotImageUI=new JLabel();
    static JLabel playerImageUI=new JLabel();
    static JLabel activeDeckTable=new JLabel();

    public BS()
    {
        JFrame frame = new JFrame();
        frame.setTitle("BS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(720,600);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setResizable(false);


        button=new JButton("Call BS");
        button.setBounds(300,400,100,20);
        frame.add(button);

        button.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 checkBS=true;
                 if (expectedCard >= 2
                 ) {
                     log.append("Player called BS\n");
                 }
                 playerMove();

                 checkBS=false;
         }});


        log.setEditable(false);
        frame.setVisible(true);


        JScrollPane scrollPane=new JScrollPane(log);
        frame.add(scrollPane);
        frame.setVisible(true);
        scrollPane.setBounds(50,430,590,100);
        frame.add(scrollPane);

        frame.setVisible(true);


        ImageIcon mascotImage=new ImageIcon("images/Mascot_Overhead.png");
        mascotImageUI= new JLabel(mascotImage);
        mascotImageUI.setBounds(275,20,160,200);
        frame.add(mascotImageUI);
        mascotImageUI.revalidate();
        mascotImageUI.repaint();

        ImageIcon playerImage=new ImageIcon("images/Player_Overhead.png");
        playerImageUI= new JLabel(playerImage);
        playerImageUI.setBounds(340,115,160,200);
        frame.add(playerImageUI);
        playerImageUI.revalidate();
        playerImageUI.repaint();

        ImageIcon backCard=new ImageIcon("cards/BACK.png");


        ImageIcon table=new ImageIcon("images/Table_Overhead.png");
        activeDeckTable= new JLabel(table);
        activeDeckTable.setBounds(0,0,130,130);
        activeDeckTable.revalidate();
        activeDeckTable.repaint();

        activeDeckUI= new JLabel(backCard);
        activeDeckUI.setBounds(45,30,41,60);
        frame.add(activeDeckUI);
        frame.setVisible(true);
        activeDeckUI.revalidate();;
        activeDeckUI.repaint();


        JLayeredPane layeredPane=new JLayeredPane();
        layeredPane.setBounds(290,130,130,130);
        layeredPane.add(activeDeckUI, Integer.valueOf(1));
        layeredPane.add(activeDeckTable, Integer.valueOf(0));
        frame.add(layeredPane);
        frame.setVisible(true);
        layeredPane.revalidate();;
        layeredPane.repaint();

        playerDeckUI= new JPanel(null);
        playerDeckUI.setBounds(20,280,680,100);
        frame.add(playerDeckUI);

        log.append("Welcome to BS!\n");
        System.out.println("Welcome to BS!");
        System.out.println("");
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
        playerCardsUI();
        whoShouldStart();
        if (playerTurn==true) {
            playerMove();
        }
        if (oppsTurn==true) {
            oppMove();
        }



        


        if (playerDeck.size()==0) {
            System.out.println("Opp wins, player loses");
            log.append("Opp wins, player loses");
            log.append("");
        }

        if (oppsDeck.size()==0) {
            System.out.println("Player wins, opp loses");
            log.append("Player wins, opp loses");
            log.append("");
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

        public static void playerCardsUI(){
            playerDeckUI.removeAll();

            widthOfCards=(playerDeck.size()-1)*20+60; //take all cards except first, multiplies by spacing and width of individual card
            spacingOfCards=(640-widthOfCards)/2; //640 is width of playerHandUI

            for (int i=0; i<playerDeck.size(); i++)
            {

                ImageIcon card=new ImageIcon("cards/" + playerDeck.get(i) + ".png");

                JButton cardButton=new JButton(card);
                cardButton.setBounds(spacingOfCards+i*20, 0,60,90);

                int indexOfCardPressed = i;
                cardButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (indexOfCardPressed<playerDeck.size()) {
                            String playedCard = playerDeck.remove(indexOfCardPressed);
                            activeDeck.add(playedCard);
                            log.append("You put down " + playedCard + "\n");

                            playerTurn = false;
                            oppsTurn = true;
                            playerCardsUI();
                            oppMove();
                        }
                    }
                });

                playerDeckUI.add(cardButton);
            }
            playerDeckUI.revalidate();;
            playerDeckUI.repaint();
        }

        public static void whoShouldStart()
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
                log.append("Player has Ace of Spades and will start.\n");
            //    System.out.println("Player has Ace of Spades and will start.\n");
                playerTurn=true;
                oppsTurn=false;
                OppCallBS=false;
                PlayerCallBS=false;
            }

            if(oppHasAce==true)
            {
                log.append("Opp has Ace of Spades and will start. \n");
              System.out.println("Opp has Ace of Spades and will start.");
                playerTurn=false;
                oppsTurn=true;
                PlayerCallBS=false;
                OppCallBS=false;
                oppHasAce=false;
            }

        }

        public void playerMove()
        {
            OppCallBS=false;
            PlayerCallBS=true;

            System.out.println("Your turn. Your cards: " + playerDeck);
            System.out.println("Do you want to place down a card or call BS?");
         //   String choice = scan.nextLine().trim();
            String choice = "";
            if (choice.equals("if UI breaks change this :<("))
            {   for (int i=1; i<playerDeck.size(); i++)
                {
                System.out.println(i + ": " + playerDeck.get(i));
                }
                System.out.println("Type in the number of the card you want to place");
                String choice1 = scan.nextLine().trim();
                String playedCard = playerDeck.remove(Integer.parseInt(choice1));
                activeDeck.add(playedCard);
                System.out.println("You put down: " + playedCard);
                expectedCard = (expectedCard + 1)%cardNum.length;
                playerTurn = false;
                PlayerCallBS=false;
                oppsTurn = true;
            }

            if ((choice.equals("BS") && PlayerCallBS==true) || checkBS==true && expectedCard>=2)
            {
                lastCard = activeDeck.get(activeDeck.size() - 1);
                if (expectedCard == 0) {
                    lastExpectedCard = cardNum.length - 1;
                } else {
                    lastExpectedCard = expectedCard - 1;
                }

                trueCard = lastCard.substring(0, lastCard.indexOf(" "));

                if (!trueCard.equals(cardNum[lastExpectedCard])) {
                    System.out.println("Opp was BSing their card. Opp picks up the deck");
                    System.out.println("ExpectedCard: " + lastExpectedCard);
                    System.out.println("True card:" + trueCard);

                    log.append("Opp was BSing their card. Opp picks up the deck\n");
                    log.append("ExpectedCard: " + lastExpectedCard + "\n");
                    log.append("True card:" + trueCard + "\n");

                    lastExpectedCard=0;
                    playerTurn = false;
                    oppsTurn = true;
                    for (int i = 0; i < activeDeck.size(); i++) {
                        oppsDeck.add(activeDeck.get(i));

                    }
                    activeDeck.clear();
                    playerCardsUI();
                    System.out.println("They now have " + oppsDeck);
                    log.append("They now have " + oppsDeck + "\n");
                    expectedCard=0;
                } else {
                    System.out.println("Opp put down the right card. Player picks up the deck.");
                    lastExpectedCard=0;
                    playerTurn = false;
                    oppsTurn = true;
                    for (int i = 0; i < activeDeck.size(); i++) {
                        playerDeck.add(activeDeck.get(i));

                    }
                    activeDeck.clear();
                    PlayerCallBS=false;
                    playerCardsUI();
                    System.out.println("You now have " + playerDeck);
                    log.append("You now have " + playerDeck + "\n");
                    expectedCard=0;
            }}}

            public static void oppMove()
            {
                if (activeDeck.size()<=1)
                {
                    OppCallBS=false;
                    whoShouldStart();

                }
                expectedCard= (expectedCard+1)%cardNum.length;
                OppCallBS=true;
                PlayerCallBS=false;
                if (Math.random()<0.3 && OppCallBS==true && oppsTurn==true && expectedCard>=2) {
                    OppCallBS=false;
                    System.out.println("Opp calls BS");
                    log.append("Opp calls BS\n");
                    lastCard = activeDeck.get(activeDeck.size() - 1);


                    if (expectedCard == 0) {
                        lastExpectedCard = cardNum.length - 1;
                    } else {
                        lastExpectedCard = expectedCard - 1;
                    }

                    trueCard = lastCard.substring(0, lastCard.indexOf(" "));

                    if (!trueCard.equals(cardNum[lastExpectedCard])) {
                        System.out.println("Player was BSing their card. Player picks up the deck");
                        System.out.println("ExpectedCard: " + lastExpectedCard);
                        System.out.println("True card:" + trueCard);

                        System.out.println("Player was BSing their card. Player picks up the deck\n");
                        System.out.println("ExpectedCard: " + lastExpectedCard + "\n");

                        System.out.println("True card:" + trueCard + "\n");

                        lastExpectedCard = 0;
                        playerTurn = true;
                        oppsTurn = false;
                        for (int i = 0; i < activeDeck.size(); i++) {
                            playerDeck.add(activeDeck.get(i));
                        }
                        activeDeck.clear();
                        playerCardsUI();
                        System.out.println("You now have " + playerDeck);
                        log.append("You now have " + playerDeck + "\n");
                        expectedCard=0;
                    } else {
                        System.out.println("Player put the right card. Opp picks up the deck.");
                        log.append("Player put the right card. Opp picks up the deck.\n");
                        lastExpectedCard = 0;
                        for (int i = 0; i < activeDeck.size(); i++) {
                            oppsDeck.add(activeDeck.get(i));

                        }
                        activeDeck.clear();
                        playerTurn = true;
                        oppsTurn = false;
                        playerCardsUI();
                        log.append("They now have " + oppsDeck+"\n");
                        System.out.println("They now have " + oppsDeck+"\n");
                        expectedCard=0;
                    }

                }

                        log.append("Opp is placing a card. The expected card is: " + cardNum[expectedCard] + "\n");
                        System.out.println("Opp is placing a card. The expected card is: " + cardNum[expectedCard]);
                        String playedCard = oppsDeck.remove(0);
                        activeDeck.add(playedCard);
                        playerTurn = true;
                        oppsTurn = false;
                        OppCallBS=true;


                }

            }







