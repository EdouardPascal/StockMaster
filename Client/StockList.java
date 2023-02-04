package Client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

public class StockList extends HashMap<String, String> {
    String port = "3306";
    String directory = "localhost";
    String database_name = "stockmaster";
    String url = "jdbc:mysql://" + directory + ":" + port + "/" + database_name;
    String database_username = "root";
    String database_password = "stockmaster1";

    public StockList() {

        Connection connection;
        //add searchbar
        try {
            connection = DriverManager.getConnection(url, database_username, database_password);

            PreparedStatement statement;
            ResultSet resultSet;
            String query = "SELECT* FROM stock_name"; //take all the value from the table that has stock name information
            statement = (PreparedStatement) connection.prepareStatement(query);

            resultSet = statement.executeQuery();

            String code;
            String name;
            while (resultSet.next()) {
                code = resultSet.getString("stock_code");
                name = resultSet.getString("stock_name");

                this.put(code, name);


            }
        } catch (
                Exception exception) {
            throw new RuntimeException(exception);
        }


    }


}



