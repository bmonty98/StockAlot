# CS-49J-Final-Project

Github: https://github.com/bmonty98/CS-49J-Final-Project

Classes and Methods:
- Frame.java
- GenerateCapital.java
    - SomeMethod: Implements and interface to generate random value
    - rollroulette: Calls the somemethod and converts the randomly generated interger to an floating point number
- Money.java
    - Abstract Class meant to be inherited for use as a container for financial based data
- StartingMoney.java
    - Inherits Money class; used to store data pertaining to money user has at the start of the game
- BoolingShingle.java
    - Interface with general purpose function for the sake of interface implementation
    - someMethod: general purpose function made specifically to be implemented externally
- getHashed.java
- PriceUpdate.java
- stockObject.java
- User.java
- WebScrapper.java

User.java:
- someMethod: Impliments an interface to read off a UserSummary.
- buyStock(int Quantity): manages the interaction of a User to buy a valid stock from the market and have it stashed in a porfolio thats represented by a hashmap. throws errors if the function cant find the stock or the user has insufficent funds.
- sellStock(int Quantity): manages the interaction of a player selling a stock from their portfolio back to the market for its current worth. Throws errors if the stock deired to sell is not found in the portfolio or if you try to sell more of the stock than you can that would give you a negative amount of stock in there.
- Bankruptcy(): boolean method, the returns true if player has no capital and no stocks in portfolio otherwise returns false.
- 

How to Run:

