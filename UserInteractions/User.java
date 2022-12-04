package UserInteractions;

import WebScrapper.*;
import java.util.HashMap;

import org.omg.CORBA.portable.ValueBase;

abstract public class User {
    private double capital = 0.0;
    private String name = "";
    private HashMap<String, stockObject> portfolio;
    
    public User(){
        this.capital = 0.0;
        this.name = "John Doe";
        this.portfolio = new HashMap<String, stockObject>();
    }

    public User(double capital, String name, HashMap<String, stockObject> portfolio){
        this.capital = capital;
        this.name = name;
        this.portfolio = portfolio;
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
     public void buyStock(String ticker){
        // gets the desired stock for purchase:
        try{
            stockObject targetedStock = getHashed.getSFH(ticker);

        // checks to see if User has the funds to purchase that stock and if so, does the transaction:
            double targetedStockPrice = Double.parseDouble(targetedStock.getPrice());

            if(this.capital - targetedStockPrice >= 0){
                this.portfolio.put(ticker, targetedStock);
                this.capital -= targetedStockPrice;
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

        return;
     }

    /**
     * for some reason wont let me make it a abstract public static method, so immitated the abstract ability.
     * @param ticker a String that represents the ticker of the stock.
     * @return NOTHING! Sells the specified stock, takes that stock out of players portfolio.
     *  adds its current price value of sold stock to Users capital variable.
     */
    public void sellStock(String ticker){
        try{

            stockObject targetedStock = getHashed.getSFH(ticker);
            
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
   abstract public void UserSummary();

}
