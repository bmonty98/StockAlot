import WebScrapper.*;
import Draw.Frame;

public class App {
    public static void main(String[] args) throws Exception {
        WebScrapper.readJSON();
        Frame someFrame = new Frame(500, 1000);
    }
}
