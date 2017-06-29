package client.controller;

import java.rmi.RemoteException;

import client.view.ViewLoader;
import server.SesameServerInterface;
import server.model.Speler;

public class SchatkamerController extends MainController {

	public SchatkamerController(ViewLoader viewLoader, SesameServerInterface server, Speler speler) {
		super(viewLoader);
		this.server = server;
		this.speler = speler;
	}

	public void pakKaart(int positie) {
		try {
			server.pakKaart(positie);
		} catch (RemoteException re) {
			re.printStackTrace();
		}
	}

}
