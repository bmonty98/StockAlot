package WebScrapper;

public class PriceUpdate implements Runnable {
    private stockObject thisStock;
    public void run() {
        try {
            Thread.sleep(15000);
            thisStock.someMethod();
            System.out.println("updating " + thisStock.getName());
        }
        catch (Exception e) {e.printStackTrace();}
        //thisStock.setPrice(Double.toString(Double.parseDouble(thisStock.getPrice()) + 1));
        return;
    }
    public PriceUpdate(stockObject o) {
        thisStock = o;
        return;
    }
}
