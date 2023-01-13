package Client;


import Server.UserAccount;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class UserInterface {
    // AccountList accountList = null;

    Server.UserAccount userAccount = new UserAccount("pascal");
    LogFrames log_page;
    LogFrames create_page;

    //   public UserInterface(AccountList list) {
    //  accountList = list;
    //  accountList.loadUserPass(); //add password to list interval
    //  }


    //The User Interface will take care of allowing interactons with the user and allowing it to connect and create an account
    //this is essentially the welcome screen
    //it contains a function called go that launch an intial JFrame

    //There are also innerclasses consisting of actionListener in order to see when the user log: LoginListener and create an account

    //  JTextField usernameField;
    //  JPasswordField passwordField;
    JButton login = new JButton("Login");

    public void go() {
        log_page = new LogFrames("Login Page");
        log_page.setIconImage((new ImageIcon("icon/stock-market.png")).getImage());
        log_page.setTitle("StockMaster");
        login.addActionListener(new LoginListener());
        login.setBackground(new Color(76, 255, 71));
        login.setForeground(Color.WHITE);
        //login.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 150));


        login.setHorizontalAlignment(SwingConstants.LEFT);
        JButton create_account = new JButton("Create a new Account");
        create_account.setBackground(new Color(76, 255, 71));

        create_account.setForeground(Color.WHITE);
        create_account.addActionListener(new CreateAccountListener());
        //login.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 150));
        // create_account.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 150));

        //create_account.setPreferredSize((new Dimension(300, 50)));


        log_page.logpanel.add(login);
        log_page.logpanel.add(create_account);


        //log_page.setSize(300, 300);


        // log_page.getContentPane().

        //     add(BorderLayout.EAST, log_page.info);

        log_page.setBackground(Color.BLACK);
        log_page.getContentPane().requestFocusInWindow();

        /////////////////////////////////////////////////////////////////////////////////////////////
        ///login.requestFocusInWindow();


        log_page.pack();
        log_page.setVisible(true);


    }

    public class LoginListener implements ActionListener { //action listeners implemented for the action button
        public void actionPerformed(ActionEvent e) {
            String userInput = log_page.usernameField.getText();
            String passInput = String.valueOf(log_page.passwordField.getPassword());
            System.out.println("Tried to login");
            System.out.println("Username is " + userInput);
            System.out.println("Password is " + passInput);
            connect(userInput, passInput);
        }
    }


    public class CreateAccountListener implements ActionListener { //action listeners implemented for create account button
        public void actionPerformed(ActionEvent e) {

            System.out.println("Tried to Create Account");

        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            UIManager.put("TabbedPane.selectedBackground", Color.blue);
            UIManager.put("TabbedPane.showTabSeparators", true);
            UIManager.put("TabbedPane.tabSeparatorsFullHeight", true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Client.UserInterface user = new Client.UserInterface();
        user.go();

    }

    public void connect(String Username, String Password) {
//Connect to SQL database

        Connection connection = null;
        try {
            // db parameters
            String port = "3306";
            String directory = "localhost";
            String database_name = "stockmaster";
            String url = "jdbc:mysql://" + directory + ":" + port + "/" + database_name;
            String database_username = "root";
            String database_password = "stockmaster1";
            // create a connection to the database
            connection = DriverManager.getConnection(url, database_username, database_password);

            System.out.println("Connection to SQLite has been established.");
            /////////look into the table userpass for matching username and password
            PreparedStatement preparedStatement = (PreparedStatement)
                    connection.prepareStatement("SELECT Username, Password FROM userpass where Username=? and Password=?");
            preparedStatement.setString(1, Username);
            preparedStatement.setString(2, Password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                //UserHome ah = new UserHome(userName);
                //ah.setTitle("Welcome");
                //ah.setVisible(true);
                JOptionPane.showMessageDialog(login, "You have successfully logged in");
            } else {
                System.out.println("Wrong Username & Password");
                JOptionPane.showMessageDialog(login, "Wrong Username & Password");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }


}







