import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main
{

    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args)
    {
        System.out.println("What card game would you like to play?");
        String choice = scan.nextLine().trim();
        if(choice.equals("Cuarenta"))
        {
            Cuarenta game = new Cuarenta();
        }

        if(choice.equals("BS"))
        {
            BS game= new BS();
        }
    }


}
