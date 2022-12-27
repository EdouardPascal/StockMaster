package Server;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class AccountList {
    HashMap<String, String> userpass = new HashMap<>(); //a hash map putting together username and password
    // HashMap<String, Stock> user;

    //read files and to create the map of username to users
    File passwords = new File("Server/userlist.txt");

    public AccountList() {
/*
Function that take 0 arguments, return nothing, but moify the hashmap that contains username and passwords by reading
the userlist.txt and calling the function addUserPass to add them to the hashmap

 */
        {

            String line = null;
            try {
                FileReader fileReader = new FileReader(passwords);
                BufferedReader reader = new BufferedReader(fileReader);
                while ((line = reader.readLine()) != null) {
                    addUserPass(line);
                }
            } catch (
                    Exception ex) {
                ex.printStackTrace();
            }
        }
    }


    private void addUserPass(String line) {
        String[] combo = line.split("/");
        userpass.put(combo[0], combo[1]);
        System.out.println("added a user");
    }


    private void saveUserPass() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(passwords));
            for (Map.Entry<String, String> entry : userpass.entrySet()) {
                writer.write(entry.getKey() + "/" + entry.getValue());
                writer.write("\n");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}



