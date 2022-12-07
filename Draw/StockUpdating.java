package Draw;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Map.Entry;
import WebScrapper.stockObject;
import WebScrapper.PriceUpdate;
import java.util.HashMap;

public class StockUpdating implements Runnable {
    private ArrayList<StockInfo> panelsOfStocks;
    private Frame myFrame;
    private JPanel stockPanel;
    private JLabel jUserNW;
    private JLabel nSP;
    private HashMap<stockObject,Thread> threads = new HashMap<stockObject,Thread>() {};

    public StockUpdating(ArrayList<StockInfo> someArr, Frame someFrame, JPanel somePanel, JLabel userNW, JLabel stockPrice) {
        this.panelsOfStocks = someArr;
        this.myFrame = someFrame;
        this.stockPanel = somePanel;
        this.jUserNW = userNW;
        this.nSP = stockPrice;
    }

    public void run() {
        try {
            while (myFrame.getGoing() == true) {
                HashMap<String, stockObject> someHash = new HashMap<String, stockObject>(this.myFrame.getUser().getPortfolio());
                for (Entry<String, stockObject> entry : someHash.entrySet()) {
                    PriceUpdate updateStock = new PriceUpdate(entry.getValue());
                    if (!threads.containsKey(entry.getValue())) {
                        Thread myThread = new Thread(updateStock);
                        threads.put(entry.getValue(), myThread);
                        myThread.start();
                    }
                    else if (!threads.get(entry.getValue()).isAlive()) {
                        Thread myThread = new Thread(updateStock);
                        threads.replace(entry.getValue(), myThread);
                        myThread.start();
                    }
                    else if(threads.get(entry.getValue()).isAlive()) {
                        continue;
                    }
                    for (int i = 0; i < panelsOfStocks.size(); i++) {
                        jUserNW.setText("Net Worth: $" + Double.toString(this.myFrame.getUser().getNetWorth()));
                        if (entry.getValue().getName() == Frame.getStock().getName()) {
                            nSP.setText("$" + entry.getValue().getPrice());
                        }
                        if(panelsOfStocks.get(i).getStockObject().getName() == entry.getValue().getName()) {
                            panelsOfStocks.get(i).getSPrice().setText("   $" + entry.getValue().getPrice());
                        }
                        panelsOfStocks.get(i).revalidate();
                        panelsOfStocks.get(i).repaint();
                        stockPanel.revalidate();
                        stockPanel.repaint();
                        myFrame.repaint();
                        myFrame.revalidate();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.run();
        }

    }
}
