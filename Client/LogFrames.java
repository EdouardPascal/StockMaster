package Client;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class LogFrames extends JFrame {

    //variables for west side of frame
    JPanel titlePanel;

    //variables for the login sidde
    JPanel welcome;
    JLabel welcome_msg;
    JPanel logpanel;
    JPanel eastPanel;
    JTextField usernameField;
    JPasswordField passwordField;

    //variables for field to grab information
    JPanel usernamePanel = new JPanel();
    JPanel passwordPanel = new JPanel();
    JLabel usernameLabel = new JLabel("Username");
    JLabel passwordLabel = new JLabel("Password");


    JPanel westPanel = new JPanel();

    JButton create_account = new JButton("Create a new Account");
    JButton login = new JButton("Login");

    public LogFrames(String message) {
//West side of the frame
        //setting the west panel and adding image
        ImageIcon login_image = new ImageIcon("icon/stockmarketlarge.png");
        JLabel westLabel = new JLabel("StockMaster");
        westLabel.setFont(UIManager.getFont("h1.font"));

        westLabel.setVerticalTextPosition(SwingConstants.TOP);
        westLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        westLabel.setVerticalAlignment(SwingConstants.TOP);
        westLabel.setIcon(login_image);


        titlePanel = new JPanel();
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.add(westLabel);
        // titlePanel.setBorder(BorderFactory.createEmptyBorder(50, 20, 0, 0));

        //configure the west panel of west frame and adding components
        //westPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 100, 100));
        westPanel.setBackground(Color.WHITE);

        westPanel.add(titlePanel, BorderLayout.CENTER);


        ////East side
        ////////////////////////
        //////////////////////////
        //////////////////////////
        //////////////////////////
        //configure LoginFrame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage((new ImageIcon("icon/stock-market.png")).getImage());
        this.setTitle("StockMaster");

        //configure the top of login side
        welcome = new JPanel();
        welcome_msg = new JLabel(message);
        welcome_msg.setFont(UIManager.getFont("h00.font"));
        welcome_msg.setForeground(new Color(76, 255, 71));

//configure field to receive user input
        usernameField = new JTextField("USERNAME");
        usernameField.setOpaque(false);
        usernameField.setBorder(new EmptyBorder(0, 0, 10, 10));
        usernameField.setColumns(10);
        usernameField.setBorder(BorderFactory.createLineBorder(Color.black));

        passwordField = new JPasswordField("PASSWORD");
        passwordField.setColumns(10);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.black));

        //create a panel to put the username field and the password field
        JPanel userField = new JPanel();
        userField.setLayout(new BoxLayout(userField, BoxLayout.Y_AXIS));
        userField.add(usernameField);
        userField.add(Box.createRigidArea(new Dimension(0, 10)));
        userField.add(passwordField);


        //configure login and create account buttons

        login.setBackground(new Color(76, 255, 71));
        login.setForeground(Color.WHITE);
        login.setMargin(new Insets(30, 30, 30, 25));


        create_account.setBackground(new Color(76, 255, 71));
        create_account.setForeground(Color.WHITE);
        login.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 150));
        create_account.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 150));

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


/////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////

        //create a panel for login and create account buttons
        logpanel = new JPanel();
        logpanel.setLayout(new BoxLayout(logpanel, BoxLayout.X_AXIS));
        logpanel.add(login);
        logpanel.add(Box.createRigidArea(new Dimension(10, 0)));
        logpanel.add(create_account);

        //create panel that will hold everyother panel on the east side of the frame
        eastPanel = new JPanel();
        eastPanel.setLayout(new BorderLayout(0, 10));
        eastPanel.setBorder(new EmptyBorder(0, 0, 50, 150));
        eastPanel.add(welcome_msg, BorderLayout.NORTH);
        eastPanel.add(userField);
        eastPanel.add(logpanel, BorderLayout.SOUTH);


        this.getContentPane().add(BorderLayout.WEST, westPanel);
        this.getContentPane().add(BorderLayout.CENTER, eastPanel);
        //this.pack();
    }

}

