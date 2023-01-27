package Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Stock_button extends JButton {
    JPanel panel;
    String stock_code;

    public Stock_button(JPanel panel, String code) {
        this.panel = panel;
        this.setText(code);
        this.stock_code = code;
        this.addActionListener(new Stock_button_listener());
    }


    public class Stock_button_listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                panel.removeAll();
                panel.add(new StockGraph(stock_code));
                panel.validate();
                panel.repaint();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }
    }


}
