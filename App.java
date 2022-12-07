import WebScrapper.WebScrapper;
import WebScrapper.getHashed;
import WebScrapper.stockObject;
import java.util.HashMap;
import java.util.Map.Entry;

public class App {
    public static void main(String[] args) throws Exception {
        WebScrapper.readJSON();
        for (Entry<String, stockObject> entry : WebScrapper.someHash.entrySet()) {
            entry.getValue().updatePrice();
        }

        return;
    }
}