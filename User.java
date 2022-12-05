//package WebScraper;
// import java.util.ArrayList;
import java.util.HashMap;

import WebScraper.stockObject;

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
     * @param ticker a String that represents the ticker of the stock.
     * @return NOTHING. just adds a specified stockObject to the object's portfolio variable.
     * then subtracts specified stocks price from User's capital variable.
     */
   abstract public void buyStock(String ticker);

    /**
     * @param ticker a String that represents the ticker of the stock.
     * @return NOTHING! Sells the specified stock, takes that stock out of players portfolio.
     *  adds its current price value of sold stock to Users capital variable.
     */
    abstract public void sellStock(String ticker);

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

    /**
     * Prints a series of prints or related UI interaction that gives summary
     * of the User's stance currently in the game such as current variable values.
     */
   abstract public void UserSummary();

}