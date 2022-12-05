
//package WebScrapper;
import java.util.Random;
import java.util.Stack;
import java.util.Scanner;
import javax.swing.*;

public class GenerateCapital implements boolingShingle { // Prompts the user to "roll" up to 10 times for their starting capital value

  public void someMethod(){
    Random seedgen = new Random(); // Random Number Generation
    rolls.push(seedgen.nextInt(20000000)); // Generate a random integer (Max 20,000,000) and push to stack
  }

  // Store each "roll" on a stack so that the user can only choose the most recent
  // roll
  public static Stack<Integer> rolls = new Stack<>();

  public double rollroulette(int count) { // Function called to roll for value

    // Class objects for utility
    
    Scanner choice = new Scanner(System.in); // User Input
    

    // Generate and store rolled value
    someMethod();
    double currentroll = (Double.valueOf(rolls.peek()) / 100); // Convert Rolled integer into a double with two decimal
                                                               // place
                                                               // We want to cap our capital at $200,000.00

    if (count < 10) { // No more than 10 rerolls
      
      // Prompt user for reroll
      char yn = ' '; // Store user input for reroll
      System.out.println("Roll " + count + ": " + currentroll);
      System.out.println("Reroll? (y/n):");
      String instring = choice.nextLine();
      yn = instring.charAt(0);

      if ((yn != 'Y') && (yn != 'y') && (yn != 'N') && (yn != 'n')) {
        System.out.println("Invalid Input"); // Prompt user to provide a valid input
        return rollroulette(count); // Recurse without incrementing counter
      }

      // Switch Statement to either recurse for a reroll or terminate the rolling process
      switch (yn) {
        case 'Y': // If yes, recurse
          count++; // Increment roll counter
          return rollroulette(count); // Recurse
        case 'y':
          count++;
          return rollroulette(count);
        case 'N':
          break; // If user doesn't reroll, do nothing and move on
        case 'n':
          break;
      }
    }

    // When user doesn't choose to reroll, return the final value.
    double acceptedroll = (Double.valueOf(rolls.pop()) / 100); // Convert Integer from stack to double value with 2
                                                               // decimal points
    System.out.println("Accepted Starting Capital: " + acceptedroll); // Tell user their accepted capital value
    return acceptedroll; // Return final value
  }

  /*public static void main(String[] args) {

    double Capital = rollroulette(1);

    System.out.print(Capital);

  }
*/
}