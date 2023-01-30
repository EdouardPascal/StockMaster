package Client;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.swing.*;
import java.io.Serializable;
import java.sql.*;
import java.util.HashMap;


public class UserAccount implements Serializable {


    String port = "3306";
    String directory = "localhost";
    String database_name = "stockmaster";
    String url = "jdbc:mysql://" + directory + ":" + port + "/" + database_name;
    String database_username = "root";
    String database_password = "stockmaster1";


    private String username;
    private String password;
    private double total_money;
    private double money_invested;
    private double money_available;
    private HashMap<String, Double> list_stock; // a list of stock that we bought
    //using hashmap as data structure and using the name of the stock has a key

    //constructor
    public UserAccount(String username, String password) {
        this.username = username;
        this.password = password;

        this.money_available = getMoney_available();

        try {

            this.money_invested = getMoney_invested();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        this.total_money = this.money_available + this.money_invested;

        this.list_stock = new HashMap<>();
        this.list_stock = getList_stock();


    }
//reapeated functions that are useful

    //mutators
    public void setMoney_available(double amount) {
        //connect to sql database and update the money_available column from userpass table
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, database_username, database_password);
            String query = "UPDATE userpass SET money_available=? WHERE username=?";
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            preparedStatement.setDouble(1, amount);
            preparedStatement.setString(2, this.username);

            preparedStatement.executeQuery();


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
    }


    //we will create accessor and  mutators
    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public double getMoney_available() { //connect to SQL database and return the total amount of money available
        Connection connection = null;
        try {
            // db parameters
            // create a connection to the database
            connection = DriverManager.getConnection(url, database_username, database_password);


            /////////look into the table userpass for matching username and password
            PreparedStatement preparedStatement = (PreparedStatement)
                    connection.prepareStatement("SELECT* FROM userpass where username=? and password=?");
            preparedStatement.setString(1, this.username);
            preparedStatement.setString(2, this.password);
            //configure the statement for correct Account lookup

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                this.money_available = resultSet.getDouble("money_available");

                return this.money_available;

            } else {
                JOptionPane.showMessageDialog(null, "Wrong Username or Password", "Incorrect", JOptionPane.WARNING_MESSAGE);
                //  JOptionPane.showMessageDialog(new JButton(), "Wrong Username & Password");
                return 0.0;
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


        return money_available;
    }

    public double getMoney_invested() throws Exception {
        //look the SQL database to find the stock and the amount owned and multiply it by the real time price to find
        //amount invested
        Connection connection = null;
        try {
            // db parameters

            // create a connection to the database
            connection = DriverManager.getConnection(url, database_username, database_password);


            /////////look into the table userpass for matching username and password
            PreparedStatement preparedStatement = (PreparedStatement)
                    connection.prepareStatement("SELECT* FROM stock_owner where username=?");
            preparedStatement.setString(1, this.username);
            ResultSet resultSet = preparedStatement.executeQuery();
            double money = 0.0;
            String stock_code;
            double quantity;
            while (resultSet.next()) {
                stock_code = resultSet.getString("stock_code");
                quantity = resultSet.getDouble("quantity");
                money += real_time_price(stock_code) * quantity;
                System.out.println("Added " + quantity + " of stock " + stock_code);

                System.out.println("Total invested: " + money);

            }
            this.money_available = money;
            return money_available;


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
        return money_invested;
    }


    public double getTotal_money() throws Exception {
        this.total_money = getMoney_available() + getMoney_invested();

        return total_money;
    }

    public HashMap<String, Double> getList_stock() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, database_username, database_password);
            System.out.println("Connection to SQLite has been established.");
            PreparedStatement statement;
            ResultSet resultSet;
            String query = "SELECT* FROM stock_owner where username=?";
            statement = (PreparedStatement) connection.prepareStatement(query);
            statement.setString(1, this.username);
            resultSet = statement.executeQuery();

            String code;
            double quantity;
            while (resultSet.next()) {
                code = resultSet.getString("stock_code");
                quantity = resultSet.getDouble("quantity");
                list_stock.put(code, quantity);


            }

            return list_stock;

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

        return list_stock;
    }

    ///////////////////////////


    public void setPassword(String password) {
        this.password = password;
    }

