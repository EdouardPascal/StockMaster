package Client;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.Serializable;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;


public class UserAccount implements Serializable {


    String port = "3306";
    String directory = "localhost";
    String database_name = "stockmaster";
    String url = "jdbc:mysql://" + directory + ":" + port + "/" + database_name;
    String database_username = "root";
    String database_password = "stockmaster1";


    final double initial = 0;
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

        this.list_stock = new HashMap<String, Double>();


    }


    //we will create accessor and  mutators
    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public double getMoney_available() {
        Connection connection = null;
        try {
            // db parameters
            // create a connection to the database
            connection = DriverManager.getConnection(url, database_username, database_password);

            System.out.println("Connection to SQLite has been established.");
            /////////look into the table userpass for matching username and password
            PreparedStatement preparedStatement = (PreparedStatement)
                    connection.prepareStatement("SELECT* FROM userpass where username=? and password=?");
            preparedStatement.setString(1, this.username);
            preparedStatement.setString(2, this.password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                this.money_available = resultSet.getDouble("money_available");
                System.out.println(this.money_available);
                return this.money_available;

            } else {
                System.out.println("Wrong Username & Password");
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
        Connection connection = null;
        try {
            // db parameters

            // create a connection to the database
            connection = DriverManager.getConnection(url, database_username, database_password);

            System.out.println("Connection to SQLite has been established.");
            /////////look into the table userpass for matching username and password
            PreparedStatement preparedStatement = (PreparedStatement)
                    connection.prepareStatement("SELECT* FROM stock_owner where username=?");
            preparedStatement.setString(1, this.username);
            ResultSet resultSet = preparedStatement.executeQuery();
            double money = 0.0;
            String stock_code;
            double quantity = 0.0;
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


    ///////////////////////////


    public void setMoney_available(double money_available) {
        this.money_available = money_available;
    }

    public void refresh_money_invested() throws Exception {

        try {
            double invested_money = 0;
            for (Map.Entry<String, Double> entry : list_stock.entrySet()) {

                String key = entry.getKey();
                Double value = entry.getValue();
                invested_money += real_time_price(key) * value;
            }
            this.money_invested = invested_money;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


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
                          double amount_money)//take the stock code and the amount of money invested and perform the buying
    {
        try {
            double quantity_bought = amount_money / real_time_price(stock_code); //calculate quantity brought at the time

            if (list_stock.containsKey(stock_code)) { //if we already have that stock we just update its value in the list
                double new_quantity = list_stock.get(stock_code) + quantity_bought;
                list_stock.replace(stock_code, new_quantity);

            } else {
                list_stock.put(stock_code, quantity_bought);
            }
            this.setMoney_available(getMoney_available() - amount_money);//update money invested and all


        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }
}

