package Financial;
import java.util.Random;
import WebScrapper.boolingShingle;

public class GenerateCapital implements boolingShingle { // Prompts the user to "roll" up to 10 times for their starting capital value

  private int x;

  public void someMethod(){
    Random seedgen = new Random(); // Random Number Generation
    x = seedgen.nextInt(20000000); // Generate a random integer (Max 20,000,000) and push to stack
  }
  
  public Double rollroulette() { // Function called to roll for value
    
    someMethod(); // Generate and store rolled value
    Double currentroll = Double.valueOf(x/ 100); // Convert Rolled integer into a double with two decimal place We want to cap our capital at $200,000.00
    return currentroll; // Return final value
  }
}