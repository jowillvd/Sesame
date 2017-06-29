package server.model.kaarten;

public class Kelk implements Schat {

	private static final long serialVersionUID = 1L;
	private int waarde = 2;
	private boolean gepakt = false;
	private final String kaart = "kelk";

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
