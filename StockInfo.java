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

    public stockObject getMyStock(){
        return this.myStock;
    }


    
    public StockInfo(stockObject o){
        myStock = o;
        Border border = BorderFactory.createLineBorder(Color.WHITE);
        this.setBorder(border);
        this.setSize(new Dimension(500/2, 500/6));
        this.setLayout(new GridLayout(4, 0));
        
        JLabel stockName = new JLabel();
        stockName.setForeground(Color.WHITE);
        stockName.setText(o.getName());
        this.add(stockName);

        JLabel stockPrice = new JLabel();
        stockPrice.setForeground(Color.WHITE);
        stockPrice.setText(o.getPrice());
        this.add(stockPrice);

        JLabel stockTicker = new JLabel();
        stockTicker.setForeground(Color.WHITE);
        stockTicker.setText(o.getTicker());
        this.add(stockTicker);

        JLabel stockAmount = new JLabel();
        stockAmount.setForeground(Color.WHITE);
        stockAmount.setText(Integer.toString(o.getQauntity()));
        this.add(stockAmount);



        return;
    }
    
}
