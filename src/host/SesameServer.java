package host;

import java.rmi.server.UnicastRemoteObject;

public class SesameServer {

	public static void main(String[] args){
		new SesameServer().runServer();
	}

	public void runServer() {
		Kluis kluis = new Kluis(); // create calculator and treat as Counter
		Countable counterSkeleton = (Countable) UnicastRemoteObject.exportObject(kluis, 0);

		Countable counterSkeleton = 	(Countable) UnicastRemoteObject.exportObject(counter, 0); // cast to remote object
		System.out.println("Counter skeleton created");
		Registry registry = LocateRegistry.createRegistry(1099); // default port 1099 // run RMI registry on local host
		System.out.println("RMI Registry starter");
		registry.rebind("Counter", counterSkeleton); // bind calculator to RMI registry
        System.out.println("Calculator skeleton bound");
        System.out.println("Server running...");
	}

}
