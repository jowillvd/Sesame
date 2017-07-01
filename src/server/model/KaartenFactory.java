package server.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import server.model.kaarten.Kaart;

public class KaartenFactory implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Kaart> kaarten = new ArrayList<Kaart>();

	public void addKaart(Kaart kaart) {
		kaarten.add(kaart);
	}

	public void removeKaart(Kaart kaart) {
		kaarten.remove(kaart);
	}

	public Kaart getKaart(int positie) {
		return kaarten.get(positie);
	}

	public boolean isGevuld() {
		if(kaarten.size() > 0) return true;
		return false;
	}

}
