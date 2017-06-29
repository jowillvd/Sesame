package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import client.controller.MainController;

import server.SesameServerInterface;

public class SesameClient {

	public SesameClient(MainController controller, String hostadres) throws RemoteException, NotBoundException {
		System.out.println("Verbinding maken met de server...");
		// Toegang krijgen tot de RMI registry op de remote server
		Registry registry = LocateRegistry.getRegistry(hostadres); // IP adres wordt door de client ingevoerd. Standaard port  1099
		System.out.println(" - SesameServer stub van de registry krijgen");
		SesameServerInterface server = (SesameServerInterface) registry.lookup("SesameServer"); // Kluis object van registry

		controller.setServer(server);
	}

	public Object getClient() {
		// TODO Auto-generated method stub
		return null;
	}

}
