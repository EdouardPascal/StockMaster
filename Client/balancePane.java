package Client;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;


//this is a class to hold the balance panel
//the constructor take UserAccount as a parameter to gain access to necessary information
public class balancePane extends JPanel {
    public balancePane(UserAccount account) {

        Border border = BorderFactory.createRaisedBevelBorder();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.setBackground(Color.lightGray);

        this.setBorder(BorderFactory.createTitledBorder(border, "Balance", TitledBorder.CENTER,
                TitledBorder.TOP, UIManager.getFont("h2.font"), Color.black));

        // JLabel balanceTitle = new JLabel("Balance");
        //balanceTitle.setFont(UIManager.getFont("h2.font"));
        // balanceTitle.setForeground(Color.black);
        //  balanceTitle.setBackground(new Color(38, 36, 34));

        //this.add(balanceTitle);
        Component largerRigidArea = Box.createRigidArea(new Dimension(0, 10));

        Component rigidArea = Box.createRigidArea(new Dimension(0, 10));
        rigidArea.setBackground(Color.white);

        //this.add(rigidArea);

        JPanel totalMoneyPanel = new JPanel();
        totalMoneyPanel.setBackground(Color.white);
        totalMoneyPanel.setBorder(border);

        JLabel totalMoneyLabel = new JLabel("Total Money: ");
        totalMoneyLabel.setForeground(Color.darkGray);
        totalMoneyLabel.setFont(UIManager.getFont("h3.font"));

        JLabel totalAmountMoney;
        try {
            totalAmountMoney = new JLabel("$" + String.valueOf(account.getTotal_money()));
            totalAmountMoney.setFont(UIManager.getFont("h3.font"));
            totalAmountMoney.setForeground(Color.black);

            totalMoneyPanel.add(totalMoneyLabel);
            totalMoneyPanel.add(totalAmountMoney, BorderLayout.WEST);


            JPanel availableMoneyPanel = new JPanel();
            availableMoneyPanel.setBorder(border);
            availableMoneyPanel.setBackground(Color.white);

            JLabel availableMoneyLabel = new JLabel("Available Funds: ");
            availableMoneyLabel.setFont(UIManager.getFont("h3.font"));
            availableMoneyLabel.setForeground(Color.darkGray);

            JLabel availableAmountMoney = new JLabel("$" + String.valueOf(account.getMoney_available()));
            availableAmountMoney.setFont(UIManager.getFont("h3.font"));
            availableAmountMoney.setForeground(Color.black);

            availableMoneyPanel.add(availableMoneyLabel, BorderLayout.EAST);
            availableMoneyPanel.add(availableAmountMoney, BorderLayout.WEST);
            //availableMoneyPanel.setBorder(new EmptyBorder(0, 0, 15, 5));

            JPanel investedMoneyPanel = new JPanel();
            investedMoneyPanel.setBackground(Color.white);

            JLabel investedMoneyLabel = new JLabel("Total Invested: ");

            investedMoneyLabel.setFont(UIManager.getFont("h3.font"));
            investedMoneyLabel.setForeground(Color.darkGray);

            JLabel investedAmountMoney = new JLabel("$" + String.valueOf(account.getMoney_invested()));
            investedAmountMoney.setFont(UIManager.getFont("h3.font"));
            investedAmountMoney.setForeground(Color.black);

            investedMoneyPanel.add(investedMoneyLabel, BorderLayout.EAST);
            investedMoneyPanel.add(investedAmountMoney, BorderLayout.WEST);
            investedMoneyPanel.setBorder(border);
            // investedMoneyPanel.setBorder(new EmptyBorder(0, 0, 15, 5));


            this.add(totalMoneyPanel);

            this.add(rigidArea);

            this.add(availableMoneyPanel);
            this.add(rigidArea);

            this.add(investedMoneyPanel);
            this.add(rigidArea);
            //  this.setBorder(new EmptyBorder(0, 0, 50, 70));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());

        }


    }


}
