//package WebScrapper;
import java.util.Random;
import java.util.Stack;
import java.util.Scanner;
import java.math.*;

public class GenerateCapital{

  public static Stack<Integer> rolls = new Stack<>();
  public static double rollroulette(){
 
  Scanner choice = new Scanner(System.in);

  for (int i = 1; i < 10; i++){
     
     Random seedgen = new Random();
     rolls.push(seedgen.nextInt(20000000));
     
     double currentroll = (Double.valueOf(rolls.peek())/100);

     System.out.println("Roll " + i + ": " + currentroll);
     System.out.println("Reroll? (y/n):");
        
     String instring = choice.nextLine(); // Find a way to take continuous inputs
     char yn = instring.charAt(0);

     if ((yn == 'n' || yn == 'N')){
            
            double acceptedroll = (Double.valueOf(rolls.pop())/100);
 
            System.out.println("Accepted Starting Capital: " + acceptedroll);
            return acceptedroll;
     }
     else if((yn != 'y') &&(yn != 'Y') &&(yn != 'n') &&(yn != 'N')){
           System.out.println("Invalid Input");
           i--;
     }
        
     }

     choice.close();

     Random seedgen = new Random();
     rolls.push(seedgen.nextInt(20000000));

     double acceptedroll = (Double.valueOf(rolls.pop())/100);

     System.out.println("Accepted Starting Capital: " + acceptedroll);
     return acceptedroll;
  }


  public static void main(String[] args) {
      
      PseudoUser TestUser = new PseudoUser();

      TestUser.setName("Johnny");

      TestUser.setCapital(rollroulette());

  }
}