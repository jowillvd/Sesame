package client.controller;

import java.rmi.RemoteException;

import client.view.SchatkamerView;
import client.view.ViewLoader;
import server.SesameServerInterface;

public class KluisController extends MainController{

	private MainController controller;

	public KluisController(ViewLoader viewLoader, SesameServerInterface server, int spelerId, MainController mainController) {
		super(viewLoader);
		try {
			this.server = server;
			this.speler = server.getSpelers().get(spelerId);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		this.controller = mainController;
	}

	public void openKluis() {
		try {
			SchatkamerController schatkamerController = new SchatkamerController(this.view, this.server, speler.getId(), controller);
			this.viewCenter(new SchatkamerView(schatkamerController));
			this.setGameMode(2);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void draaiLinks(int slot) {
		try {
			server.draaiSlotLinks(slot);
		} catch (RemoteException re) {
			re.printStackTrace();
		}
	}

	public void draaiRechts(int slot) {
		try {
			server.draaiSlotRechts(slot);
		} catch (RemoteException re) {
			re.printStackTrace();
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

}
