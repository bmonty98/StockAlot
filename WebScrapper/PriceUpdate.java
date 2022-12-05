package WebScrapper;

public class PriceUpdate implements Runnable {
    private stockObject thisStock;
    public void run() {
        thisStock.updatePrice();
        //thisStock.setPrice("poop worked");
        //thisStock.printStock();
        return;
    }
    public PriceUpdate(stockObject o) {
        thisStock = o;
        return;
    }
}
