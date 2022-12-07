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
import java.awt.Font;
import javax.swing.border.Border;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.BorderFactory;
import WebScrapper.stockObject;
import Financial.GenerateCapital;
import Financial.StartingMoney;
import java.util.Stack;
import UserInteractions.User;
import java.util.ArrayList;

public class Frame extends JFrame {
    private int Height;
    private int Width;
    private String text;
    private static stockObject currentStock;
    private static int amount = 0;
    private int rollcount;
    private Double capital;
    public static Stack<Double> rolls;
    private User ronald;
    private boolean contGoing;
    public Frame(int h, int w) {
        this.contGoing = true;
        User donald = new User();
        ronald = donald;
        ArrayList<StockInfo> panelsOfStocks = new ArrayList<>();
        Height = h;
        Width = w;
        this.setTitle("US Stock Market");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(Width, Height);
        this.setLayout(new BorderLayout());
        this.setResizable(true);
        Border border = BorderFactory.createLineBorder(Color.WHITE);

        JPanel CapPanel = new JPanel();
        CapPanel.setLayout(new BorderLayout());
        CapPanel.setAlignmentX(SwingConstants.CENTER);
        CapPanel.setPreferredSize(new Dimension(Width/3, Height/3));
        CapPanel.setBackground(new Color (70,70,80));
        CapPanel.setBorder(border);
        
        JPanel ButtonPanel = new JPanel();
        ButtonPanel.setLayout(new BorderLayout());
        ButtonPanel.setBackground(new Color (70,70,80));
        ButtonPanel.setPreferredSize(new DimensionUIResource((Width/3), Height/4));

        JLabel CapTitle = new JLabel("", SwingConstants.CENTER);
        CapTitle.setForeground(Color.WHITE);
        CapTitle.setText("Current User Capital:");
        CapTitle.setFont(new Font("Arial", Font.BOLD, 20));
        
        JLabel CapVal = new JLabel("", SwingConstants.CENTER);
        CapVal.setForeground(Color.WHITE);
        CapVal.setText("$0.00");
        CapVal.setFont(new Font("Arial", Font.BOLD, 20));
        
        JPanel testPan = new JPanel();
        testPan.setPreferredSize(new Dimension(Width/3, Height));
        testPan.setBackground(new Color (70,70,80));
        testPan.setBorder(border);

        JPanel stockPanel2 = new JPanel();
        stockPanel2.setPreferredSize(new Dimension(Width/3, Height));
        stockPanel2.setBackground(new Color (120,70,80));
        stockPanel2.setBorder(border);
        stockPanel2.setLayout(new GridLayout(11, 0));

        JLabel userNW = new JLabel("Net Worth: $", SwingConstants.CENTER);
        userNW.setForeground(Color.WHITE);
        userNW.setText("Net Worth: $" + Double.toString(ronald.getNetWorth()));
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
        buttonDisplay.setPreferredSize(new Dimension(Width/5 + 10, Height/5));
        buttonDisplay.setBackground(new Color (70,70,120));
        buttonDisplay.setBorder(border);
        buttonDisplay.setLayout(new BorderLayout());
        
        // Buy Button
        JButton buyButton = new JButton();
        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                ronald.someMethod();
                if (panelsOfStocks.size() > 0) {
                    for(int i = 0; i < panelsOfStocks.size(); i++) {
                        if(Integer.parseInt(panelsOfStocks.get(i).getQuantity()) <= 0) {
                            stockPanel2.remove(panelsOfStocks.get(i));
                            panelsOfStocks.remove(i);
                            stockPanel2.revalidate();
                            stockPanel2.repaint();
                        }
                    }
                }
                if (amount >= 1 && ronald.getMoney() >= Double.parseDouble(currentStock.getPrice())) {
                    ronald.buyStock(amount);
                    userNW.setText("Net Worth: $" + Double.toString(ronald.getNetWorth()));
                    CapVal.setText("$" + Double.toString(ronald.getMoney()));
                    CapVal.revalidate();
                    CapVal.repaint();
                    CapPanel.revalidate();
                    CapPanel.repaint();
                    stockPanel2.revalidate();
                    stockPanel2.repaint();
                if (panelsOfStocks.size() == 0) {
                    StockInfo someStock = new StockInfo(currentStock);
                    panelsOfStocks.add(someStock);
                    stockPanel2.add(someStock);
                    CapVal.setText("$" + Double.toString(ronald.getMoney()));
                    CapVal.revalidate();
                    CapVal.repaint();
                    CapPanel.revalidate();
                    CapPanel.repaint();
                    stockPanel2.revalidate();
                    stockPanel2.repaint();
                }
                else {
                    int i = 0;
                    for(i = 0; i < panelsOfStocks.size(); i++){
                        if(currentStock == panelsOfStocks.get(i).getMyStock()){
                            panelsOfStocks.get(i).setQuantity(currentStock.getQauntity());
                            panelsOfStocks.get(i).getTickQuant().setText( "   " + currentStock.getTicker() + "       Amount Owned: " + currentStock.getQauntity());
                            panelsOfStocks.get(i).revalidate();
                            panelsOfStocks.get(i).repaint();
                            CapVal.setText("$" + Double.toString(ronald.getMoney()));
                            CapVal.revalidate();
                            CapVal.repaint();
                            CapPanel.revalidate();
                            CapPanel.repaint();
                            stockPanel2.revalidate();
                            stockPanel2.repaint();
                            break;
                        }
                    }
                    if(i == panelsOfStocks.size()){
                        StockInfo randoStock = new StockInfo(currentStock);
                        panelsOfStocks.add(randoStock);
                        stockPanel2.add(randoStock);
                        CapVal.setText("$" + Double.toString(ronald.getMoney()));
                        CapVal.revalidate();
                        CapVal.repaint();
                        CapPanel.revalidate();
                        CapPanel.repaint();
                        stockPanel2.revalidate();
                        stockPanel2.repaint();
                    }
                }
                }
            }
        });
        buttonDisplay.add(buyButton, BorderLayout.WEST);
        buyButton.setPreferredSize(new Dimension((Width/2)/4 - (Width/50), Height/20));
        buyButton.setText("BUY");

        // Sell Button
        JButton sellButton = new JButton();
        sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                ronald.someMethod();
                if (amount >= 1) {
                    ronald.sellStock(amount);
                    userNW.setText("Net Worth: $" + Double.toString(ronald.getNetWorth()));
                    if (panelsOfStocks.size() > 0) {
                        for(int i = 0; i < panelsOfStocks.size(); i++) {
                            if(Integer.parseInt(panelsOfStocks.get(i).getQuantity()) - amount == 0 && panelsOfStocks.get(i).getMyStock() == currentStock) {
                                stockPanel2.remove(panelsOfStocks.get(i));
                                panelsOfStocks.remove(i);
                                CapVal.setText("$" + Double.toString(ronald.getMoney()));
                                CapVal.revalidate();
                                CapVal.repaint();
                                CapPanel.revalidate();
                                CapPanel.repaint();
                                stockPanel2.revalidate();
                                stockPanel2.repaint();
                            }
                        }
                    }
                    for(int i = 0; i < panelsOfStocks.size(); i++){
                        if(currentStock == panelsOfStocks.get(i).getMyStock()){
                            panelsOfStocks.get(i).setQuantity(currentStock.getQauntity());
                            panelsOfStocks.get(i).getTickQuant().setText( "   " + currentStock.getTicker() + "       Amount Owned: " + currentStock.getQauntity());
                            panelsOfStocks.get(i).revalidate();                            
                            panelsOfStocks.get(i).repaint();
                            CapVal.setText("$" + Double.toString(ronald.getMoney()));
                            CapVal.revalidate();
                            CapVal.repaint();
                            CapPanel.revalidate();
                            CapPanel.repaint();
                            stockPanel2.revalidate();
                            stockPanel2.repaint();
                            break;
                        }
                    }
                }
            }
        });
        sellButton.setPreferredSize(new Dimension((Width/2)/4 - (Width/50), Height/20));
        buttonDisplay.add(sellButton, BorderLayout.EAST);
        sellButton.setText("SELL");

        JPanel textDisplay = new JPanel();
        textDisplay.setPreferredSize(new Dimension(Width/3 - 5, Height/10));
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
        testPan.add(stockDisplay, BorderLayout.CENTER);
        testPan.add(buttonDisplay, BorderLayout.SOUTH);

        //

        CapPanel.add(CapVal, BorderLayout.CENTER);
        CapPanel.add(CapTitle, BorderLayout.NORTH);

        JButton RerollButton = new JButton();
        rollcount = 10;
        RerollButton.setText(("Roll (Remaining: " + rollcount + ")"));
        RerollButton.setPreferredSize(new Dimension(Width/6 - 1, ButtonPanel.getHeight()));
        JButton AcceptButton = new JButton();
        AcceptButton.setText("Accept");
        AcceptButton.setPreferredSize(new Dimension(Width/6 - 1, ButtonPanel.getHeight()));

        ButtonPanel.add(RerollButton, BorderLayout.WEST);
        ButtonPanel.add(AcceptButton, BorderLayout.EAST);
        CapPanel.add(ButtonPanel, BorderLayout.SOUTH);
        rolls = new Stack<>();
        StartingMoney startmoney = new StartingMoney(); 
        RerollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (rollcount <= 1){
                    ronald.setCapital(capital);
                    userNW.setText("Net Worth: $" + Double.toString(ronald.getNetWorth()));
                    userNW.revalidate();
                    userNW.repaint();
                    stockPanel2.revalidate();
                    stockPanel2.repaint();
                    CapPanel.remove(ButtonPanel);
                    CapPanel.revalidate();
                }
                else {
                    GenerateCapital generator = new GenerateCapital();
                    capital = generator.rollroulette();
                    stockPanel2.revalidate();
                    stockPanel2.repaint();
                    rolls.push(capital);
                    CapVal.setText(String.valueOf("$" + capital));
                    rollcount--;
                    RerollButton.setText(("Roll (Remaining: " + rollcount + ")"));
                }
                
            }
        });
        AcceptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                ronald.setCapital(capital);
                userNW.setText("Net Worth: $" + Double.toString(ronald.getNetWorth()));
                userNW.revalidate();
                userNW.repaint();
                stockPanel2.revalidate();
                stockPanel2.repaint();
                startmoney.giveMeMoney(rolls.pop());
                ronald.setCapital(startmoney.getMoney());
                CapPanel.remove(ButtonPanel);
                CapPanel.revalidate();
                CapPanel.repaint();
            }
        });
        

        this.add(testPan, BorderLayout.WEST);
        this.add(stockPanel2, BorderLayout.CENTER);
        this.add(CapPanel, BorderLayout.EAST);
        this.setVisible(true);
        Frame tempFrame = this;
        this.addWindowListener(new WindowAdapter() {
            Thread myThread;
            @Override
            public void windowOpened(WindowEvent e) {
                contGoing = true;
                StockUpdating uStocks = new StockUpdating(panelsOfStocks, tempFrame, CapPanel, userNW, stockPrice);
                myThread = new Thread(uStocks);
                myThread.start();
            }
            @Override
            public void windowClosing(WindowEvent e) {
                contGoing = false;
                //WebScrapper.updateAllStocks(); //Multithreading to make run faster, however needs some optmization (Won't be in final version just yet)
                WebScrapper.writeJSON();
                //Save user data as well
                return;
            }
        });
        // add Constant Updates to stock portfolio in while loop.
    }
    public static stockObject getStock() {
        return currentStock;
    }
    public boolean getGoing() {
        return this.contGoing;
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
    public User getUser() {
        return this.ronald;
    }
}