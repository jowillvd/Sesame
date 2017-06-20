package controller;

import java.rmi.RemoteException;

import host.KluisInterface;

public class KluisController {

	private KluisInterface kluis;

	public KluisController(KluisInterface kluis) {
		this.kluis = kluis;

	}

	public void draaiSlotLinks(int slot) throws RemoteException {
		this.kluis.draaiPositieLinks(slot);
	}

	public void draaiSlotRechts(int slot) throws RemoteException {
		this.kluis.draaiPositieRechts(slot);
	}

}
