package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import controller.KluisController;
import host.KluisInterface;
import view.KluisView;

public class SesameClient {

	public SesameClient() {
		try {
			//for (int i = 0; i < 5; i++) {
				System.out.println("Verbinding maken met de server.");
				// Toegang krijgen tot de RMI registry op de remote server
				Registry registry = LocateRegistry.getRegistry("145.101.81.141"); // IP adres wordt door de client ingevoerd. Standaard port  1099
				System.out.println(" - Kluis stub van de registry krijgen");
		        KluisInterface kluis = (KluisInterface) registry.lookup("Kluis"); // Kluis object van registry

		        System.out.println(" - Performing arithmetics");

		        KluisController kluisController = new KluisController(kluis);
		        KluisView kluisView = new KluisView(kluisController, kluis);
				System.out.println(" ✓ Verbonden met de server.");
			//}
		} catch (Exception e) {
			System.out.println("EXCEPTION: " + e);
		}
	}
}
