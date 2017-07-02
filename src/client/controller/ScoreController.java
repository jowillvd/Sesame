package client.controller;

import java.rmi.RemoteException;

import client.view.ViewLoader;

import server.SesameServerInterface;

public class ScoreController extends MainController {

	MainController controller;

	public ScoreController(ViewLoader viewLoader, SesameServerInterface server, int spelerId, MainController mainController) {
		super(viewLoader);
		try {
			this.server = server;
			this.speler = server.getSpelers().get(spelerId);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		this.controller = mainController;
	}

	@Override
	public void setGameMode(int i) {
		controller.setGameMode(i);
	}

	@Override
	public int getGameMode() {
		return controller.getGameMode();
	}

}
