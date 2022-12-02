package WebScrapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class WebScrapper {
    public static HashMap<String, stockObject> someHash = new HashMap<String, stockObject>();

    public static void getInfo() {
        try {
            String baseURL = "https://www.centralcharts.com";
            ArrayList<Thread> bunchOThreads = new ArrayList<>();
            for (int i = 1; i < 92; i++) { // 92
                String url = "https://www.centralcharts.com/en/price-list-ranking/ALL/asc/ts_29-us-nyse-stocks--qc_1-alphabetical-order?p="
                        + i;
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
            for (int i = 1; i < 6; i++) { // 6 (I think)
                String url = "https://www.centralcharts.com/en/price-list-ranking/ALL/asc/ts_20-us-tech-150--qc_1-alphabetical-order?p="
                        + i;
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
            Thread.sleep(10000);
            System.out.println("All stocks gotten");
            writeJSON();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Bad URL, 404 not found (Most likely)");
        }
    }

    public static void writeJSON() {
        JSONArray someArr = new JSONArray();
        try (FileWriter file = new FileWriter("stocks.json")) {
            for (stockObject o : someHash.values()) {
                JSONObject jObj = new JSONObject();
                jObj.put("name", o.getName());
                jObj.put("ticker", o.getTicker());
                jObj.put("price", o.getPrice());
                jObj.put("Yurl", o.getYURL());
                someArr.add(jObj);
            }
            file.write(someArr.toString());
            file.flush();
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }

    public static void readJSON() {
        JSONParser parser = new JSONParser();
        someHash.clear();
        try {
            Object obj = parser.parse(new FileReader("stocks.json"));
            JSONArray someJarr = (JSONArray) obj;
            JSONObject jOBJ = new JSONObject();
            Iterator<JSONObject> iterator = someJarr.iterator();
            while (iterator.hasNext()) {
                stockObject nSObj = new stockObject();
                jOBJ = iterator.next();
                nSObj.setName((String) jOBJ.get("name"));
                nSObj.setTicker((String) jOBJ.get("ticker"));
                nSObj.setPrice((String) jOBJ.get("price"));
                nSObj.setYURL((String) jOBJ.get("Yurl"));
                someHash.put(nSObj.getTicker(), nSObj);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error stocks.json not found");
        } catch (IOException e) {
            System.out.println("Retry JSON reading");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }

    public static void updateAllStocks() {
        for (Entry<String, stockObject> entry : WebScrapper.someHash.entrySet()) {
            entry.getValue().updatePrice();
        }
        return;
    }
}