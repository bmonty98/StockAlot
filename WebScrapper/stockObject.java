package WebScraper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.IOException;

public class stockObject implements Runnable, Comparable<stockObject> {
    private static String useSubStr = "                                             ";
    private static String yahooURL = "https://finance.yahoo.com/quote/";
    private String stockYURL;
    private String url;
    private String name;
    private String price;
    private String ticker;
    private Element row;
    private long value; // Here for no reason currently. Comparable is not complete
    
    public void run() {
        if (row.select("td:nth-of-type(1)").text() == null) {
            return;
        }
        else{
            try {
                String finalString = "";
                final String name = row.select("td:nth-of-type(1)").text();
                String priceStr = row.select("td:nth-of-type(2)").text();
                priceStr = priceStr.replaceAll(",", "");
                this.name = name;
                this.ticker = getTicker(url);
                this.price = priceStr;
                if (ticker == null) {
                    return;
                }
                else{
                    if(!ticker.isEmpty() && ticker.length() < 7) {
                        finalString += ticker + useSubStr.substring(0, 7 - ticker.length());
                        if (!name.isEmpty() && name.length() < 50) {
                            finalString += name + useSubStr.substring(0, 40 - name.length()) + price;
                                WebScrapper.someHash.put(ticker, this);
                                //System.out.println(finalString);
                        }
                    }
                }
                stockYURL = yahooURL + ticker + "/";
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
    }

    private String getTicker(String url) {
       try {
        // Uses Yahoo finance, simple URL just yahoo + stock ticker. More reliable as well as more real time data
        // Not every stock will have real time date, some are delayed. Yahoo offers either other website doesn't
        Document tickerPage = Jsoup.connect(url).get();
        return tickerPage.select("div.hp-center-right.col-md-6.col-sm-6 > table.tabMini.tabQuotes > tbody > tr:eq(1) > td:eq(1) > span").text();
       } catch (IOException e) {
            System.out.println("404 or 503 Error, Skipping Stock: " + this.name);
            return null;
       }
    }

    // Put method in a while loop/
    //  - Estimated ~20 second buffer is good
    public void updatePrice() {
        try {
            Document stockPage = Jsoup.connect(this.getYURL()).get();
            String priceStr = stockPage.select("body.reportReactMarkupDiff > div:eq(0) > div:eq(0) > div:eq(0) > div:eq(0) > div:eq(0) > div:eq(1) > div:eq(0) > div:eq(0) > div:eq(5) > div:eq(0) > div:eq(0) > div:eq(0) > div:eq(2) > div:eq(0) > div:eq(0) > fin-streamer:eq(0)").text(); // Can get more data, such as gain since open and % if final :eq() is removed
            this.setPrice(priceStr);
        } catch (IOException e) {
            System.out.println("404 or 503 Error, Skipping Stock: " + this.name);
            return;
        }
    }


    /*----COMPARABLE (Not working as indended currently)---- */

    @Override
    public int compareTo(stockObject o) {
        if (this.value > o.getValue()) {
            return 1;
        }
        else if (this.value == o.getValue()) {
            return 0;
        }
        else {
            return -1;
        }
    }

    /*----CONSTRUCTORS, GETTERS, AND SETTERS---- */

    public stockObject(Element someRow, String url) {
        this.row = someRow;
        this.url = url;
    }
    public String getName() {
        return this.name;
    }
    public long getValue() {
        return this.value;
    }
    public String getURL() {
        return this.url;
    }
    public String getPrice() {
        return this.price;
    }
    public String getTicker() {
        return this.ticker;
    }
    public String getYURL() {
        return this.stockYURL;
    }
    public void setName(String sName) {
        this.name = sName;
        return;
    }
    public void setURL(String sURL) {
        this.url = sURL;
        return;
    }
    public void setValue(int sValue) {
        this.value = sValue;
        return;
    }
    public void setPrice(String sPrice) {
        this.price = sPrice;
        return;
    }
    public void setTicker(String sTicker) {
        this.ticker = sTicker;
        return;
    }
}
