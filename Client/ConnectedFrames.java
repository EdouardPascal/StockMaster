package Client;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.HashMap;


public class ConnectedFrames extends JFrame {

    ConnectedPanel home_panel, account_panel, transaction_panel, deposit_panel;
    JLabel stock_teacher;
    JTextField search;
    JButton home_button, buy_sell_button, transaction_buttton, logout_button;
    JMenuBar menuBar;

    JPanel top_page = new JPanel();
    JMenu account_menu;
    JMenuItem logout_item;
    JTabbedPane tabbedPane;

    public ConnectedFrames(UserAccount account) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        //functions to look for historical data


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

        home_panel = new ConnectedPanel();
        home_panel.setBackground(Color.white);
        //create home tab
        transaction_panel = new ConnectedPanel(); //create transaction tab
        deposit_panel = new ConnectedPanel();
        account_panel = new ConnectedPanel();


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

        //create a jfreechart to plot stock panel
        JPanel StockGraphPanel = new JPanel();

        
        JPanel balancePanel = new JPanel();
        balancePanel.setLayout(new BoxLayout(balancePanel, BoxLayout.Y_AXIS));

        balancePanel.setBackground(Color.lightGray);
        Border border = BorderFactory.createRaisedBevelBorder();
        balancePanel.setBorder(BorderFactory.createTitledBorder(border, "Balance", TitledBorder.CENTER,
                TitledBorder.TOP, UIManager.getFont("h2.font"), Color.black));

        // JLabel balanceTitle = new JLabel("Balance");
        //balanceTitle.setFont(UIManager.getFont("h2.font"));
        // balanceTitle.setForeground(Color.black);
        //  balanceTitle.setBackground(new Color(38, 36, 34));

        //balancePanel.add(balanceTitle);
        Component largerRigidArea = Box.createRigidArea(new Dimension(0, 10));

        Component rigidArea = Box.createRigidArea(new Dimension(0, 10));
        rigidArea.setBackground(Color.white);

        //balancePanel.add(rigidArea);

        JPanel totalMoneyPanel = new JPanel();
        totalMoneyPanel.setBackground(Color.white);
        totalMoneyPanel.setBorder(border);

        JLabel totalMoneyLabel = new JLabel("Total Money");
        totalMoneyLabel.setForeground(Color.darkGray);
        totalMoneyLabel.setFont(UIManager.getFont("h3.font"));

        JLabel totalAmountMoney;
        try {
            totalAmountMoney = new JLabel(String.valueOf(account.getTotal_money()));
            totalAmountMoney.setFont(UIManager.getFont("h3.font"));
            totalAmountMoney.setForeground(Color.black);

            totalMoneyPanel.add(totalMoneyLabel);
            totalMoneyPanel.add(totalAmountMoney, BorderLayout.WEST);


            JPanel availableMoneyPanel = new JPanel();
            availableMoneyPanel.setBorder(border);
            availableMoneyPanel.setBackground(Color.white);

            JLabel availableMoneyLabel = new JLabel("Available Funds");
            availableMoneyLabel.setFont(UIManager.getFont("h3.font"));
            availableMoneyLabel.setForeground(Color.darkGray);

            JLabel availableAmountMoney = new JLabel(String.valueOf(account.getMoney_available()));
            availableAmountMoney.setFont(UIManager.getFont("h3.font"));
            availableAmountMoney.setForeground(Color.black);

            availableMoneyPanel.add(availableMoneyLabel, BorderLayout.EAST);
            availableMoneyPanel.add(availableAmountMoney, BorderLayout.WEST);
            //availableMoneyPanel.setBorder(new EmptyBorder(0, 0, 15, 5));

            JPanel investedMoneyPanel = new JPanel();
            investedMoneyPanel.setBackground(Color.white);

            JLabel investedMoneyLabel = new JLabel("Total Invested");

            investedMoneyLabel.setFont(UIManager.getFont("h3.font"));
            investedMoneyLabel.setForeground(Color.darkGray);

            JLabel investedAmountMoney = new JLabel(String.valueOf(account.getMoney_invested()));
            investedAmountMoney.setFont(UIManager.getFont("h3.font"));
            investedAmountMoney.setForeground(Color.black);

            investedMoneyPanel.add(investedMoneyLabel, BorderLayout.EAST);
            investedMoneyPanel.add(investedAmountMoney, BorderLayout.WEST);
            investedMoneyPanel.setBorder(border);
            // investedMoneyPanel.setBorder(new EmptyBorder(0, 0, 15, 5));


            balancePanel.add(totalMoneyPanel);

            balancePanel.add(rigidArea);

            balancePanel.add(availableMoneyPanel);
            balancePanel.add(rigidArea);

            balancePanel.add(investedMoneyPanel);
            balancePanel.add(rigidArea);
            //  balancePanel.setBorder(new EmptyBorder(0, 0, 50, 70));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());

        }

