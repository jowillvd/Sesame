package server.model;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import client.SesameObserver;
import server.model.kaarten.Schat;

public class Speler implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * Observer bestaat uit views, volgorde: 	[0]LobbyView, [0]ScoreView,
	 * 											[1]LinkerView,
	 * 											[2]KluisView, [2]SchatkamerView
	 * LobbyView wordt vervangen door ScoreView bij het opstarten van de Game,
	 * KluisView wordt vervangen door SchatkamerView bij het openen van de kluis.
	 */
	private List<SesameObserver> observers = new ArrayList<SesameObserver>(3);
	private int id;
	// Score staat in de volgende volgorde: [0]toverlamp, [1]kelk, [2]ketting, [3]ring
	private List<List<Schat>> score = new ArrayList<List<Schat>>(4);
	public String naam;
	private boolean beurt = false;

	public Speler(SesameObserver observer, int id) {
		this.observers.add(0, observer);
		this.id = id;
		this.naam = "Speler" + String.valueOf(id+1);
	}

	public List<SesameObserver> getObservers() {
		return this.observers;
	}

	public void setObserver(SesameObserver observer, int positie) throws RemoteException {
		if(positie == 0) {
			if(id == 0) observer.setEnabled(true);
			this.observers.set(positie, observer);
		} else if(observers.size() == 3){
			if(beurt) observer.setEnabled(true);
			this.observers.set(positie, observer);
		} else {
			if(id == 0) observer.setEnabled(true);
			this.observers.add(positie, observer);
		}
	}

	public int getId() {
		return this.id;
	}

	public int[] getScore() {
		int[] scoreArray = new int[9];

		for (int i = 0; i < score.size(); i++) {
			System.out.println(i);
			scoreArray[i] = score.get(i).size();
		}

		return scoreArray;
	}

	public void setSchat(Schat schat, int i) {
		this.score.get(i).add(schat);
	}

	public boolean isAanDeBeurt() {
		return this.beurt ;
	}

	public void setBeurt(boolean b) {
		this.beurt = b;
	}

}
