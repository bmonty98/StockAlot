package Draw;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import WebScrapper.WebScrapper;
import WebScrapper.getHashed;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import WebScrapper.stockObject;
import UserInteractions.User;


public class StockInfo extends JPanel{
    private stockObject myStock;
    private String name;
    private String price;
    private String ticker;
    private String quantity;
    private JLabel tickQuant;
    public stockObject getMyStock(){
        return this.myStock;
    }
    public StockInfo(stockObject o){
        name = o.getName();
        price = o.getPrice();
        ticker = o.getTicker();
        quantity = Integer.toString(o.getQauntity());
        myStock = o;
        Border border = BorderFactory.createLineBorder(Color.WHITE);
        this.setBorder(border);
        this.setSize(new Dimension(500/2, 500/4));
        this.setLayout(new GridLayout(4, 0));
        this.setBackground(new Color (70,70,80));
        
        JLabel stockName = new JLabel("", SwingConstants.LEFT);
        stockName.setForeground(Color.WHITE);
        stockName.setText("   " + name);
        this.add(stockName);

        JLabel stockPrice = new JLabel("", SwingConstants.LEFT);
        stockPrice.setForeground(Color.WHITE);
        stockPrice.setText("   $" + price);
        this.add(stockPrice);

        JLabel stockTicker = new JLabel("", SwingConstants.LEFT);
        tickQuant = stockTicker;
        tickQuant.setForeground(Color.WHITE);
        tickQuant.setText( "   " + ticker + "       Amount Owned: " + quantity);
        this.add(stockTicker);

        return;
    }
    public stockObject getStockObject() {
        return this.myStock;
    }
    public void setQuantity(int q) {
        this.quantity = Integer.toString(q);
        return;
    }
    public String getQuantity() {
        return this.quantity;
    }
    public JLabel getTickQuant() {
        return this.tickQuant;
    }
}