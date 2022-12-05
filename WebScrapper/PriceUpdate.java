package WebScrapper;

public class PriceUpdate implements Runnable {
    private stockObject thisStock;
    public void run() {
        thisStock.someMethod();
        //thisStock.setPrice("poop worked");
        //thisStock.printStock();
        return;
    }
    public PriceUpdate(stockObject o) {
        thisStock = o;
        return;
    }
}
