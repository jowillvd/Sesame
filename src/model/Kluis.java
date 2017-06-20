package model;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import client.ModelObserver;
import host.KluisInterface;
import view.KluisView;

public class Kluis implements KluisInterface{

	private Slot sloten[] = new Slot[9];
	private KluisView kluisView;
	private boolean open = false;

	public Kluis() {
		for (int i = 0; i < sloten.length; i++) {
			this.sloten[i] = new Slot();
		}
	}

	/**
	 * Draai de positie van het slot links
	 *
	 * @param slot positie nummer van het slot
	 */
	public void draaiPositieLinks(int slot) {
		int nPositie = this.sloten[slot].getPositie() + 1;
		this.sloten[slot].setPositie(nPositie);

		String icoon = this.sloten[slot].getSymbool().getIcoon();
		this.kluisView.update(icoon, slot);

	}

	/**
	 * Draai de positie van het slot rechts
	 *
	 * @param slot positie nummer van het slot
	 */
	public void draaiPositieRechts(int slot) {
		int nPositie = this.sloten[slot].getPositie() - 1;
		this.sloten[slot].setPositie(nPositie);

		String icoon = this.sloten[slot].getSymbool().getIcoon();
		this.kluisView.update(icoon, slot);
	}

	/**
	 * Return een slot die gevraagd wordt
	 * op basis van de positie van het slot
	 */
	public Slot selectSlot(int slotPositie) {
		return this.sloten[slotPositie];
	}

	/**
	 * Return alle sloten.
	 *
	 * @return sloten array
	 */
	public Slot[] getSloten() {
		return sloten;
	}

	/**
	 * Return een boolean om te kijken of de kluis open of dicht is.
	 * True is gelijk aan open, false is gelijk aan dicht.
	 *
	 * @return boolean open of dicht
	 */
	public boolean isOpen() {
		return open;
	}

	/**
	 *
	 */
	public void setOpen(boolean bool) {
		this.open = bool;
	}

	/**
	 * De nieuwste kluisView.
	 *
	 * @param kluisView
	 */
	public void setKluisView(KluisView kluisView) {
		this.kluisView = kluisView;
	}





	@Override
	public void addObserver(ModelObserver mo) throws RemoteException {
		// TODO Auto-generated method stub

	}

}
