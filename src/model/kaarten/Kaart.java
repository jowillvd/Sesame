package model.kaarten;

public interface Kaart {
	public void setGepakt(boolean gepakt);

	public boolean isGepakt();

	public void toonKaart(String icoon);

	}
}