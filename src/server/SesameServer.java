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

import server.model.Speler;
import server.model.Geestkaart;
import server.model.Kluis;
import server.model.Schatkamer;

public class SesameServer extends UnicastRemoteObject implements SesameServerInterface {

	private static final long serialVersionUID = 1L;
	private List<Speler> spelers = new ArrayList<Speler>();
	private int spelerIndex;
	private Geestkaart geestkaart = new Geestkaart();
	private Kluis kluis = new Kluis(this, geestkaart.getSymboolIds());
	private Schatkamer schatkamer = new Schatkamer(this);

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

	@Override
	public void addObserver(SesameObserver observer, int positie, Speler speler) throws RemoteException {
		int spelerID = speler.getId();
		this.spelers.get(spelerID).setObserver(observer, positie);
	}

	private void nextSpeler() throws RemoteException {
		if (spelers.size() > 0) {
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
		}
	}

	@Override
	public List<Speler> getSpelers() throws RemoteException {
		return this.spelers;
	}

	@Override
	public Geestkaart getGeestkaart() throws RemoteException {
		return this.geestkaart;
	}

	@Override
	public Kluis getKluis() throws RemoteException {
		return this.kluis;
	}

	@Override
	public Schatkamer getSchatkamer() throws RemoteException {
		return this.schatkamer;
	}

	@Override
	public void draaiSlotLinks(int positie) throws RemoteException {
		kluis.draaiSlotLinks(positie, geestkaart.getSymboolIds());
		try {
			this.getKluis().checkSloten(this.getGeestkaart().getSymboolIds()[positie]);
			this.notifySpelers();
			System.out.println(" \u2713 Slot op positie \'" + positie + "\' aangepast");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void draaiSlotRechts(int positie) throws RemoteException {
		kluis.draaiSlotRechts(positie, geestkaart.getSymboolIds());
		try {
			kluis.checkSloten(this.getGeestkaart().getSymboolIds()[positie]);
			this.notifySpelers();
			System.out.println(" \u2713 Slot op positie \'" + positie + "\' aangepast");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void pakKaart(int positie) throws RemoteException {
		schatkamer.pakKaart(positie);
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
	public void steelSchat(int schat, int vanSpeler, int beurt) throws RemoteException {
		try {
			this.notifySpelers();
			System.out.println(" \u2713 De \'" + schat + "\' is gestolen van \'" + spelers.get(vanSpeler).naam +
					"\' en gegeven aan \'" + spelers.get(beurt).naam + "\'");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Start de game voor elke speler.
	 * Hierbij wordt alleen de observer van LobbyView op de hoogte gesteld. De gamemode
	 * veranderd naar buiten de kluis.
	 */
	@Override
	public void startGame() throws RemoteException {
		System.out.println(" - Server - Spel starten voor de spelers");
		for(Speler speler : spelers) {
			speler.getObservers().get(0).updateMode();
		}
	}

	/**
	 * Toon de kluis voor elke speler.
	 * Hierbij wordt alleen de observer van KluisView op de hoogte gesteld. De gamemode
	 * veranderd naar in de schatkamer.
	 */
	@Override
	public void openKluis() throws RemoteException {
		System.out.println(" - Server - Kluis wordt geopend en getoond aan alle spelers");
		for(Speler speler : spelers) {
			speler.getObservers().get(2).updateMode();
		}
	}

	/**
	 * De speler kan een kaart stelen.
	 * Hierbij worden de observers van SchatkamerVie en ScoreView op de hoogte gesteld.
	 * De gamemode veranderd naar schat stelen van medespeler.
	 */
	@Override
	public void steelMode() throws RemoteException {
		System.out.println(" - Server - Het is mogelijk om een schat te stelen");
		for(Speler speler : spelers) {
			speler.getObservers().get(0).updateMode();
			speler.getObservers().get(3).updateMode();
		}
	}

}
