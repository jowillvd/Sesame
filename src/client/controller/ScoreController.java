package client.controller;

import java.rmi.RemoteException;

import client.view.ViewLoader;

import server.SesameServerInterface;

public class ScoreController extends MainController {

	public ScoreController(ViewLoader viewLoader, SesameServerInterface server, int spelerId) {
		super(viewLoader);
		try {
			this.server = server;
			this.speler = server.getSpelers().get(spelerId);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void enableSteelmode() {
		this.gameMode = 3;
	}

}
