package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class LogFrames extends JFrame {
    JPanel welcome;
    JLabel welcome_msg;
    JPanel logpanel;
    JPanel info;
    JTextField usernameField;
    JPasswordField passwordField;

    JPanel usernamePanel = new JPanel();
    JPanel passwordPanel = new JPanel();
    JLabel usernameLabel = new JLabel("Username");
    JLabel passwordLabel = new JLabel("Password");

    JPanel westPanel = new JPanel();


    public LogFrames(String message) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //setting the west panel and adding image
        ImageIcon login_image = new ImageIcon("icon/stockmarketlarge.png");
        //login_image.setImage(login_image.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT));

        JPanel titlePanel = new JPanel();

        westPanel.setMinimumSize(new Dimension(300, 250));
        // westPanel.add(new JLabel(login_image));
        westPanel.setBackground(Color.WHITE);
        JLabel westLabel = new JLabel("StockMaster");
        westLabel.setFont(UIManager.getFont("h1.font"));

        westLabel.setVerticalTextPosition(SwingConstants.TOP);
        westLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        westLabel.setVerticalAlignment(SwingConstants.TOP);
        westLabel.setIcon(login_image);
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.add(westLabel);

        //titlePanel.add(new JLabel(login_image));

        //JPanel spacer=new JPanel();
        //spacer.setBackground(B);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(100, 50, 250, 100));
        westPanel.add(titlePanel, BorderLayout.CENTER);
        /////////
        this.setSize(700, 600);
        welcome = new JPanel();
        welcome_msg = new JLabel(message);

        logpanel = new JPanel();
        logpanel.setLayout(new

                BoxLayout(logpanel, BoxLayout.Y_AXIS));

        info = new JPanel();
        info.setLayout(new

                BoxLayout(info, BoxLayout.Y_AXIS));
//////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////


        usernameField = new JTextField("USERNAME");
        // usernameField.setBounds(100, 20, 165, 25);
        usernamePanel.add(BorderLayout.WEST, usernameLabel);
        usernamePanel.add(BorderLayout.CENTER, usernameField);
        passwordPanel.add(BorderLayout.WEST, passwordLabel);

        usernameField.setBorder(BorderFactory.createLineBorder(Color.black));
        passwordField = new JPasswordField("PASSWORD");
        passwordField.setBorder(BorderFactory.createLineBorder(Color.black));

        passwordPanel.add(BorderLayout.CENTER, passwordField);


        usernameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (usernameField.getText().equals("USERNAME")) {
                    usernameField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (usernameField.getText().equals("")) {
                    usernameField.setText("USERNAME");
                }
            }
        });

        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (passwordField.getText().equals("PASSWORD")) {
                    passwordField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (passwordField.getText().equals("")) {
                    passwordField.setText("PASSWORD");
                }
            }
        });
//////////////////////////////////////////////////////////////////////////////////////////
        info.add(welcome_msg);
        info.add(usernamePanel);
        info.add(passwordPanel);
        info.add(logpanel);

        this.getContentPane().add(BorderLayout.WEST, westPanel);
    }

}

