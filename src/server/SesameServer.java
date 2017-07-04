package server;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import client.SesameObserver;
import javafx.application.Platform;
import server.model.Speler;
import server.model.kaarten.Schat;
import server.model.Geestkaart;
import server.model.Kluis;
import server.model.Schatkamer;

public class SesameServer extends UnicastRemoteObject implements SesameServerInterface {

	private static final long serialVersionUID = 1L;
	private List<Speler> spelers = new ArrayList<Speler>();
	private Geestkaart geestkaart = new Geestkaart();
	private Kluis kluis = new Kluis();
	private Schatkamer schatkamer = new Schatkamer(this);
	private int spelerIndex;
	private int gepakteSlangen;
	private int pogingen = 0;
	private String instructies = "Welkom bij Sesame";

	public SesameServer() throws RemoteException {
		System.out.println(" - Server - Sesame Server wordt opgestart...");
		try {
			// Connect to RMI Registry
			Registry registry = LocateRegistry.createRegistry(1099);
			System.out.println(" - Server - Verbonden met RMI registry.");

			// Register this server object in the RMI Registry
			registry.rebind("SesameServer", this);
			System.out.println(" - Server - Server staat geregistreerd als \'SesameServer\' in RMI registry.");
			System.out.println(" \u2713 Server is gestart. Gehost op:");
	        System.out.println(InetAddress.getLocalHost().getHostAddress());

		} catch (RemoteException re){
			System.out.println(re);

		} catch (UnknownHostException uhe) {
			uhe.printStackTrace();
		}
	}

	// Functies die met de communicatie tussen client en server te maken hebben

	/**
	 * Stel de spelers + observers op de hoogte.
	 * @throws RemoteException
	 */
	@Override
	public void notifySpelers() throws RemoteException {
		System.out.println(" - Server - Spelers op de hoogte stellen");
		for(Speler speler : spelers) {
			for(SesameObserver observer : speler.getObservers()) {
				observer.update(this);
			}
		}
	}

