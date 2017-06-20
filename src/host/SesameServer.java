package host;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Enumeration;

import model.Kluis;

public class SesameServer {

	public void runServer() {
		try {
			Kluis kluis = new Kluis();
			KluisInterface kluisInterface = (KluisInterface) UnicastRemoteObject.exportObject(kluis, 0); // cast to remote object
			System.out.println(" - Kluis skeleton aangemaakt");

			// default port 1099 // run RMI registry on local host
			Registry registry = LocateRegistry.createRegistry(1099);
			System.out.println(" - RMI Registry starter");

			// Rebind alle objecten met RMI registry
			registry.rebind("Kluis", kluisInterface);
	        System.out.println(" - Kluis skeleton gekoppelt aan Kluis");

	        System.out.println(" ✓ Server is gestart. Gehost op:");
	        System.out.println(InetAddress.getLocalHost().getHostAddress());
		} catch (Exception e) {
			System.out.println("EXCEPTION: " + e);
		}
	}

}
