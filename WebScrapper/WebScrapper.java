package WebScraper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.util.ArrayList;
import java.util.HashMap;

public class WebScrapper {
    public static HashMap<String, stockObject> someHash = new HashMap<String, stockObject>();
    public static void getInfo() {
        try {
            String baseURL = "https://www.centralcharts.com";
            ArrayList<Thread> bunchOThreads =  new ArrayList<>();
            for (int i = 1; i < 92; i++) { // 92  
                String url = "https://www.centralcharts.com/en/price-list-ranking/ALL/asc/ts_29-us-nyse-stocks--qc_1-alphabetical-order?p=" + i;
                Document document = Jsoup.connect(url).get();
                for (Element row : document.select("table.tabMini.tabQuotes tr")) {
                    String routingURL = row.select("td:nth-of-type(1) > a").attr("href");
                    routingURL = baseURL + routingURL;
                    stockObject obj = new stockObject(row, routingURL);
                    Thread myThread = new Thread(obj);
                    bunchOThreads.add(myThread);
                    Thread.sleep(40);
                    myThread.start();
                }
            }
            for (int i = 1; i < 6; i++) { // 92  
                String url = "https://www.centralcharts.com/en/price-list-ranking/ALL/asc/ts_20-us-tech-150--qc_1-alphabetical-order?p=" + i;
                Document document = Jsoup.connect(url).get();
                for (Element row : document.select("table.tabMini.tabQuotes tr")) {
                    String routingURL = row.select("td:nth-of-type(1) > a").attr("href");
                    routingURL = baseURL + routingURL;
                    stockObject obj = new stockObject(row, routingURL);
                    Thread myThread = new Thread(obj);
                    bunchOThreads.add(myThread);
                    Thread.sleep(40);
                    myThread.start();
                }
            }
            System.out.println("All stocks gotten");
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Bad URL, 404 not found (Most likely)");
        }
    }
}