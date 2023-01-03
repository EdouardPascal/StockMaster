package Client;

import javax.swing.*;
import java.awt.*;

public class ConnectedFrames extends JFrame {

    JPanel top_page, main;
    JLabel stock_teacher;
    JTextField search;
    JButton home_button, buy_sell_button, transaction_buttton, logout_button;
    JMenuBar menuBar;

    JMenu account_menu;
    JMenuItem logout_item;

    public ConnectedFrames() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        stock_teacher = new JLabel("StockMaster");
        stock_teacher.setIcon(new ImageIcon("icon/stock-market.png"));
        JPanel search_panel = new JPanel();
        search = new JTextField(30);

        search_panel.add(search);

        JButton search_button = new JButton();
        search_button.setIcon(new ImageIcon("icon/search.png"));
        search_panel.add(search_button);
        search_panel.setOpaque(false);
        menuBar = new JMenuBar();


        top_page = new JPanel();
        top_page.add(stock_teacher);
        top_page.add(search_panel);

        home_button = new JButton("Home");
        top_page.add(home_button);
        menuBar = new JMenuBar();

        logout_item = new JMenuItem("Logout");
        account_menu = new JMenu("Account");
        JMenuItem user_change = new JMenuItem("Change Username");
        JMenuItem pass_change = new JMenuItem("Change PassWord");

        account_menu.add(user_change);
        account_menu.add(pass_change);
        account_menu.add(logout_item);


        menuBar.add(account_menu);

        top_page.add(menuBar);

        buy_sell_button = new JButton("Buy/Sell Stock");
        top_page.add(buy_sell_button);

        transaction_buttton = new JButton("Add/Take Funds");
        top_page.add(transaction_buttton);


        top_page.setBounds(0, 0, getWidth(), 10);
        top_page.setBackground(new Color(218, 234, 246));
        top_page.setMinimumSize(new Dimension(getWidth(), 50));
        top_page.setMaximumSize(new Dimension(getWidth(), 50));


        main = new JPanel();
        main.setBackground(Color.BLUE);
        // main.setBounds(0, top_page.getHeight(), getWidth(), 50);
        // main.setSize(850, 50);

        this.getContentPane().add(BorderLayout.NORTH, top_page);


        this.getContentPane().add(BorderLayout.CENTER, main);
    }

    public static void main(String[] args) {
        try {


            ConnectedFrames frames = new ConnectedFrames();
            frames.setDefaultCloseOperation(EXIT_ON_CLOSE);
            frames.setMinimumSize(new Dimension(850, 500));
            frames.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
