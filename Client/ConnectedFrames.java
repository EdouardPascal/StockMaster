package Client;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ConnectedFrames extends JFrame {

    HashMap<String, String> stock_convertion = new HashMap<>();//hasmap with stock name as set and stock_code as value
    List<String> stockname = new ArrayList<String>();


    StringSearchable searchable = new StringSearchable(stockname);


    String current_string = "TSLA";

    JPanel home_panel, account_panel, transaction_panel, deposit_panel;
    JLabel stock_teacher;
    JTextField search;
    JButton home_button, buy_sell_button, transaction_buttton, logout_button;
    JMenuBar menuBar;

    JPanel top_page = new JPanel();
    JMenu account_menu;
    JMenuItem logout_item;
    JTabbedPane tabbedPane;
    Border border = BorderFactory.createRaisedBevelBorder();

    JPanel east_panel = new JPanel();
    JPanel west_panel;

    JPanel buying_choice;
    JPanel buyingPanel;
    JButton buy_choice;
    JButton sell_choice;
    SpinnerNumberModel numberModel;
    JSpinner numberChooser;
    JButton transaction;
    UserAccount account;

    public ConnectedFrames(UserAccount UserAccount) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        

        //functions to look for historical data
        ConnectedFrames frames = this;
        this.account = UserAccount;
        this.pack();

        balancePane balancePanel = new balancePane(account);
        Component largerRigidArea = Box.createRigidArea(new Dimension(0, 10));
        Component rigidArea = Box.createRigidArea(new Dimension(0, 10));
        rigidArea.setBackground(Color.white);

        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        UIManager.setLookAndFeel(new FlatLightLaf());
        //UIManager.put("TabbedPane.selectedBackground", Color.green);

        UIManager.put("TabbedPane.showTabSeparators", true);
        UIManager.put("TabbedPane.tabSeparatorsFullHeight", true);
        tabbedPane = new JTabbedPane(JTabbedPane.LEFT);


        tabbedPane.setFocusable(false);
        tabbedPane.setForeground(Color.BLUE);
        tabbedPane.setBackground(Color.WHITE);
        //tabbedPane.setFont(UI.);
        //tabbedPane.setForeground(Color.BLUE);

        home_panel = new JPanel();
        home_panel.setLayout(new BoxLayout(home_panel, BoxLayout.X_AXIS));
        home_panel.setBackground(Color.white);
        //create home tab
        transaction_panel = new JPanel(); //create transaction tab
        deposit_panel = new JPanel();
        account_panel = new JPanel();


        stock_teacher = new JLabel("StockMaster");
        stock_teacher.setIcon(new ImageIcon("icon/stock-market.png"));

        top_page.add(stock_teacher);

        JPanel search_panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        search = new JTextField(30);
        search.setBorder(BorderFactory.createEmptyBorder());
        search_panel.setBorder(BorderFactory.createLoweredBevelBorder());

        search_panel.add(search);

        top_page.add(search_panel);

        JButton search_button = new JButton();
        search_button.setIcon(new ImageIcon("icon/search.png"));
        search_panel.add(search_button);
        search_panel.setOpaque(false);

        ImageIcon homeIcon = new ImageIcon("icon/home-button.png");
        tabbedPane.addTab("Home", homeIcon, home_panel);

        tabbedPane.add("Add/Retract Funds", deposit_panel);
        tabbedPane.add("Buy/Sell Stock", transaction_panel);
        tabbedPane.add("Account", account_panel);

        //configure home panel

        //create a StockGraphPanel that extends JPanel to plot stock panel
        west_panel = new JPanel();


        StockGraph stockGraphPanel = new StockGraph("TSLA");
        west_panel.add(stockGraphPanel);

        buyingPanel = new JPanel();
        buyingPanel.setBorder(BorderFactory.createTitledBorder(border, "Buy/Sell ", TitledBorder.CENTER,
                TitledBorder.TOP, UIManager.getFont("h2.font"), Color.black));

        //small panel inside buying panel to contain two buttons that will be used to use to select if we want to
        //buy or sell
        buying_choice = new JPanel();
        buying_choice.setLayout(new BoxLayout(buying_choice, BoxLayout.X_AXIS));

        buy_choice = new JButton("Buy");
        sell_choice = new JButton("Sell");
        sell_choice.setBackground(Color.white);
        sell_choice.setForeground(Color.black);
        sell_choice.setFont(new Font("Arial", Font.PLAIN, 12));
        buy_choice.setBackground(Color.green);
        buy_choice.setForeground(Color.white);
        buy_choice.setFont(new Font("Arial", Font.BOLD, 12));


        //listener to add to transaction button to perform operation


        class Sell_Listener implements ActionListener {
            String stock;
            double amount;
            JSpinner jspinner;

            public Sell_Listener(String code, JSpinner spinner) {
                this.stock = code;
                this.jspinner = spinner;
            }

            public void actionPerformed(ActionEvent e) {
                try {
                    jspinner.commitEdit();

                    account.sell_stock(stock, (Double) jspinner.getValue());
                    east_panel.removeAll();
                    east_panel.add(buyingPanel);
                    east_panel.add(Box.createRigidArea(new Dimension(0, 5)));
                    east_panel.add(new balancePane(account));
                    east_panel.add(Box.createRigidArea(new Dimension(0, 5)));
                    east_panel.add(new porfolioPane(account, frames));

                    east_panel.repaint();
                    east_panel.revalidate();


                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }


        }

        class Buy_Listener implements ActionListener {
            String stock;
            JSpinner jSpinner;

            public Buy_Listener(String code, JSpinner spinner) {
                this.stock = code;
                this.jSpinner = spinner;
            }

            public void actionPerformed(ActionEvent e) {
                try {
                    jSpinner.commitEdit();
                    account.buy_stock(stock, (Double) jSpinner.getValue());

                    east_panel.removeAll();
                    east_panel.add(buyingPanel);
                    east_panel.add(Box.createRigidArea(new Dimension(0, 5)));
                    east_panel.add(new balancePane(account));
                    east_panel.add(Box.createRigidArea(new Dimension(0, 5)));


                    east_panel.add(new porfolioPane(account, frames));

                    east_panel.repaint();
                    east_panel.revalidate();

                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }
        }

        buyingPanel.add(buying_choice);

        double maximum_amount = 0.0;
        numberModel = new SpinnerNumberModel(0.0, 0.0, account.getMoney_available(), 0.01);
        numberChooser = new JSpinner(numberModel);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {


                numberChooser.setEditor(new JSpinner.NumberEditor(numberChooser, "###,##0.0#"));
                JSpinner.NumberEditor jsEditor = (JSpinner.NumberEditor) numberChooser.getEditor();
                DefaultFormatter formatter = (DefaultFormatter) jsEditor.getTextField().getFormatter();
                formatter.setAllowsInvalid(false);

                buyingPanel.add(numberChooser);
            }
        });


        transaction = new JButton("Buy " + current_string);
        transaction.setBackground(Color.green);
        transaction.setForeground(Color.white);
        transaction.setFont(new Font("Arial", Font.BOLD, 20));
        Buy_Listener buy_listener = new Buy_Listener(current_string, numberChooser);
        transaction.addActionListener(buy_listener);

        buyingPanel.add(transaction);
        buy_choice.addActionListener(e -> {
            sell_choice.setBackground(Color.white);
            sell_choice.setForeground(Color.black);
            sell_choice.setFont(new Font("Arial", Font.PLAIN, 12));
            buy_choice.setBackground(Color.green);
            buy_choice.setForeground(Color.white);
            buy_choice.setFont(new Font("Arial", Font.BOLD, 12));
            numberModel.setMaximum(account.getMoney_available());
            transaction.setText("Buy " + current_string);

            try {
                numberChooser.commitEdit();
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
            for (ActionListener listener : transaction.getActionListeners()) {
                transaction.removeActionListener(listener);
            }
            Double value = (Double) numberChooser.getValue();
            double value_double = value.doubleValue();
            transaction.addActionListener(new Buy_Listener(current_string, numberChooser));
            buyingPanel.repaint();
        });

        sell_choice.addActionListener(e -> {
            buy_choice.setBackground(Color.white);
            buy_choice.setForeground(Color.black);
            buy_choice.setFont(new Font("Arial", Font.PLAIN, 12));
            sell_choice.setBackground(Color.green);
            sell_choice.setForeground(Color.white);
            sell_choice.setFont(new Font("Arial", Font.BOLD, 12));
            try {
                numberModel.setMaximum(account.real_time_price(current_string) *
                        account.getList_stock().get(current_string));
            } catch (Exception exception) {

            }
            transaction.setText("Sell " + current_string);

            try {
                numberChooser.commitEdit();
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
            for (ActionListener listener : transaction.getActionListeners()) {
                transaction.removeActionListener(listener);
            }
            Double value = (Double) numberChooser.getValue();
            double value_double = value.doubleValue();
            transaction.addActionListener(new Sell_Listener(current_string, numberChooser));
            buyingPanel.repaint();
        });


        buying_choice.add(buy_choice);
        buying_choice.add(sell_choice);


        //Action Listener for the portfolio button to interact with the graph


        //panel to hold the balance and the amount of money we have


        east_panel.setLayout(new BoxLayout(east_panel, BoxLayout.Y_AXIS));
        east_panel.setBackground(Color.white);
        east_panel.add(buyingPanel);
        east_panel.add(largerRigidArea);
        east_panel.add(balancePanel);

        porfolioPane portfolio_panel = new porfolioPane(account, this);

        west_panel.repaint();

        east_panel.add(largerRigidArea);
        east_panel.add(portfolio_panel);
        home_panel.add(Box.createRigidArea(new Dimension(10, 0)));
        home_panel.add(west_panel);
        home_panel.add(Box.createRigidArea(new Dimension(10, 0)));
        home_panel.add(east_panel);
        home_panel.add(Box.createRigidArea(new Dimension(10, 0)));

        // tabbedPane.setLayout(());

        this.getContentPane().add(tabbedPane);
        this.pack();

    }


}
