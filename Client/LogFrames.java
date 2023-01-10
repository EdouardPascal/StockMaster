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
        ImageIcon login_image = new ImageIcon("icon/welcome.jpg");
        //login_image.setImage(login_image.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH));
        westPanel.setSize(100, 250);
        // westPanel.add(new JLabel(login_image));
        westPanel.setBackground(Color.CYAN);
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

