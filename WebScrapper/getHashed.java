package WebScraper;

public class getHashed {
    public static void getSFH(String ticker) {
        stockObject someObj = WebScrapper.someHash.get(ticker);
        System.out.println(someObj.getTicker());
        System.out.println(someObj.getName());
        System.out.println(someObj.getPrice());
    }
}