    public void setTotal_money(double total_money) {
        this.total_money = total_money;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /////////
    public double real_time_price(String stock_code) throws
            Exception //get the real time price of stock using its code
    {
        String url = "https://www.google.com/finance/quote/" + stock_code + ":NASDAQ?hl=en";//build url using stock code
        String texts;
        Document page = (Jsoup.connect(url)).get();


        texts = page.html();


        int start = texts.indexOf("<div class=\"YMlKec fxKbKc\">", 0);//used to find price by looking a specific point of html parsed
        int from = texts.indexOf(">", start);
        int stop = texts.indexOf("</div", from);

        String price = texts.substring(from + 1, stop).replace("$", "").replace(",", "");
        return (Double.parseDouble(price)); //transform the string price into double and return it


    }


    public void buy_stock(String stock_code,
                          double amount_money)
    //take the stock code and the amount of money invested and perform the buying
    //by connecting to database and modifiying quantity column from stock_owner table and also list_stock object
    {
        if (amount_money > getMoney_available()) {//if not enough money
            JOptionPane.showMessageDialog(null, "Not enough money to perform this acquisition", "Incorrect", JOptionPane.WARNING_MESSAGE);

            return;
        }
        Connection connection;
        try {
            double quantity_bought = amount_money / real_time_price(stock_code); //calculate quantity brought at the time

            connection = DriverManager.getConnection(url, database_username, database_password);


            PreparedStatement preparedStatement = (PreparedStatement)
                    connection.prepareStatement("SELECT quantity FROM stock_owner where username=? AND stock_code=?");

            preparedStatement.setString(1, this.username);
            preparedStatement.setString(2, stock_code);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {//if user already had that stock we just update the quantity
                double amount_owned = resultSet.getDouble("quantity");
                double new_quantity = quantity_bought + amount_owned;

                PreparedStatement another_preparedStatement = (PreparedStatement)
                        connection.prepareStatement("UPDATE stock_owner SET quantity=? WHERE stock_code=? and username=?");
                another_preparedStatement.setDouble(1, new_quantity);
                another_preparedStatement.setString(2, stock_code);
                another_preparedStatement.setString(3, this.username);

                another_preparedStatement.executeUpdate();
            } else {

                PreparedStatement another_preparedStatement = (PreparedStatement)
                        connection.prepareStatement("INSERT INTO stock_owner(stock_code,username,quantity) VALUES(?,?,?)");
                another_preparedStatement.setString(1, stock_code);
                another_preparedStatement.setString(2, this.username);
                another_preparedStatement.setDouble(3, quantity_bought);

                another_preparedStatement.executeUpdate();

            }

            JOptionPane.showMessageDialog(null, "You succesfully bought "
                    + quantity_bought + " shares of " + stock_code + "for " + amount_money, "Congrats", JOptionPane.WARNING_MESSAGE);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //after performing the purchase set the corect amount of money by substracting the amount
        //previously available with the amount purchased

        setMoney_available(getMoney_available() - amount_money);


    }

    public void sell_stock(String stock_code, double amount_money) throws Exception {


        //first acknowledge bad cases
        if (!list_stock.containsKey(stock_code)) {

            JOptionPane.showMessageDialog(null, "You do not own that stock", "Incorrect", JOptionPane.WARNING_MESSAGE);
            return;
        }
        double price = real_time_price(stock_code);
        double quantity = amount_money / price;

        if (quantity > list_stock.get(stock_code)) {
            JOptionPane.showMessageDialog(null, "You do not own that many stock", "Incorrect", JOptionPane.WARNING_MESSAGE);


            return;

        }

        Double new_quantity = list_stock.get(stock_code) - quantity;
        list_stock.put(stock_code, new_quantity);
        setStockQuantity(stock_code, new_quantity);

        JOptionPane.showMessageDialog(null, "You successfully sold "
                + quantity + " shares of " + stock_code, "Congrats", JOptionPane.WARNING_MESSAGE);

    }

    public void setStockQuantity(String stock_code, Double new_quantity) {
        //using the stock_code and the new quantity, connect to database and
        //modify the value of the quantity owned by user for that specific stock
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, database_username, database_password);

            String query = "UPDATE stock_owner SET quantity=? where stock_code=? and username=? ";
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);

            preparedStatement.setDouble(1, new_quantity);
            preparedStatement.setString(2, stock_code);
            preparedStatement.setString(3, this.username);

            preparedStatement.executeUpdate();


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }

            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
    }


}

