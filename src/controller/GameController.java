package controller;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import host.Calculator;
import host.CalculatorImpl;

public class GameController {

	public void joinLobby() {

	}

	public void gameAfsluiten() {

	}

	public void hostLobby() {
		try {

			Lobby lobby = new Lobby(); // create calculator and treat as Calculator
			Calculator calcSkeleton = 	(Calculator) UnicastRemoteObject.exportObject(calcImpl, 0); // cast to remote object
			System.out.println("Calculator skeleton created");
			Registry registry = LocateRegistry.createRegistry(1099); // default port 1099 // run RMI registry on local host
			System.out.println("RMI Registry starter");
			registry.rebind("Calculator", calcSkeleton); // bind calculator to RMI registry
            System.out.println("Calculator skeleton bound");
            System.out.println("Server running...");

			// if you'd like to run rmiregistry from the commend line
			//	run it from the project's bin directory, so rmiregistry can find the necessary classes

		} catch (Exception e) {
			System.out.println("EXCEPTION: " + e);
		}
	}

}
