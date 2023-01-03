package Client;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
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

    public ConnectedFrames() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        UIManager.setLookAndFeel(new FlatLightLaf());
        UIManager.put("TabbedPane.selectedBackground", Color.blue);
        UIManager.put("TabbedPane.showTabSeparators", true);
        UIManager.put("TabbedPane.tabSeparatorsFullHeight", true);
        tabbedPane = new JTabbedPane(JTabbedPane.LEFT);

        tabbedPane.setFocusable(false);
        tabbedPane.setForeground(Color.WHITE);
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

        tabbedPane.add("Home", home_panel);

        tabbedPane.add("Add/Retract Funds", deposit_panel);
        tabbedPane.add("Buy/Sell Stock", transaction_panel);
        tabbedPane.add("Account", account_panel);

        // tabbedPane.setLayout(());

        this.getContentPane().add(tabbedPane);
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
