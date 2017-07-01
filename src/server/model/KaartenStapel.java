package server.model;

import java.io.Serializable;

import server.model.kaarten.Kaart;

public class KaartenStapel implements Serializable {

	private static final long serialVersionUID = 1L;
	private KaartenFactory kaarten;

	public KaartenStapel() {
		this.kaarten = new KaartenFactory();
	}

	public void addKaart(Kaart kaart) {
		this.kaarten.addKaart(kaart);
	}

	public void removeKaart(Kaart kaart) {
		this.kaarten.removeKaart(kaart);
	}

	public Kaart getBovensteKaart() {
		return this.kaarten.getKaart(0);
	}

	public boolean isGevuld() {
		return this.kaarten.isGevuld();
	}

}
