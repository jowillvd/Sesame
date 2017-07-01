package server.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Slot implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<SymboolEnum> symbolen;
	private int positie;
	private boolean positieJuist = false;

	public Slot() {
		symbolen = new ArrayList<SymboolEnum>(Arrays.asList(SymboolEnum.values()));
		Collections.shuffle(symbolen);
	}

	public List<SymboolEnum> getSymbolen() {
		return symbolen;
	}

	public SymboolEnum getSymbool() {
		return symbolen.get(positie);
	}

	public int getSymboolPositie() {
		return this.positie;
	}

	public void setSymboolPositie(int positie) {
		this.positie = positie;
	}

	public void setPositieJuist(boolean bool) {
		this.positieJuist = bool;
	}

	public boolean isPositieJuist() {
		return this.positieJuist;
	}

}
