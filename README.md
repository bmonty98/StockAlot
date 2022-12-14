# CS-49J-Final-Project

Github: https://github.com/bmonty98/CS-49J-Final-Project

### Prerequisites
 - JDK Version 17.0.4 at a minimum.
 - IDE of choice.

# Classes and Methods:
## Frame.java:
  - Frame(int h, int w): Constructor, used to make the entire GUI run
  - getStock: Gets the stock currently active on the GUI
  - setStock: Sets the current stock on the GUI
  - getAmount: Getter for the amount displayed on the GUI
  - setAmount: Sets the amount on the GUI
## StockInfo.java
  - getMyStock: returns stock data
  - StockInfo(stockObject o): Constructor to create a new StockInfo panel
  - Few getters and setters included
## StockUpdating.java
  - StockUpdating() Constructor to make object
  - run() for multithreading purposes, dynamically updates GUI to show real time data
## GenerateCapital.java
  - SomeMethod: Implements and interface to generate random value
  - rollroulette: Calls the somemethod and converts the randomly generated interger to an floating point number
## Money.java
  - Abstract Class meant to be inherited for use as a container for financial based data
## StartingMoney.java
  - Inherits Money class; used to store data pertaining to money user has at the start of the game
## BoolingShingle.java
  - Interface with general purpose function for the sake of interface implementation
  - someMethod: general purpose function made specifically to be implemented externally
## getHashed.java
  - getSFH(String ticker): Gets a stockObject which was stored in a HashMap
## PriceUpdate.java
  - run(): Used for multithreading, allows multiple stockObjects to be updated concurrently
## stockObject.java
  - run(): Multithreading used to gather data on a stock
  - getTicker(String url): NOT A GETTER, gets ticker from website (Webscraping)
  - someMethod(): used to realtime update prices of a stock from Yahoo Finance (Webscraping)
  - compareTo(stockObject o): compares two stock objects, one with greatest price is favored
  - printStock(): for debugging purposes
  - stockObject(Element someRow, String url): Constructor to initialize the object
  - Getters and Setters included for all pertinent variables
## User.java
## WebScrapper.java
  - getInfo(): Main Web Scraping function, accesses an index of stocks and uses mutlithreading to get info on all stocks (Used mainly for preprocessing)
  - writeJSON(): Writes a JSON file from the HashMap, again used mainly for preprocessing
  - readJSON(): Reads the preprocessed JSON file, stores all values in a HashMap
  - updateAllStocks(): updates every single stock in the HashMap with multithreading (Not in final product, needs optimization as it takes too long to run on close)

## User.java:
  - someMethod: Impliments an interface to read off a UserSummary.
  - buyStock(int Quantity): manages the interaction of a User to buy a valid stock from the market and have it stashed in a porfolio thats represented by a hashmap.  throws errors if the function cant find the stock or the user has insufficent funds.
  - sellStock(int Quantity): manages the interaction of a player selling a stock from their portfolio back to the market for its current worth. Throws errors if the stock deired to sell is not found in the portfolio or if you try to sell more of the stock than you can that would give you a negative amount of stock in there.
  - Bankruptcy(): boolean method, the returns true if player has no capital and no stocks in portfolio otherwise returns false.
 

# How to Run:

1. JDK Version 17.0.4 at a minimum.

2. In the lib folder are the included dependent libraries, if code does not work right away make sure these are listed as referenced libraries in order to run the code. Pertinent for Web Scraping.

 - json-simple-1.1.1.jar

 - jsoup-1.15.3.jar

 Otherwise ensure the following packages are installed:

 - java.util.*

 - java.io.*

 - javax.swing.*

 - java.awt.*



3. Main() found in App.java in order to use program

4. Run


### If you would rather create your own main() function, import these:


import Draw.Frame;

import WebScrapper.WebScrapper;

Then include these lines of code:

WebScrapper.readJSON();

Frame myFrame = new Frame(500, 1000);

## For Real Time Data:

### All data is technically real time, however, as the stock market closes in order to get data that changes make sure to run the program Mon-Fri 6:30-1:00 PM

## Using the GUI
In order to use the program you must first get money

This is done on the right hand side of the program, Roll/Reroll to get money and accept what random amount of money you get if you want (10 Rolls maximum)

Then on the left hand side of the program is a search bar, search stock tickers (Ex: AMZN, AAPL, AMD)

Only correct stock tickers work right now no incorrect tickers / names work

Press enter to get that stock and then buy and sell that amount of stock with your money you generated

That's it, enjoy!
