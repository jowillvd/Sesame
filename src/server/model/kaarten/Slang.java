package server.model.kaarten;

public class Slang implements Kaart {

	private static final long serialVersionUID = 1L;
	private boolean gepakt = false;
	private final String kaart = "slang";

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

}
