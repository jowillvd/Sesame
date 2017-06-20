package client;

import java.rmi.Remote;
import java.rmi.RemoteException;

import host.KluisInterface;

public interface ModelObserver extends Remote {

	public void modelVeranderd(KluisInterface t) throws RemoteException;

	public boolean isEnabled() throws RemoteException;

	public void setEnabled(boolean enabled) throws RemoteException;

}
