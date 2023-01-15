package Client;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

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

        home_panel = new ConnectedPanel(); //create home tab
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
        JPanel StockGraphPanel = new JPanel();

        JPanel balancePanel = new JPanel();
        balancePanel.setLayout(new BoxLayout(balancePanel, BoxLayout.Y_AXIS));
        JLabel balanceTitle = new JLabel("Balance");
        balanceTitle.setFont(UIManager.getFont("h2.font"));
        balancePanel.add(balanceTitle);
        balancePanel.add(Box.createRigidArea(new Dimension(0, 5)));

        JPanel totalMoneyPanel = new JPanel();
        JLabel totalMoneyLabel = new JLabel("Total Money");
        totalMoneyPanel.add(totalMoneyLabel);
        totalMoneyLabel.setFont(UIManager.getFont("h3.font"));

        JPanel availableMoneyPanel = new JPanel();
        JLabel availableMoneyLabel = new JLabel("Available Funds");
        availableMoneyLabel.setFont(UIManager.getFont("h3.font"));

        availableMoneyPanel.add(availableMoneyLabel, BorderLayout.EAST);
        //availableMoneyPanel.setBorder(new EmptyBorder(0, 0, 15, 5));

        JPanel investedMoneyPanel = new JPanel();
        JLabel investedMoneyLabel = new JLabel("Total Invested");
        investedMoneyLabel.setFont(UIManager.getFont("h3.font"));
        investedMoneyPanel.add(investedMoneyLabel, BorderLayout.EAST);
        // investedMoneyPanel.setBorder(new EmptyBorder(0, 0, 15, 5));

        balancePanel.add(totalMoneyPanel);
        balancePanel.add(Box.createRigidArea(new Dimension(0, 5)));

        balancePanel.add(availableMoneyPanel);
        balancePanel.add(Box.createRigidArea(new Dimension(0, 5)));

        balancePanel.add(investedMoneyPanel);
        balancePanel.setBorder(new EmptyBorder(0, 0, 50, 70));

        home_panel.add(balancePanel, BorderLayout.EAST);

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
