package UserInteractions;

import WebScrapper.*;
import java.util.HashMap;
// import Draw.*;
import java.util.*;
import java.util.Map.*;

import Draw.Frame;

// import org.omg.CORBA.portable.ValueBase;

 public class User {
    // please for the love of god change this later to zero!
    private double capital;
    private String name;
    private HashMap<String, stockObject> portfolio;
    private double netWorth;
    
    public User(){
        this.capital = 0;
        this.name = "Player 1";
        this.portfolio = new HashMap<String, stockObject>();
        this.netWorth = this.capital;
    }

    public User(double capital, String name, HashMap<String, stockObject> portfolio){
        this.capital = capital;
        this.name = name;
        this.portfolio = portfolio;
        this.netWorth = this.capital;
    }

    /**
     * @param ticker a String that represents the ticker of the stock.
     * @return returns stockobject of the specified stock.
     */
    public stockObject getStock(String ticker){
        
        stockObject specifiedStock = this.portfolio.get(ticker);
        if(specifiedStock == null){
            throw new IllegalArgumentException("this ticker is either mispelled or not in your portfolio, try again.");
        }
        else{
        return specifiedStock;
        }   
    }

    /**
     * for some reason wont let me make it a abstract public static method, so immitated the abstract ability.
     * @param ticker a String that represents the ticker of the stock.
     * @return NOTHING. just adds a specified stockObject to the object's portfolio variable.
     * then subtracts specified stocks price from User's capital variable.
     */
     public void buyStock(int amount){
        // gets the desired stock for purchase:
        try{
            stockObject targetedStock = Frame.getStock();
            if(targetedStock == null){
                throw new IllegalArgumentException("returned a null object, ticker could not be found in Frame.getStock()");
            }

        // checks to see if User has the funds to purchase that stock and if so, does the transaction:
            double targetedStockPrice = Double.parseDouble(targetedStock.getPrice());

            if(this.capital - targetedStockPrice >= 0){
                this.portfolio.put(targetedStock.getTicker(), targetedStock);
                this.capital -= targetedStockPrice * amount;
                Frame.getStock().setQuantity(targetedStock.getQauntity() + amount);
                System.out.println("you have just bought a stock");
                System.out.println(Frame.getStock().getQauntity());
                System.out.println();
            }
        // happens if you do not have the funds to purchase the stock.
            else{
                throw new ArithmeticException("Invalid funds in your account to aquire this stock!");
            }

        }
        // catching of the error
        catch(ArithmeticException invalidFunds){
            System.out.println(" You do not have the funds to be able to buy this stock, please aquire the dough to buy the stock before you waste my time.");
            return;
        }

        catch(IllegalArgumentException Frame_getStock_failure){
            System.out.println("returned a null object, ticker could not be found in Frame.getStock()");
            return;
        }

        return;
     }

    /**
     * for some reason wont let me make it a abstract public static method, so immitated the abstract ability.
     * @param ticker a String that represents the ticker of the stock.
     * @return NOTHING! Sells the specified stock, takes that stock out of players portfolio.
     *  adds its current price value of sold stock to Users capital variable.
     */
    public void sellStock(int amount){
        try{
        // finds the stock in the portfolio to make sure you got it.
            if(!this.portfolio.containsKey(Frame.getStock().getTicker())){
                throw new IllegalArgumentException("returned False for containskey(), ticker could not be found in the portfolio");
            }
        // finds the stock in the finance website to be used to obtained the current price.
            stockObject targetedStockForSale = Frame.getStock();
            if(targetedStockForSale == null){
                throw new IllegalArgumentException("returned a null object, ticker could not be found in Frame.getStock()");
            }

            double targetedStockForSalePrice = Double.parseDouble(targetedStockForSale.getPrice());

            if(Frame.getStock().getQauntity() > 0){
                this.capital += targetedStockForSalePrice * amount;
                Frame.getStock().setQuantity(targetedStockForSale.getQauntity() - amount);
                System.out.println("you have just sold a stock");
                System.out.println(Frame.getStock().getQauntity());
                System.out.println();
            }

            if(Frame.getStock().getQauntity() == 0){
                this.portfolio.remove(targetedStockForSale.getTicker(), targetedStockForSale);
            }

            else{
                throw new ArithmeticException("cant have a negative amount of a Stock!!!!");
            }
        }

        catch(IllegalArgumentException portfolioFailure){
            System.out.println("returned False for containsKey(), ticker could not be found in the portfolio");
            return;
        }

        catch(ArithmeticException negativeQuantity){
            System.out.println("Cannot have a negative quantity of a stock!!!!! if you cant count, go back to kindergarden.");
            return;
        }

        return;
    }

    /**
    * if user runs out of money, and has no stocks to sell, then returns true.
    * if not the case, returns false.
     */
    public Boolean bankruptcy(){
        if(this.capital <= 0.0 && this.portfolio.isEmpty()){
            return true;
        }
        return false;
     }

     // ------------------GETTER AND SETTTERS---------------

     public double getCapital(){
        return this.capital;
     }

     public String getName(){
        return this.name;
     }

     public HashMap<String, stockObject> getPortfolio(){
        return this.portfolio;
     }

     public double getNetWorth(){
        return this.netWorth;
     }

     public void setCapital(double capital){
        this.capital = capital;
        return;
     }

     public void setName(String name){
        this.name = name;
        return;
     }

     public void setPortfolio(HashMap<String, stockObject> portfolio){
        this.portfolio = portfolio;
        return;
     }



    /**
     * Prints a series of prints or related UI interaction that gives summary
     * of the User's stance currently in the game such as current variable values.
     */
    public void userSummary(){

    System.out.println(this.name);
    System.out.println(this.capital);
    // System.out.println("the following is the entries from the set:");

    // if(!this.portfolio.isEmpty()) {
    //     Iterator it = this.portfolio.entrySet().iterator();
    //     while(it.hasNext()) {
    //        Map.Entry obj = (Entry)it.next();
    //        System.out.println(obj.getValue());
    //     }
    //  }

        
    }

   public static void main(String[] args) {
    WebScrapper.readJSON();
    
    Frame newFrame = new Frame(500, 500);
    }


 }

