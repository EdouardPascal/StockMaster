package Client;

import java.io.Serializable;
import java.util.HashMap;


public class UserAccount implements Serializable {
    final double initial = 0;
    private String username;
    private String password;
    private double total_money;
    private double money_invested;
    private double money_available;
    private HashMap<String, Double> list_stock; // a list of stock that we bought
    //using hashmap as data structure and using the name of the stock has a key

    //constructor
    public UserAccount(String user) {
        this.money_available = initial;
        this.total_money = initial;
        this.money_invested = 0;
        this.list_stock = new HashMap<String, Double>();
        this.username = user;


    }

    //we will create accessor and  mutators
    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public double getMoney_available() {
        return money_available;
    }

    public double getMoney_invested() {
        return money_invested;
    }

    public double getTotal_money() {
        return total_money;
    }


    ///////////////////////////


    public void setMoney_available(double money_available) {
        this.money_available = money_available;
    }

    public void setMoney_invested(double money_invested) {
        this.money_invested = money_invested;
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


}