	@Override
	public Speler registerSpeler(SesameObserver observer, String spelernaam) throws RemoteException {
		System.out.println(" - Server - Nieuwe speler registreren, speler id: "
				+ spelers.size());
		nextSpeler();
		Speler speler = new Speler(observer, spelers.size());
		spelers.add(speler);
		if(spelernaam != null && !spelernaam.trim().isEmpty()) {
			speler.naam = spelernaam;
		}

		try {
			notifySpelers();
			System.out.println(" \u2713 Speler \'" + spelers.get(spelers.size()-1).naam + "\' toegevoegd");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return speler;
	}

	@Override
	public void unregisterSpeler(Speler speler) throws RemoteException {
		System.out.println(" - Server - Observer registratie verwijderen, observer positie: "
				+ spelers.indexOf(speler));
		this.spelers.remove(speler);
		System.out.println(" \u2713 Observer verwijderd");
	}

	/**
	 * Voeg een observer toe aan de speler
	 */
	@Override
	public void addObserver(SesameObserver observer, int positie, Speler speler) throws RemoteException {
		int spelerID = speler.getId();
		this.spelers.get(spelerID).setObserver(observer, positie);
	}

	// PRIVATE functies

	/**
	 * De beurt wordt doorgegeven aan de volgend speler.
	 * @throws RemoteException
	 */
	private void nextSpeler() throws RemoteException {
		System.out.println(spelers.size());
		if (spelers.size() > 0) {
			spelers.get(spelerIndex).setBeurt(false);
			for (SesameObserver observer : spelers.get(spelerIndex).getObservers()) {
				observer.setEnabled(false);
			}
			spelerIndex++;
			if (spelerIndex >= spelers.size()) {
				spelerIndex = 0;
			}
			for (SesameObserver observer : spelers.get(spelerIndex).getObservers()) {
				observer.setEnabled(true);
			}
			spelers.get(spelerIndex).setBeurt(true);
			setInstructies(spelers.get(spelerIndex).naam + " is nu aan de beurt.");
		}
	}

	/**
	 *
	 */
	private void setInstructies(String instructie) {
		this.instructies = instructie;
	}

	/**
	 * Controleer de sloten.
	 * Er wordt gecontroleerd of de sloten gelijk staan met de geestkaart.
	 * @throws RemoteException
	 */
	private void checkSloten() {
		int aantalJuist = 0;
		for (int i = 0; i < kluis.getSloten().length; i++) {
			int slotId = kluis.getSloten()[i].getSymbool().id;
			int geestkaartSymboolId = geestkaart.getSymbolen()[i].id;

			if(slotId == geestkaartSymboolId) {
				kluis.getSloten()[i].setPositieJuist(true);
				aantalJuist++;
			}
		}
		if(aantalJuist == kluis.getSloten().length) {
			try {
				this.openKluis();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	private void checkPoging(int i) {
		int slotId = kluis.getSloten()[i].getSymbool().id;
		int geestkaartSymboolId = geestkaart.getSymbolen()[i].id;

		if(slotId == geestkaartSymboolId) {
			kluis.getSloten()[i].setPositieJuist(true);
			this.pogingen = 0;
		} else {
			this.pogingen++;
		}
		checkSloten();
	}

	// De getters

	/*
	 * (non-Javadoc)
	 * @see server.SesameServerInterface#getSpelers()
	 * @throws RemoteException
	 */
	@Override
	public List<Speler> getSpelers() throws RemoteException {
		return this.spelers;
	}

	/*
	 * (non-Javadoc)
	 * @see server.SesameServerInterface#getGeestkaart()
	 * @throws RemoteException
	 */
	@Override
	public Geestkaart getGeestkaart() throws RemoteException {
		return this.geestkaart;
	}

	/*
	 * (non-Javadoc)
	 * @see server.SesameServerInterface#getKluis()
	 * @throws RemoteException
	 */
	@Override
	public Kluis getKluis() throws RemoteException {
		return this.kluis;
	}

	/*
	 * (non-Javadoc)
	 * @see server.SesameServerInterface#getSchatkamer()
	 * @throws RemoteException
	 */
	@Override
	public Schatkamer getSchatkamer() throws RemoteException {
		return this.schatkamer;
	}

	/*
	 * (non-Javadoc)
	 * @see server.SesameServerInterface#getGepakteSlangen()
	 * @throws RemoteException
	 */
	@Override
	public int getGepakteSlangen() throws RemoteException {
		return this.gepakteSlangen;
	}

	@Override
	public String getInstructie() throws RemoteException {
		return this.instructies ;
	}

	// De spel functies

	/**
	 * Draai een slot naar links.
	 * Nadat een slot is gedraaid worden alle spelers op de hoogte gesteld.
	 * @param positie, de positie van het slot dat gedraaid moet worden.
	 * @throws RemoteException
	 */
	@Override
	public void draaiSlotLinks(int positie) throws RemoteException {
		try {
			kluis.draaiSlotLinks(positie);
			this.checkPoging(positie);
			System.out.println(" \u2713 Slot op positie \'" + positie + "\' aangepast");
			if(this.pogingen == 3) {
				this.pogingen = 0;
				this.nextSpeler();
				System.out.println(" - Server - De pogingen zijn voorbij, volgende speler is aan de beurt");
			}
			this.notifySpelers();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Draai een slot naar rechts.
	 * Nadat een slot is gedraaid worden alle spelers op de hoogte gesteld.
	 * @param positie, de positie van het slot dat gedraaid moet worden.
	 * @throws RemoteException
	 */
	@Override
	public void draaiSlotRechts(int positie) throws RemoteException {
		try {
			kluis.draaiSlotRechts(positie);
			this.checkPoging(positie);
			System.out.println(" \u2713 Slot op positie \'" + positie + "\' aangepast");
			if(this.pogingen == 3) {
				this.pogingen = 0;
				this.nextSpeler();
				System.out.println(" - Server - De pogingen zijn voorbij, volgende speler is aan de beurt");
			}
			this.notifySpelers();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Pak een kaart uit de schatkamer.
	 * Nadat een kaart is gepakt worden alle spelers op de hoogte gesteld.
	 * @param positie, de positie op welke stapel de kaart ligt
	 * @throws RemoteException
	 */
	@Override
	public void pakKaart(int positie) throws RemoteException {
		if(!(schatkamer.pakKaart(positie) instanceof Schat)) {
			this.gepakteSlangen++;
			if(schatkamer.getGepakteKaarten().size() > 0) {
				schatkamer.getGepakteKaarten().clear();
				this.beurtDoorgeven();
			} else {
				this.steelMode();
			}
		}
		try {
			this.notifySpelers();
			System.out.println(" \u2713 Schat op positie \'" + positie + "\' is getoond");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Steel schat van geselecteerde speler.
	 * Nadat de schat gestolen is worden alle spelers op de hoogte gesteld.
	 * @param schat, schat dat gestolen wordt
	 * @param vanSpeler, speler van wie wordt gestolen
	 * @param beurt, speler aan wie de schat gegeven moet worden
	 * @throws RemoteException
	 */
	@Override
	public void steelSchat(int schatPos, int vanSpeler, int beurt) throws RemoteException {
		Schat schat = spelers.get(vanSpeler).getScore().get(schatPos).get(0);
		spelers.get(beurt).getScore().get(schatPos).add(schat);
		spelers.get(vanSpeler).getScore().get(schatPos).remove(schat);
		try {
			this.beurtDoorgeven();
			System.out.println(" \u2713 De \'" + schat.getKaart() + "\' is gestolen van \'" + spelers.get(vanSpeler).naam +
					"\' en gegeven aan \'" + spelers.get(beurt).naam + "\'");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	// Alles wat met de game mode te maken heeft

	/**
	 * Start de game voor elke speler.
	 * Hierbij wordt alleen de observer van LobbyView op de hoogte gesteld. De gamemode
	 * veranderd naar buiten de kluis.
	 * @throws RemoteException
	 */
	@Override
	public void startGame() throws RemoteException {
		System.out.println(" - Server - Spel starten voor de spelers");
		for(Speler speler : spelers) {
			speler.getObservers().get(0).updateMode();
		}
		this.checkSloten();
	}

	/**
	 * Toon de kluis voor elke speler.
	 * Hierbij wordt alleen de observer van KluisView op de hoogte gesteld. De gamemode
	 * veranderd naar in de schatkamer.
	 * @throws RemoteException
	 */
	@Override
	public void openKluis() throws RemoteException {
		System.out.println(" - Server - Kluis wordt geopend en getoond aan alle spelers");
		for(Speler speler : spelers) {
			speler.getObservers().get(2).updateMode();
			speler.getObservers().get(1).updateMode();
		}
	}

	/**
	 * De speler kan een kaart stelen.
	 * Hierbij worden de observers van SchatkamerVie en ScoreView op de hoogte gesteld.
	 * De gamemode veranderd naar schat stelen van medespeler.
	 * @throws RemoteException
	 */
	@Override
	public void steelMode() throws RemoteException {
		int size = 0;
		System.out.println(" - Server - Het is mogelijk om een schat te stelen");
		setInstructies(spelers.get(spelerIndex).naam + " zijn eerste kaart is een slang. "
				+ spelers.get(spelerIndex).naam + " mag nu een schat stelen van een willekeurige speler");
		for(Speler speler : spelers) {
			if(!speler.isAanDeBeurt()) {
				for (List<Schat> schat : speler.getScore()) {
					if(schat.size() > 0){
						size++;
						break;
					}
				}
			}
		}
		if(size == 0) {
			this.beurtDoorgeven();
		} else {
			for (Speler speler : spelers) {
				speler.getObservers().get(0).updateMode();
			}
		}
	}

	/**
	 * @throws RemoteException
	 */
	public void beurtDoorgeven() throws RemoteException {
		if(schatkamer.getGepakteKaarten().size() > 0) {
			spelers.get(spelerIndex).geefPunten(schatkamer.getGepakteKaarten()); // Geef de tijdelijke punten aan de speler
			schatkamer.getGepakteKaarten().clear(); // Leeg de tijdelijke gepakte punten
		}
		this.kluis = new Kluis();
		this.geestkaart = new Geestkaart();
		for(Speler speler : spelers) {
			speler.getObservers().get(2).updateMode();
			this.notifySpelers();
			Platform.runLater(
					() -> {
						try {
							speler.getObservers().get(1).updateMode();
						} catch (RemoteException e) {
							e.printStackTrace();
						}

					}
			);
		}
		this.nextSpeler();
	}

}
