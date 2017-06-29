package server.model;

import java.io.Serializable;
import java.rmi.RemoteException;

import server.SesameServerInterface;

public class Kluis implements Serializable {

	private static final long serialVersionUID = 1L;
	private SesameServerInterface server;
	private Slot[] sloten = new Slot[9];
	private int actiefSlot;

	public Kluis(SesameServerInterface server, int geestkaartSymboolId[]) {
		this.server = server;
		for (int i = 0; i < sloten.length; i++) {
			this.sloten[i] = new Slot(geestkaartSymboolId[i]);
		}
	}

	public String[] getSloten() {
		String[] sloten = new String[9];
		for (int i = 0; i < this.sloten.length; i++) {
			Slot slot = this.sloten[i];
			sloten[i] = slot.getSymbolen().get(slot.getPositie()).getIcoon();
		}
		return sloten;
	}

	public String getActiefSlot() {
		Slot slot = this.sloten[actiefSlot];
		return slot.getSymbolen().get(slot.getPositie()).getIcoon();
	}

	public void draaiSlotLinks(int positie, int geestkaartSymboolID[]) {
		this.actiefSlot = positie;
		int newPos = this.sloten[actiefSlot].getPositie() + 1;
		if(newPos >= this.sloten[actiefSlot].getSymbolen().size()) newPos = 0;
		this.sloten[actiefSlot].setPositie(newPos);
	}

	public void draaiSlotRechts(int positie, int geestkaartSymboolID[]) {
		this.actiefSlot = positie;
		int newPos = this.sloten[actiefSlot].getPositie() - 1;
		if(newPos < 0) newPos = this.sloten[actiefSlot].getSymbolen().size()-1;
		this.sloten[actiefSlot].setPositie(newPos);
	}

	public int getActiefSlotPositie() {
		return this.actiefSlot;
	}

	public void checkSloten(int geestkaartSymboolId) {
		int aantalJuisteSloten = 0;
		this.sloten[actiefSlot].checkPositie(geestkaartSymboolId);
		for (int i = 0; i < sloten.length; i++) {
			if(sloten[i].isPositieJuist() == true) {

				aantalJuisteSloten++;
			}
		}
		System.out.println("Totaal goed: " + aantalJuisteSloten);
		if(aantalJuisteSloten == sloten.length) {
			try {
				this.server.openKluis();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		try {
			this.server.notifySpelers();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
