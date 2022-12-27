package Server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MyRemote extends Remote {

    public String connect(String username, String password) throws RemoteException;

    public void saveAccount(UserAccount account) throws RemoteException;
}
