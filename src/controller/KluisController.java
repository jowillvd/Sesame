package controller;

import model.Kluis;
import model.Slot;

public class KluisController {

	private Kluis kluis;

	public KluisController(Kluis kluis) {
		this.kluis = kluis;

	}

	public void draaiSlotLinks(int slot) {
		this.kluis.draaiPositieLinks(slot);
	}

	public void draaiSlotRechts(int slot) {
		this.kluis.draaiPositieRechts(slot);
	}

}
