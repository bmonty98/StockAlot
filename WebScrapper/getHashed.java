package WebScrapper;

public class getHashed {
    public static stockObject getSFH(String ticker) {
        if (WebScrapper.someHash.containsKey(ticker)) {
            return WebScrapper.someHash.get(ticker);
        }
        else {
            stockObject emptyStock = new stockObject();
            System.out.println("Stock Ticker Not Found");
            return emptyStock;
        }
    }
}
