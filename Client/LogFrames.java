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

    public LogFrames(String message) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        usernameField.setBounds(100, 20, 165, 25);


        usernameField.setBorder(BorderFactory.createLineBorder(Color.black));
        passwordField = new JPasswordField("PASSWORD");
        passwordField.setBorder(BorderFactory.createLineBorder(Color.black));
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
        info.add(usernameField);
        info.add(passwordField);

    }
}

