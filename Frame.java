package Draw;
import java.util.ArrayList;
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
import javax.swing.plaf.PanelUI;
import javax.swing.BorderFactory;
import WebScrapper.stockObject;
import UserInteractions.User;


public class Frame extends JFrame {
    private int Height;
    private int Width;
    private String text;
    private static stockObject currentStock;
    private static int amount = 0;
    public Frame(int h, int w) {
        User ronald = new User();
        ArrayList<StockInfo> panelsOfStocks = new ArrayList<>();
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
        stockPanel2.setLayout(new GridLayout(11, 0));

        JLabel userNW = new JLabel("", SwingConstants.CENTER);
        userNW.setForeground(Color.WHITE);
        userNW.setText(Double.toString(ronald.getNetWorth()));
        stockPanel2.add(userNW);

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
                amount = Integer.parseInt(count.getText());
                amount++;
                count.setText(Integer.toString(amount));
            }
        });
        JButton downButton = new JButton();
        downButton.setText("↓");
        downButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                amount = Integer.parseInt(count.getText());
                if (amount > 0) {amount--;}
                count.setText(Integer.toString(amount));
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
                ronald.userSummary();
                ronald.buyStock(amount);
                userNW.setText(Double.toString(ronald.getNetWorth()));
                for(int i = 0; i < panelsOfStocks.size(); i++){
                    if(currentStock == panelsOfStocks.get(i).getMyStock()){
                        panelsOfStocks.get(i).revalidate();
                        panelsOfStocks.get(i).repaint();
                        stockPanel2.revalidate();
                        stockPanel2.repaint();
                        break;
                    }
                    if(i == panelsOfStocks.size() - 1){
                        StockInfo someStock = new StockInfo(currentStock);
                        stockPanel2.add(someStock);
                        stockPanel2.revalidate();
                        stockPanel2.repaint();
                    }
                }
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
                if (getHashed.getSFH(text) != null) {
                    stockName.setText(getHashed.getSFH(text).getName());
                    stockTicker.setText(getHashed.getSFH(text).getTicker());
                    stockPrice.setText("$" + getHashed.getSFH(text).getPrice());
                    currentStock = getHashed.getSFH(text);
                }
                else {
                    currentStock = null;
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
            WebScrapper.updateAllStocks();
            //Add multithreading to updating all stocks (will make runtime 2812x faster)
            WebScrapper.writeJSON();
            //Save user data as well
            return;
            }
        });
        // add Constant Updates to stock portfolio in while loop.
        while(this.isActive()) {
            if(currentStock != null)  {currentStock.someMethod();} // Probably want to make a seperate thread to do this
        }
    }
    public static stockObject getStock() {
        return currentStock;
    }
    public static void setStock(stockObject o) {
        currentStock = o;
        return;
    }
    public static int getAmount() {
        return amount;
    }
    public static void setAmount(int a) {
        amount = a;
        return;
    }
}