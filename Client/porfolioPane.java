package Client;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.HashMap;

public class porfolioPane extends JPanel {
    JPanel inside;
    JScrollPane jScrollPane;

    public porfolioPane(UserAccount account, ConnectedFrames frames) {
        inside = new JPanel();
        inside.setLayout(new BoxLayout(inside, BoxLayout.Y_AXIS));

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.lightGray);
        Border border = BorderFactory.createRaisedBevelBorder();
        this.setBorder(BorderFactory.createTitledBorder(border, "Portfolio", TitledBorder.CENTER
                , TitledBorder.TOP, UIManager.getFont("h2.font"), Color.BLACK));


        this.add(Box.createRigidArea(new Dimension(0, 5)));
        HashMap<String, Double> list_stock = account.getList_stock();


        list_stock.forEach((stock_code, quantity) -> {
            JPanel stock_panel = new JPanel();
            stock_panel.setLayout(new BoxLayout(stock_panel, BoxLayout.Y_AXIS));
            stock_panel.setBackground(Color.lightGray);
            stock_panel.setForeground(Color.WHITE);
            stock_panel.setBorder(border);

            JButton code = new JButton(stock_code);
            code.addActionListener(new Stock_button_listener(stock_code, frames));


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


            inside.add(stock_panel);
            inside.setBackground(Color.lightGray);
            inside.setForeground(Color.white);
            inside.add(Box.createRigidArea(new Dimension(0, 5)));

        });

        jScrollPane = new JScrollPane(inside, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.add(jScrollPane);
    }

}
