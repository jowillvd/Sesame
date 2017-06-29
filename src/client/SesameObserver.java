package client;

import java.rmi.Remote;
import java.rmi.RemoteException;

import server.SesameServerInterface;

public interface SesameObserver extends Remote {

	public void update(SesameServerInterface server) throws RemoteException;

	public boolean isEnabled() throws RemoteException;

	public void setEnabled(boolean enabled) throws RemoteException;

	public void updateMode() throws RemoteException;

}
