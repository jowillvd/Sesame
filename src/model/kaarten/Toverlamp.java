package model.kaarten;

public class Toverlamp implements Schat{
	private int waarde = 5;
	private boolean gepakt;

	@Override
	public void setGepakt(boolean gepakt) {
		this.gepakt = gepakt;
	}

	@Override
	public boolean isGepakt() {
		return gepakt;
	}

	@Override
	public void toonKaart(String icoon) {
		icoon = "Kelk";

	}

	@Override
	public int getWaarde() {
		return waarde;
	}

	@Override
	public void setSpeler(String speler) {
		Speler.naam = speler;

	}

	@Override
	public String getSpeler() {
		return Speler.naam;
	}

}

