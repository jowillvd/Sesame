package client.controller;

import java.rmi.RemoteException;

import client.view.SchatkamerView;
import client.view.ViewLoader;
import server.SesameServerInterface;

public class KluisController extends MainController{

	public KluisController(ViewLoader viewLoader, SesameServerInterface server, int spelerId) {
		super(viewLoader);
		try {
			this.server = server;
			this.speler = server.getSpelers().get(spelerId);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void openKluis() {
		try {
			SchatkamerController schatkamerController = new SchatkamerController(this.view, this.server, this.speler.getId());
			this.viewCenter(new SchatkamerView(schatkamerController));
			this.gameMode = 2;
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

}
