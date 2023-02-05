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

    final int SEARCH_BAR_POSITION = 1;
    final int STOCKGRAPH_POSITION = 2;

    AutoCompleteJComboBox
            searchbar;


    String current_string = "TSLA";

    JPanel home_panel, account_panel, transaction_panel, deposit_panel;
    JLabel stock_teacher;
    JTextField search;
    JButton home_button, buy_sell_button, transaction_buttton, logout_button;
    JMenuBar menuBar;

    JPanel rectract_panel;
    JPanel top_page = new JPanel();
    JMenu account_menu;
    JMenuItem logout_item;
    JTabbedPane tabbedPane;
    Border border = BorderFactory.createRaisedBevelBorder();

    HashMap<String, String> stock_convertion = new HashMap<>();

    JPanel retract_choice;
    JButton retract_button;
    JButton deposit_button;
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
    SpinnerNumberModel RetractnumberModel;
    JSpinner RetractnumberChooser;
    JButton Retracttransaction;

    public ConnectedFrames(UserAccount UserAccount) throws Exception, UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {


        //functions to look for historical data
        ConnectedFrames frames = this;
        this.account = UserAccount;
        this.pack();

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
                    east_panel.add(rectract_panel);
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
        //initialize search bar
        //hasmap with stock name as set and stock_code as value
        List<String> stockname = new ArrayList<String>();
        account.initialize_searchbar(stockname, stock_convertion);
        StringSearchable searchable = new StringSearchable(stockname);
        searchbar = new AutoCompleteJComboBox(searchable);


        JPanel search_panel = new JPanel();
        search_panel.setLayout(new BoxLayout(search_panel, BoxLayout.X_AXIS));
        searchbar.setPreferredSize(new Dimension(300, 5));
        searchbar.setBorder(BorderFactory.createEmptyBorder());
        search_panel.setBorder(BorderFactory.createLoweredBevelBorder());

        search_panel.setPreferredSize(new Dimension(305, 5));
        search_panel.add(searchbar);
        JButton search_button = new JButton();
        search_button.setIcon(new

                ImageIcon("icon/search.png"));
        search_panel.add(search_button);
        search_panel.setOpaque(false);


        search_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String selection = (String) searchbar.getSelectedItem();
                String code = stock_convertion.get(selection);
                current_string = code;

                for (Component i : west_panel.getComponents()) {
                    if (i instanceof StockGraph) west_panel.remove(i);
                }

                try {
                    west_panel.add(new StockGraph(current_string));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                sell_choice.setBackground(Color.white);
                sell_choice.setForeground(Color.black);
                sell_choice.setFont(new Font("Arial", Font.PLAIN, 12));
                buy_choice.setBackground(Color.green);
                buy_choice.setForeground(Color.white);
                buy_choice.setFont(new Font("Arial", Font.BOLD, 12));
                numberModel.setMaximum(account.getMoney_available());
                transaction.setText("Buy " + current_string);
                Border border = BorderFactory.createRaisedBevelBorder();
                buyingPanel.setBorder(BorderFactory.createTitledBorder(border, "Buy/Sell " + current_string, TitledBorder.CENTER,
                        TitledBorder.TOP, UIManager.getFont("h2.font"), Color.black));
                east_panel.repaint();
                for (ActionListener listener : transaction.getActionListeners()) {
                    transaction.removeActionListener(listener);
                }
                transaction.addActionListener(new Buy_Listener(current_string, numberChooser));

                west_panel.revalidate();
                west_panel.repaint();

                east_panel.revalidate();
                east_panel.repaint();
            }
        });


        west_panel = new

                JPanel();
        west_panel.setLayout(new

                BoxLayout(west_panel, BoxLayout.Y_AXIS));


        west_panel.add(search_panel);


        balancePane balancePanel = new balancePane(account);
        Component largerRigidArea = Box.createRigidArea(new Dimension(0, 10));
        Component rigidArea = Box.createRigidArea(new Dimension(0, 10));
        rigidArea.setBackground(Color.white);

        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        UIManager.setLookAndFeel(new

                FlatLightLaf());
        //UIManager.put("TabbedPane.selectedBackground", Color.green);

        UIManager.put("TabbedPane.showTabSeparators", true);
        UIManager.put("TabbedPane.tabSeparatorsFullHeight", true);
        tabbedPane = new

                JTabbedPane(JTabbedPane.LEFT);


        tabbedPane.setFocusable(false);
        tabbedPane.setForeground(Color.BLUE);
        tabbedPane.setBackground(Color.WHITE);
        //tabbedPane.setFont(UI.);
        //tabbedPane.setForeground(Color.BLUE);

        home_panel = new

                JPanel();
        home_panel.setLayout(new

                BoxLayout(home_panel, BoxLayout.X_AXIS));
        home_panel.setBackground(Color.white);
        //create home tab
        transaction_panel = new

                JPanel(); //create transaction tab

        deposit_panel = new

                JPanel();

        account_panel = new

                JPanel();


        stock_teacher = new

                JLabel("StockMaster");
        stock_teacher.setIcon(new

                ImageIcon("icon/stock-market.png"));

        top_page.add(stock_teacher);


        // top_page.add(search_panel);


        ImageIcon homeIcon = new ImageIcon("icon/home-button.png");
        tabbedPane.addTab("Home", homeIcon, home_panel);

        tabbedPane.add("Add/Retract Funds", deposit_panel);
        tabbedPane.add("Buy/Sell Stock", transaction_panel);
        tabbedPane.add("Account", account_panel);

        //configure home panel

        //create a StockGraphPanel that extends JPanel to plot stock panel


        StockGraph stockGraphPanel = new StockGraph("TSLA");
        west_panel.add(stockGraphPanel);


        rectract_panel = new

                JPanel();
        rectract_panel.setBorder(BorderFactory.createTitledBorder(border, "Add/Retract Funds ", TitledBorder.CENTER,
                TitledBorder.TOP, UIManager.getFont("h2.font"), Color.black));

        ///////////////////////////

        buyingPanel = new

                JPanel();
        buyingPanel.setBorder(BorderFactory.createTitledBorder(border, "Buy/Sell ", TitledBorder.CENTER,
                TitledBorder.TOP, UIManager.getFont("h2.font"), Color.black));
        retract_choice = new

                JPanel();
        retract_choice.setLayout(new

                BoxLayout(retract_choice, BoxLayout.X_AXIS));

        retract_button = new

                JButton("Retract");

        deposit_button = new

                JButton("Deposit");
        retract_button.setBackground(Color.white);
        retract_button.setForeground(Color.black);
        retract_button.setFont(new

                Font("Arial", Font.PLAIN, 12));
        deposit_button.setBackground(Color.green);
        deposit_button.setForeground(Color.white);
        deposit_button.setFont(new

                Font("Arial", Font.BOLD, 12));

        double max_amount = 0.0;
        RetractnumberModel = new

                SpinnerNumberModel(0.0, 0.0, Double.POSITIVE_INFINITY, 0.01);
        RetractnumberChooser = new

                JSpinner(RetractnumberModel);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                RetractnumberChooser.setEditor(new JSpinner.NumberEditor(RetractnumberChooser, "###,##0.0#"));
                JSpinner.NumberEditor jsEditor = (JSpinner.NumberEditor) RetractnumberChooser.getEditor();
                DefaultFormatter formatter = (DefaultFormatter) jsEditor.getTextField().getFormatter();
                formatter.setAllowsInvalid(false);

            }
        });


        Retracttransaction = new

                JButton("Add Funds ");
        Retracttransaction.setBackground(Color.green);
        Retracttransaction.setForeground(Color.white);
        Retracttransaction.setFont(new

                Font("Arial", Font.BOLD, 20));


        class Add_funds_listener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                try {
                    RetractnumberChooser.commitEdit();
                } catch (Exception ex) {

                }
                double amount = (Double) RetractnumberChooser.getValue();
                account.setMoney_available(account.getMoney_available() + amount);
                JOptionPane.showMessageDialog(frames, "You have succesfully added $" + amount + " to your account");
                for (Component l : east_panel.getComponents()) {
                    if (l instanceof balancePane) east_panel.remove(l);

                }
                east_panel.add(new balancePane(account), 3);
                east_panel.revalidate();
                east_panel.repaint();
            }
        }

        class Retract_funds_listener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                try {
                    RetractnumberChooser.commitEdit();
                } catch (Exception ex) {

                }

                double amount = (Double) RetractnumberChooser.getValue();
                double money_available = account.getMoney_available();

                if (amount > money_available) {
                    JOptionPane.showMessageDialog(frames, "The amount you have entered is more than you own");
                    return;
                } else {
                    account.setMoney_available(money_available - amount);
                    JOptionPane.showMessageDialog(frames, "You have succesfully retracted $" + amount + " to your account");
                    for (Component l : east_panel.getComponents()) {
                        if (l instanceof balancePane) east_panel.remove(l);
                    }

                    east_panel.add(new balancePane(account), 3);
                    east_panel.revalidate();
                    east_panel.repaint();
                }
            }
        }


        Retracttransaction.addActionListener(new Add_funds_listener());
        deposit_button.addActionListener(new ActionListener() { //what to do when click on deposit button


            public void actionPerformed(ActionEvent e) {
                RetractnumberModel.setMaximum(Double.POSITIVE_INFINITY);


                retract_button.setBackground(Color.white);
                retract_button.setForeground(Color.black);
                retract_button.setFont(new


                        Font("Arial", Font.PLAIN, 12));
                deposit_button.setBackground(Color.green);
                deposit_button.setForeground(Color.white);
                deposit_button.setFont(new

                        Font("Arial", Font.PLAIN, 12));
                Retracttransaction.setText("Add Funds");
                Retracttransaction.setBackground(Color.green);
                Retracttransaction.setForeground(Color.white);
                Retracttransaction.setFont(new

                        Font("Arial", Font.BOLD, 20));

                for (ActionListener l : Retracttransaction.getActionListeners()) {
                    Retracttransaction.removeActionListener(l);
                }
                Retracttransaction.addActionListener(new Add_funds_listener());
            }
        });

        retract_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RetractnumberModel.setMaximum(account.getMoney_available());

                try {
                    RetractnumberChooser.commitEdit();
                } catch (Exception exception) {

                }
                retract_button.setBackground(Color.green);
                retract_button.setForeground(Color.white);
                retract_button.setFont(new

                        Font("Arial", Font.PLAIN, 12));
                deposit_button.setBackground(Color.white);
                deposit_button.setForeground(Color.black);
                deposit_button.setFont(new

                        Font("Arial", Font.PLAIN, 12));
                Retracttransaction.setText("Retract Funds");
                Retracttransaction.setBackground(Color.green);
                Retracttransaction.setForeground(Color.white);
                Retracttransaction.setFont(new

                        Font("Arial", Font.BOLD, 20));

                for (ActionListener l : Retracttransaction.getActionListeners()) {
                    Retracttransaction.removeActionListener(l);
                }
                Retracttransaction.addActionListener(new Retract_funds_listener());
            }

        });
        retract_choice.setLayout(new BoxLayout(retract_choice, BoxLayout.X_AXIS));

        retract_choice.add(deposit_button);//add button for deposit
        retract_choice.add(retract_button);//add button for retracting
        retract_choice.add(RetractnumberChooser);//add button for retracting
        retract_choice.add(Retracttransaction);//add button for retracting


        rectract_panel.add(retract_choice);


        //small panel inside buying panel to contain two buttons that will be used to use to select if we want to
        //buy or sell
        buying_choice = new

                JPanel();
        buying_choice.setLayout(new

                BoxLayout(buying_choice, BoxLayout.X_AXIS));

        buy_choice = new

                JButton("Buy");

        sell_choice = new

                JButton("Sell");
        sell_choice.setBackground(Color.white);
        sell_choice.setForeground(Color.black);
        sell_choice.setFont(new

                Font("Arial", Font.PLAIN, 12));
        buy_choice.setBackground(Color.green);
        buy_choice.setForeground(Color.white);
        buy_choice.setFont(new

                Font("Arial", Font.BOLD, 12));


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
                    east_panel.add(rectract_panel);
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
        numberModel = new

                SpinnerNumberModel(0.0, 0.0, account.getMoney_available(), 0.01);
        numberChooser = new

                JSpinner(numberModel);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                numberChooser.setEditor(new JSpinner.NumberEditor(numberChooser, "###,##0.0#"));
                JSpinner.NumberEditor jsEditor = (JSpinner.NumberEditor) numberChooser.getEditor();
                DefaultFormatter formatter = (DefaultFormatter) jsEditor.getTextField().getFormatter();
                formatter.setAllowsInvalid(false);

            }
        });


        transaction = new

                JButton("Buy " + current_string);
        transaction.setBackground(Color.green);
        transaction.setForeground(Color.white);
        transaction.setFont(new

                Font("Arial", Font.BOLD, 20));
        Buy_Listener buy_listener = new Buy_Listener(current_string, numberChooser);
        transaction.addActionListener(buy_listener);


        buy_choice.addActionListener(e ->

        {
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

        sell_choice.addActionListener(e ->

        {
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

        buying_choice.add(numberChooser);
        buying_choice.add(transaction);
        //Action Listener for the portfolio button to interact with the graph


        //panel to hold the balance and the amount of money we have


        east_panel.setLayout(new

                BoxLayout(east_panel, BoxLayout.Y_AXIS));
        east_panel.setBackground(Color.white);
        east_panel.add(buyingPanel);
        east_panel.add(largerRigidArea);

        east_panel.add(rectract_panel);
        east_panel.add(largerRigidArea);
        east_panel.add(balancePanel);

        porfolioPane portfolio_panel = new porfolioPane(account, this);

        west_panel.repaint();

        east_panel.add(largerRigidArea);
        east_panel.add(portfolio_panel);


        west_panel.setPreferredSize(new Dimension(570, 550));
        east_panel.setPreferredSize(new Dimension(450, 550));

        home_panel.add(Box.createRigidArea(new

                Dimension(10, 0)));

        home_panel.add(west_panel);
        home_panel.add(Box.createRigidArea(new

                Dimension(10, 0)));
        home_panel.add(east_panel);
        home_panel.add(Box.createRigidArea(new

                Dimension(10, 0)));

        // tabbedPane.setLayout(());

        this.

                getContentPane().

                add(tabbedPane);
        this.

                pack();

    }


}
