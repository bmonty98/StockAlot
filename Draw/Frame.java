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

public class Frame extends JFrame{
    private int Height;
    private int Width;
    private String text;
    private stockObject currentStock;
    public Frame(int h, int w) {
        Height = h;
        Width = w;
        this.setTitle("US Stock Market");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(Width, Height);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        Border border = BorderFactory.createLineBorder(Color.WHITE);
        
        JPanel testPan = new JPanel();
        testPan.setPreferredSize(new Dimension(Width/2, Height));
        testPan.setBackground(new Color (70,70,80));
        testPan.setBorder(border);

        JPanel stockPanel2 = new JPanel();
        stockPanel2.setPreferredSize(new Dimension(Width/2, Height));
        stockPanel2.setBackground(new Color (120,70,80));
        stockPanel2.setBorder(border);
        stockPanel2.setLayout(new BorderLayout());

        JPanel stockDisplay = new JPanel();
        stockDisplay.setPreferredSize(new Dimension(Width/5 + 10, Height/2));
        stockDisplay.setBackground(new Color (70,120,80));
        stockDisplay.setBorder(border);
        stockDisplay.setLayout(new GridLayout(4,0));

        JPanel countDisplay = new JPanel();
        countDisplay.setBackground(new Color (70,70,120));
        countDisplay.setSize(new Dimension(stockDisplay.getWidth(), stockDisplay.getHeight()/4));
        countDisplay.setLayout(new BorderLayout());
        JLabel count = new JLabel("0", SwingConstants.CENTER);
        count.setForeground(Color.WHITE);
        JButton upButton = new JButton();
        upButton.setText("↑");
        upButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                int num = Integer.parseInt(count.getText());
                num++;
                count.setText(Integer.toString(num));
            }
        });
        JButton downButton = new JButton();
        downButton.setText("↓");
        downButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                int num = Integer.parseInt(count.getText());
                if (num > 0) {num--;}
                count.setText(Integer.toString(num));
            }
        });
        countDisplay.add(downButton, BorderLayout.WEST);
        countDisplay.add(count, BorderLayout.CENTER);
        countDisplay.add(upButton, BorderLayout.EAST);
        
        JLabel stockName = new JLabel("", SwingConstants.CENTER);
        stockName.setForeground(Color.WHITE);
        stockName.setText("No Stock");

        JLabel stockTicker = new JLabel("", SwingConstants.CENTER);
        stockTicker.setForeground(Color.WHITE);
        stockTicker.setText("No Ticker");

        JLabel stockPrice = new JLabel("", SwingConstants.CENTER);
        stockPrice.setForeground(Color.WHITE);
        stockPrice.setText("No Price");

        stockDisplay.add(stockName);
        stockDisplay.add(stockTicker);
        stockDisplay.add(stockPrice);
        stockDisplay.add(countDisplay);

        JPanel buttonDisplay = new JPanel();
        buttonDisplay.setPreferredSize(new Dimension(Width/5 + 10, Height/2));
        buttonDisplay.setBackground(new Color (70,70,120));
        buttonDisplay.setBorder(border);
        buttonDisplay.setLayout(new BorderLayout());
        
        // Buy Button
        JButton buyButton = new JButton();
        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println("Purchase Stock");
            }
        });
        buyButton.setPreferredSize(new Dimension(Width/5 + 10, Height/4));
        buttonDisplay.add(buyButton, BorderLayout.NORTH);
        buyButton.setText("BUY");

        // Sell Button
        JButton sellButton = new JButton();
        sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println("Sell Stock");
            }
        });
        sellButton.setPreferredSize(new Dimension(Width/5 + 10, Height/4));
        buttonDisplay.add(sellButton, BorderLayout.SOUTH);
        sellButton.setText("SELL");

        JPanel textDisplay = new JPanel();
        textDisplay.setPreferredSize(new Dimension(Width/2 - 5, Height/10));
        stockPanel2.setBorder(border);
        textDisplay.setLayout(new FlowLayout());
        textDisplay.setBackground(new Color (70,70,80));

        JTextField textField = new JTextField();
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                text = textField.getText();
                if (getHashed.getSFH(text).getName() != null) {
                    stockName.setText(getHashed.getSFH(text).getName());
                    stockTicker.setText(getHashed.getSFH(text).getTicker());
                    stockPrice.setText("$" + getHashed.getSFH(text).getPrice());
                    currentStock = getHashed.getSFH(text);
                }
                else {
                    stockName.setText("Stock Not Found");
                    stockTicker.setText("");
                    stockPrice.setText("");
                }
            }
        });

        textField.setColumns(20);
        textField.setSize(Width/6, 20);
        textDisplay.add(textField);
        

        testPan.add(textDisplay, BorderLayout.NORTH);
        testPan.add(stockDisplay, BorderLayout.WEST);
        testPan.add(buttonDisplay, BorderLayout.EAST);
        

        this.add(testPan, BorderLayout.WEST);
        this.add(stockPanel2, BorderLayout.EAST);
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            //WebScrapper.updateAllStocks();
            //Add multithreading to updating all stocks (will make runtime 2812x faster)
            WebScrapper.writeJSON();
            //Save user data as well
            return;
            }
        });
        while(this.isActive()) {
            if(currentStock != null)  {currentStock.updatePrice();} // Probably want to make a seperate thread to do this
        }
    }
}
