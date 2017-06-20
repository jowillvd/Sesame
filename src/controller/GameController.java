package controller;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import client.SesameClient;
import host.SesameServer;


public class GameController {

	public void joinLobby(View view) {
		//new SesameClient();
		startView.update(lobbyView);
	}

	public void gameAfsluiten() {

	}

	public void hostLobby() {
		new SesameServer().runServer();
	}

}
