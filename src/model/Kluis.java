package model;

import java.util.ArrayList;
import java.util.List;
import view.KluisView;

public class Kluis {

	private Slot sloten[] = new Slot[9];
	private KluisView kluisView;
	private boolean open = false;

	public Kluis() {
		for (int i = 0; i < sloten.length; i++) {
			this.sloten[i] = new Slot();
		}
	}

	/*
	 * Return de slot die actief is
	 */
	public Slot getCurrentSlot() {
		Slot slot = null;

		return slot;
	}

	/*
	 * Return een slot die gevraagd wordt
	 * op basis van de positie van het slot
	 */
	public Slot selectSlot(int slotPositie) {
		Slot slot = null;

		return slot;
	}

	/*
	 * Verander de positie van het slot dat actief is
	 */
	public void veranderSlotPositie(Slot slot) {

		this.kluisView.update(icoon);
	}

	public boolean isOpen() {
		return open;
	}


	public Slot[] getSloten() {
		return sloten;
	}

	public void setKluisView(KluisView kluisView) {
		this.kluisView = kluisView;
	}

}
