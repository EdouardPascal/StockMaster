package Client;


import Server.MyRemote;
import Server.UserAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


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

    public void go() {
        log_page = new LogFrames("StockTeacher");
        JButton login = new JButton("Login");
        login.addActionListener(new LoginListener());
        // login.setSize(100, 100);
        JButton create_account = new JButton("Create a new Account");
        create_account.addActionListener(new CreateAccountListener());
        //create_account.setPreferredSize((new Dimension(300, 50)));

        log_page.welcome.add(log_page.welcome_msg);
        log_page.logpanel.add(login);
        log_page.logpanel.add(create_account);


        log_page.setSize(300, 300);
        log_page.getContentPane().

                add(BorderLayout.SOUTH, log_page.logpanel);
        log_page.getContentPane().

                add(BorderLayout.NORTH, log_page.welcome);
        log_page.getContentPane().

                add(BorderLayout.CENTER, log_page.info);

        log_page.setBackground(Color.BLACK);
        log_page.getContentPane().requestFocusInWindow();

        /////////////////////////////////////////////////////////////////////////////////////////////

        log_page.setVisible(true);

    }

    public class LoginListener implements ActionListener { //action listeners implemented for the action button
        public void actionPerformed(ActionEvent e) {
            String userInput = log_page.usernameField.getText();
            String passInput = String.valueOf(log_page.passwordField.getPassword());
            System.out.println("Tried to login");
            System.out.println("Username is " + userInput);
            System.out.println("Password is " + passInput);
            try {
                Server.MyRemote service = (MyRemote) Naming.lookup("rmi://localhost:1500/Connection");
                String message = service.connect(userInput, passInput);
                service.saveAccount(userAccount);
                System.out.println(message);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }


    public class CreateAccountListener implements ActionListener { //action listeners implemented for create account button
        public void actionPerformed(ActionEvent e) {

            System.out.println("Tried to Create Account");

        }
    }

    public static void main(String[] args) {
        Client.UserInterface user = new Client.UserInterface();
        user.go();
    }

    public void connect(String Username, String Password) {

        Connection conn = null;
        try {
            // db parameters
            String port = ;
            String directory = "localhost";
            String url = "jdbc:sqlite:C:/sqlite/JTP.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}

}





