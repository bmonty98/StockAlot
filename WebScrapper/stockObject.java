package WebScraper;
//import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
//import java.io.FileWriter;
import java.io.IOException;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
    /*
     * Get URL from each Json object in the object
     *  - Access URL get ticker
     *  - Set to separate thread to do faster?
     * 
     * 
     * 
     * 
     * THREADS
     *  - Create new object with parameters of element row -> grab: name, price, routing url
     */

public class stockObject implements Runnable, Comparable<stockObject> {
    private static String useSubStr = "                                             ";
    private String url;
    private String name;
    private String price;
    private String ticker;
    private Element row;
    private long value;
    
    public void run() {
        if (row.select("td:nth-of-type(1)").text() == null) {
            return;
        }
        else{
            try {
                String finalString = "";
                // Get ticket later, for now just get the name, price and so on
                String name = row.select("td:nth-of-type(1)").text(); // This works first try, brilliant!
                final String price = row.select("td:nth-of-type(2)").text();
                // Think about storing these names locally, in a json file with a map to the tickers
                this.name = name;
                this.ticker = getTicker(url);
                //if (!hasTicker) this.ticker = "";
                this.price = price;
                if (ticker == null) {
                    return;
                }
                else{ //  Use substring instead of adding on spaces, do not change value of variables
                    if(!ticker.isEmpty() && ticker.length() < 7) {
                        finalString += ticker + useSubStr.substring(0, 7 - ticker.length());
                        if (!name.isEmpty() && name.length() < 50) {
                            finalString += name + useSubStr.substring(0, 40 - name.length()) + price;
                            WebScrapper.someHash.put(ticker, this);
                            //System.out.println(finalString);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
    }
    private String getTicker(String url) {
       try {
        Document tickerPage = Jsoup.connect(url).get();
        return tickerPage.select("div.hp-center-right.col-md-6.col-sm-6 > table.tabMini.tabQuotes > tbody > tr:eq(1) > td:eq(1) > span").text();
       } catch (IOException e) {
            System.out.println("404 or 503 Error, Skipping Stock: " + this.name);
            return null;
       }
    }
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
