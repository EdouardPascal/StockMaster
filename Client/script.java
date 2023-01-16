package Client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class script {


    public static void main(String[] args) throws IOException, SQLException {
        FileReader fileReader = new FileReader("NASDAQ.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockmaster", "root", "stockmaster1");
            bufferedReader.readLine();
            PreparedStatement statement;
            ResultSet resultSet;
            String query;
            String line;
            String[] word = new String[5];
            while ((line = bufferedReader.readLine()) != null) {

                word = line.split(" ", 2);
                query = "INSERT INTO stock_name(stock_code,stock_name) VALUES(?,?)";
                statement = (PreparedStatement) connection.prepareStatement(query);
                statement.setString(1, word[0]);
                statement.setString(2, word[1]);
                statement.execute();

                // preparedStatement.setString(1, word[0]);
                //preparedStatement.setString(2, word[1]);
                //System.out.println(line);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
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
