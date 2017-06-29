package server.model.kaarten;

import java.io.Serializable;

public interface Kaart extends Serializable {

	public boolean isGepakt();

	public void setGepakt(boolean bool);

	public String getKaart();

}
