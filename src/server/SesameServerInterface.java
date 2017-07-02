package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import client.SesameObserver;

import server.model.Geestkaart;
import server.model.Kluis;
import server.model.Schatkamer;
import server.model.Speler;

public interface SesameServerInterface extends Remote {

	public void notifySpelers() throws RemoteException;

	public Speler registerSpeler(SesameObserver observer, String spelernaam) throws RemoteException;

	public void unregisterSpeler(Speler speler) throws RemoteException;

	public List<Speler> getSpelers() throws RemoteException;

	public Geestkaart getGeestkaart() throws RemoteException;

	public Kluis getKluis() throws RemoteException;

	public Schatkamer getSchatkamer() throws RemoteException;

	public void addObserver(SesameObserver observer, int positie, Speler speler) throws RemoteException;

	public void draaiSlotLinks(int positie) throws RemoteException;

	public void draaiSlotRechts(int positie) throws RemoteException;

	public void startGame() throws RemoteException;

	public void openKluis() throws RemoteException;

	public void pakKaart(int positie) throws RemoteException;

	public void steelMode() throws RemoteException;

	public void steelSchat(int schat, int vanSpeler, int beurt) throws RemoteException;

	public int getGepakteSlangen() throws RemoteException;

	public String getInstructie() throws RemoteException;

	public void beurtDoorgeven() throws RemoteException;

}
