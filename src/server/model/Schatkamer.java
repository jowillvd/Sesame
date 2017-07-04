package server.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import server.model.kaarten.Kaart;
import server.model.kaarten.Kelk;
import server.model.kaarten.Ketting;
import server.model.kaarten.Ring;
import server.model.kaarten.Schat;
import server.model.kaarten.Slang;
import server.model.kaarten.Toverlamp;

public class Schatkamer implements Serializable {

	private static final long serialVersionUID = 1L;
	private KaartenStapel[] stapels = new KaartenStapel[9];
	private Kaart gepakteKaart;
	private List<Schat> gepakteKaarten = new ArrayList<Schat>();
	private int actiefStapel;

	/**
	 * De constructor van de schatkamer.
	 * Er wordt een list gemaakt met kaarten die gebruikt kunnen worden. Vervolgens
	 * worden die kaarten verdeeld over 9 KaartenStapel's. Die KaartenStapel's staan
	 * in een ArrayList genaamd stapels.
	 * @param server
	 */
	public Schatkamer() {
		List<Kaart> uitdeelbareKaarten = new ArrayList<Kaart>(36); // Mogelijke kaarten
		for(int i = 0; i < 12; i++) {
			uitdeelbareKaarten.add(new Ring());
		}
		for(int i = 0; i < 10; i++) {
			uitdeelbareKaarten.add(new Kelk());
		}
		for(int i = 0; i < 7; i++) {
			uitdeelbareKaarten.add(new Slang());
		}
		for(int i = 0; i < 6; i++) {
			uitdeelbareKaarten.add(new Ketting());
		}
		for(int i = 0; i < 1; i++) {
			uitdeelbareKaarten.add(new Toverlamp());
		}
		Collections.shuffle(uitdeelbareKaarten);

		int kaartIndex = 0;
		for(int i = 0; i < stapels.length; i++) {
			this.stapels[i] = new KaartenStapel();
			for(int k = 0; k < uitdeelbareKaarten.size()/9; k++) {
				this.stapels[i].addKaart(uitdeelbareKaarten.get(kaartIndex));
				kaartIndex++;
			}
		}
		uitdeelbareKaarten.clear();
	}

	/**
	 * Zet de stapel actief en pak er een kaart uit.
	 * De kaart die boven op de actieve stapel ligt wordt gecontroleerd of het
	 * een slang is. De kaart die gepakt is wordt tijdelijk in de ArrayList gepakteKaarten gezet.
	 * @param positie
	 * @return schat, of de gepakte kaart een schat is
	 */
	public Kaart pakKaart(int positie) {
		Kaart kaart = stapels[positie].getBovensteKaart();
		this.actiefStapel = positie;
		if(kaart.getKaart() != "slang") {
			this.gepakteKaarten.add((Schat) kaart);
		}
		stapels[actiefStapel].removeKaart(kaart);
		this.gepakteKaart = kaart;
		return kaart;
	}

	/**
	 * Krijg de bovenste kaart van de actieve stapel.
	 * @return kaart, String van kaart
	 */
	public String getKaart() {
		if(stapels[actiefStapel].isGevuld()) return stapels[actiefStapel].getBovensteKaart().getKaart();
		return null;
	}

	/**
	 * Krijg de positie van de actieve stapel in het spel.
	 * @return
	 */
	public int getActiefStapelPositie() {
		return this.actiefStapel;
	}

	/**
	 * Krijg de actieve stapel in het spel.
	 * @return
	 */
	public KaartenStapel getActiefStapel() {
		return this.stapels[actiefStapel];
	}

	public Kaart getGepakteKaart() {
		return this.gepakteKaart;
	}

	public List<Schat> getGepakteKaarten() {
		return this.gepakteKaarten;
	}

}
