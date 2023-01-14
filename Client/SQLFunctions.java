package Client;

import javax.swing.*;
import java.sql.*;

public interface SQLFunctions {

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