        JPanel east_panel = new JPanel();
        east_panel.setLayout(new BoxLayout(east_panel, BoxLayout.Y_AXIS));
        east_panel.setBackground(Color.white);
        east_panel.add(balancePanel);

        JPanel portfolio_panel = new JPanel();
        portfolio_panel.setLayout(new BoxLayout(portfolio_panel, BoxLayout.Y_AXIS));
        portfolio_panel.setBackground(Color.lightGray);

        portfolio_panel.setBorder(BorderFactory.createTitledBorder(border, "Portfolio", TitledBorder.CENTER
                , TitledBorder.TOP, UIManager.getFont("h2.font"), Color.BLACK));


        portfolio_panel.add(Box.createRigidArea(new Dimension(0, 5)));
        HashMap<String, Double> list_stock = account.getList_stock();


        list_stock.forEach((stock_code, quantity) -> {
                    JPanel stock_panel = new JPanel();
                    stock_panel.setLayout(new BoxLayout(stock_panel, BoxLayout.Y_AXIS));
                    stock_panel.setBackground(Color.lightGray);
                    stock_panel.setForeground(Color.WHITE);
                    stock_panel.setBorder(border);

                    JLabel code = new JLabel(stock_code);
                    code.setFont(UIManager.getFont("h3.font"));
                    code.setForeground(Color.black);

                    JPanel title = new JPanel();
                    title.setBackground(new Color(255, 255, 255));

                    JLabel actual_price;


                    JLabel amount = new JLabel("Quantity:" + String.valueOf(quantity));
                    amount.setForeground(Color.BLACK);

                    try {
                        actual_price = new JLabel(String.valueOf(account.real_time_price(stock_code) * quantity));
                        actual_price.setFont(UIManager.getFont("h3.font"));
                        actual_price.setForeground(Color.BLACK);

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    title.add(code, BorderLayout.WEST);
                    title.add(actual_price, BorderLayout.EAST);

                    stock_panel.add(title);
                    stock_panel.setForeground(Color.white);
                    stock_panel.add(amount);


                    portfolio_panel.add(stock_panel);
                    portfolio_panel.setBackground(Color.lightGray);
                    portfolio_panel.setForeground(Color.white);
                    portfolio_panel.add(Box.createRigidArea(new Dimension(0, 5)));
                }

        );
        east_panel.add(largerRigidArea);
        east_panel.add(portfolio_panel);
        home_panel.add(east_panel, BorderLayout.EAST);

        // tabbedPane.setLayout(());

        this.getContentPane().add(tabbedPane);
    }

    public static void main(String[] args) {


        try {

            ConnectedFrames frames = new ConnectedFrames(new UserAccount("pascal", "edouard"));
            frames.setDefaultCloseOperation(EXIT_ON_CLOSE);
            frames.setMinimumSize(new Dimension(850, 500));
            frames.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }


}
