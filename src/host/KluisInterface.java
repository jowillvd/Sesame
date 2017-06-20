package host;

import java.rmi.Remote;
import java.rmi.RemoteException;

import client.ModelObserver;

public interface KluisInterface extends Remote {

	void draaiPositieLinks(int slot) throws RemoteException;

	void draaiPositieRechts(int slot) throws RemoteException;

	public void addObserver(ModelObserver mo) throws RemoteException;

}
