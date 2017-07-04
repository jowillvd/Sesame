package client.controller;

import java.rmi.RemoteException;

import client.view.DummyView;
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
		if(i == 3) this.viewCenter(new DummyView());
		controller.setGameMode(i);
	}

	@Override
	public int getGameMode() {
		return controller.getGameMode();
	}

	public void steelSchat(int positie, int vanSpeler) {
		try {
			this.setGameMode(1);
			this.server.steelSchat(positie, vanSpeler, this.speler.getId());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
