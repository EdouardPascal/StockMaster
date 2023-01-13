package Client;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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

        westPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 100, 100));
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
        titlePanel.setBorder(BorderFactory.createEmptyBorder(50, 20, 0, 0));
        westPanel.add(titlePanel, BorderLayout.CENTER);
        /////////
        ;
        welcome = new JPanel();
        welcome_msg = new JLabel(message);
        welcome_msg.setFont(UIManager.getFont("h00.font"));
        welcome_msg.setForeground(new Color(76, 255, 71));
        //welcome.setBounds(100, 100, 100, 0);

        logpanel = new JPanel();
        logpanel.setLayout(new

                BoxLayout(logpanel, BoxLayout.X_AXIS));
        //logpanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 100, 100));

        info = new JPanel();
        info.setLayout(new BorderLayout());

        //     BoxLayout(info, BoxLayout.Y_AXIS));
        //////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////


        usernameField = new JTextField("USERNAME");
        usernameField.setOpaque(false);
        usernameField.setBorder(new EmptyBorder(0, 0, 15, 0));

        usernameField.setColumns(10);
        //usernameField.setBounds(300, 0, 165, 25);
/*

        usernamePanel.add(BorderLayout.WEST, usernameLabel);
        usernamePanel.add(BorderLayout.CENTER, usernameField);
        passwordPanel.add(BorderLayout.WEST, passwordLabel);

*/
        usernameField.setBorder(BorderFactory.createLineBorder(Color.black));

        passwordField = new JPasswordField("PASSWORD");
        passwordField.setColumns(10);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.black));

        //passwordPanel.add(BorderLayout.CENTER, passwordField);


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


        //logpanel.setOpaque(false);
        //////////////////////////////////////////////////////////////////////////////////////////
        info.add(welcome_msg, BorderLayout.NORTH);

        JPanel userField = new JPanel();
        userField.setLayout(new BoxLayout(userField, BoxLayout.Y_AXIS));

        userField.add(usernameField);
        userField.add(passwordField);

        info.add(userField);
        info.add(logpanel, BorderLayout.SOUTH);
        // info.setBorder(BorderFactory.createEmptyBorder(westPanel.getWidth(), westPanel.getWidth(), 850, 300));


        this.getContentPane().add(BorderLayout.WEST, westPanel);
        this.getContentPane().add(BorderLayout.CENTER, info);
        this.pack();
    }

}

