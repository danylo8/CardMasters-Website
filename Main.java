import java.util.Scanner;
import javax.swing.JFrame;

public class Main
{

    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args)
    {

        System.out.println("What card game would you like to play?");
        System.out.println("There is Spit, Spoons, and BS");
        System.out.println("Type in the exact title of the game you would like to play");
        String choice = scan.nextLine().trim();


        if(choice.equals("Spit"))
        {
            Spit spitGame = new Spit();

        }else if(choice.equals("Spoons"))
        {
            //System.out.println("Sorry, but someone took the last of the disposible spoons to some party last night");
            Spoons spoonGame = new Spoons();
        }else if(choice.equals("BS"))
        {
            //System.out.println("Hey! No curse words!");

            BS bsGame = new BS();
        }

    }


    }

