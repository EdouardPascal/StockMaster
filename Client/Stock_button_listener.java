package Client;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;

public class Stock_button_listener implements ActionListener {
    String stock;
    ConnectedFrames frames;

    public Stock_button_listener(String stock_code, ConnectedFrames connectedFrames) {
        this.stock = stock_code;
        this.frames = connectedFrames;
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
                frames.account.buy_stock(stock, (Double) jSpinner.getValue());

                frames.east_panel.removeAll();
                frames.east_panel.add(frames.buyingPanel);
                frames.east_panel.add(Box.createRigidArea(new Dimension(0, 5)));
                frames.east_panel.add(new balancePane(frames.account));
                frames.east_panel.add(Box.createRigidArea(new Dimension(0, 5)));
                frames.east_panel.add(new porfolioPane(frames.account, frames));

                frames.east_panel.repaint();
                frames.east_panel.revalidate();

            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

        }
    }

    public void actionPerformed(ActionEvent e) {
        frames.current_string = stock;
        frames.transaction.setText("Buy " + frames.current_string);
        frames.sell_choice.setBackground(Color.white);
        frames.sell_choice.setForeground(Color.black);
        frames.sell_choice.setFont(new Font("Arial", Font.PLAIN, 12));
        frames.buy_choice.setBackground(Color.green);
        frames.buy_choice.setForeground(Color.white);
        frames.buy_choice.setFont(new Font("Arial", Font.BOLD, 12));
        frames.numberModel.setMaximum(frames.account.getMoney_available());
        try {
            frames.numberChooser.commitEdit();
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
        for (ActionListener listener : frames.transaction.getActionListeners()) {
            frames.transaction.removeActionListener(listener);
        }
        Double value = (Double) frames.numberChooser.getValue();
        double value_double = value.doubleValue();
        frames.transaction.addActionListener(new Buy_Listener(frames.current_string, frames.numberChooser));
        frames.buyingPanel.repaint();


        try {
            frames.buying_choice.repaint();
            frames.buyingPanel.repaint();
            Border border = BorderFactory.createRaisedBevelBorder();
            frames.buyingPanel.setBorder(BorderFactory.createTitledBorder(border, "Buy/Sell " + frames.current_string, TitledBorder.CENTER,
                    TitledBorder.TOP, UIManager.getFont("h2.font"), Color.black));
            frames.east_panel.repaint();

            frames.west_panel.remove(2);
            frames.west_panel.add(new StockGraph(stock), 2);
            frames.west_panel.validate();
            frames.west_panel.repaint();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }


}
