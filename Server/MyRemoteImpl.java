package Server;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote {
    AccountList accountList = new AccountList();


    //load the account list
    public MyRemoteImpl() throws RemoteException {
        super();

    }

    public String connect(String username, String password) throws RemoteException { //remote function that verify if users are there
        Pattern p = Pattern.compile("[^A-Za-z0-9]");  //checking to see if the characters are not special
        Matcher m_user = p.matcher(username);
        // boolean b = m.matches();
        boolean b = m_user.find();
        if (b == true) {
            return "The username contains invalid characters";
        } else if (!accountList.userpass.containsKey(username)) {
            return "The Username does not exist";
        } else if (!password.equals(accountList.userpass.get(username))) {
            return "Invalid password";
        } else {
            return "Successful Login";
        }
    }


    public void saveAccount(UserAccount account) throws RemoteException {
        String filename = account.getUsername() + ".ser";
        try {
            FileOutputStream fileStream = new FileOutputStream(filename);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileStream);
            outputStream.writeObject(account);
            outputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    //////////////////////////

    public static void main(String[] args) {


        try {
            Registry registry = LocateRegistry.createRegistry(1500);
            registry.rebind("Connection", new MyRemoteImpl());
        } catch (
                Exception ex) {
            ex.printStackTrace();
        }
    }


}
