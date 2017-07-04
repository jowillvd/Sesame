package client.controller;

import java.rmi.RemoteException;

import client.view.KluisView;
import client.view.ViewLoader;

import server.SesameServerInterface;

public class SchatkamerController extends MainController {

	MainController controller;

	public SchatkamerController(ViewLoader viewLoader, SesameServerInterface server, int spelerId, MainController mainController) {
		super(viewLoader);
		try {
			this.server = server;
			this.speler = server.getSpelers().get(spelerId);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		this.controller = mainController;
	}

	public void pakKaart(int positie) {
		if(this.speler.isAanDeBeurt()) {
			try {
				server.pakKaart(positie);
			} catch (RemoteException re) {
				re.printStackTrace();
			}
		} else {
			view.alert("Je bent niet aan de beurt");
		}
	}

	public void sluitKluis() {
		try {
			KluisController kluisController = new KluisController(this.view, this.server, speler.getId(), controller);
			this.viewCenter(new KluisView(kluisController, this.server));
			this.setGameMode(1);
			this.alert("Kluis wordt gesloten, de volgende speler is nu aan de beurt");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setGameMode(int i) {
		controller.setGameMode(i);
	}

	@Override
	public int getGameMode() {
		return controller.getGameMode();
	}

	public void controlleerStatus() {
		try {
			if(server.getGepakteSlangen() == 7) {
				this.endGame();
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
