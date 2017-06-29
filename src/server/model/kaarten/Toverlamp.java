package server.model.kaarten;

public class Toverlamp implements Schat {

	private static final long serialVersionUID = 1L;
	private int waarde = 5;
	private boolean gepakt = false;
	private final String kaart = "toverlamp";

	@Override
	public boolean isGepakt() {
		return gepakt;
	}

	@Override
	public void setGepakt(boolean bool) {
		this.gepakt = bool;
	}

	@Override
	public String getKaart() {
		return this.kaart;
	}

	@Override
	public int getWaarde() {
		return this.waarde;
	}

}
