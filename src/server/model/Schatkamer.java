package server.model;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import server.SesameServerInterface;
import server.model.kaarten.Kaart;
import server.model.kaarten.Kelk;
import server.model.kaarten.Ketting;
import server.model.kaarten.Ring;
import server.model.kaarten.Slang;
import server.model.kaarten.Toverlamp;

public class Schatkamer implements Serializable {

	private static final long serialVersionUID = 1L;
	private SesameServerInterface server;
	private KaartenStapel[] stapels = new KaartenStapel[9];
	private List<Kaart> gepakteKaarten = new ArrayList<Kaart>();
	private int actiefStapel;

	/**
	 * De constructor van de schatkamer.
	 * Er wordt een list gemaakt met kaarten die gebruikt kunnen worden. Vervolgens
	 * worden die kaarten verdeeld over 9 KaartenStapel's. Die KaartenStapel's staan
	 * in een ArrayList genaamd stapels.
	 * @param server
	 */
	public Schatkamer(SesameServerInterface server) {
		this.server = server;

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
	 * De kaart die gepakt is wordt tijdelijk in de ArrayList gepakteKaarten gezet.
	 * @param positie
	 */
	public void pakKaart(int positie) {
		this.actiefStapel = positie;
		this.checkKaart();
	}

	/**
	 * Krijg de bovenste kaart van de actieve stapel.
	 * @return kaart, String van kaart
	 */
	public String getKaart() {
		return stapels[actiefStapel].getBovensteKaart().getKaart();
	}

	/**
	 * Krijg de positie van de actieve stapel in het spel.
	 * @return
	 */
	public int getActiefStapelPositie() {
		return this.actiefStapel;
	}

	/**
	 * Controleer de gepakte kaart.
	 * De kaart die boven op de actieve stapel ligt wordt gecontroleerd of het
	 * een slang is. Als de kaart een slang is en er zijn nog geen andere kaarten
	 * gepakt dan wordt de steel mode ingeschakeld. Als de kaart een slang is en
	 * er zijn wel andere kaarten gepakt dan worden de gepakt kaarten verwijderd.
	 */
	public void checkKaart() {
		Kaart kaart = stapels[actiefStapel].getBovensteKaart();
		if(kaart.getKaart() == "slang"
				&& gepakteKaarten.size() == 0) {
			try {
				this.server.steelMode();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		} else if(kaart.getKaart() == "slang"
				&& gepakteKaarten.size() > 0) {
			this.gepakteKaarten.clear();
		} else {
			this.gepakteKaarten.add(kaart);
		}
		stapels[actiefStapel].removeKaart(kaart);
	}

}
