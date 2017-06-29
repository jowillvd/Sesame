package client.controller;

import java.rmi.RemoteException;

import client.view.SchatkamerView;
import client.view.ViewLoader;
import server.SesameServerInterface;
import server.model.Speler;

public class KluisController extends MainController{

	public KluisController(ViewLoader viewLoader, SesameServerInterface server, Speler speler) {
		super(viewLoader);
		this.server = server;
		this.speler = speler;
	}

	public void openKluis() {
		try {
			SchatkamerController schatkamerController = new SchatkamerController(this.view, this.server, this.speler);
			this.viewCenter(new SchatkamerView(schatkamerController));
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
