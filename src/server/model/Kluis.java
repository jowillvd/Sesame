package server.model;

import java.io.Serializable;

public class Kluis implements Serializable {

	private static final long serialVersionUID = 1L;
	private Slot[] sloten = new Slot[9];
	private int actiefSlot;

	public Kluis() {
		for (int i = 0; i < sloten.length; i++) {
			this.sloten[i] = new Slot();
		}
	}

	public Slot[] getSloten() {
		return sloten;
	}

	public Slot getActiefSlot() {
		return sloten[actiefSlot];
	}

	public void draaiSlotLinks(int positie) {
		this.actiefSlot = positie;
		int newPos = this.sloten[actiefSlot].getSymboolPositie() + 1;
		if(newPos >= this.sloten[actiefSlot].getSymbolen().size()) newPos = 0;
		this.sloten[actiefSlot].setSymboolPositie(newPos);
	}

	public void draaiSlotRechts(int positie) {
		this.actiefSlot = positie;
		int newPos = this.sloten[actiefSlot].getSymboolPositie() - 1;
		if(newPos < 0) newPos = this.sloten[actiefSlot].getSymbolen().size()-1;
		this.sloten[actiefSlot].setSymboolPositie(newPos);
	}

	public int getActiefSlotPositie() {
		return this.actiefSlot;
	}

	public boolean isActiefSlotJuist() {
		return sloten[actiefSlot].isPositieJuist();
	}

}
