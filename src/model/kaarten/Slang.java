package model.kaarten;

public class Slang implements Kaart{
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
		icoon = "Slang";

	}

}