package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Slot {

	private List<SymboolEnum> symbolen;
	private int positie;

	public Slot() {
		symbolen = new ArrayList<SymboolEnum>(Arrays.asList(SymboolEnum.values()));
		Collections.shuffle(symbolen);

		/* Debug
		for (Symbool symbool : symbolen) {
			System.out.println(symbool.toString());
		}
		// */
	}

	public List<SymboolEnum> getSymbolen() {
		return symbolen;
	}

	public SymboolEnum getSymbool() {
		return symbolen.get(positie);
	}

	public int getPositie() {
		return this.positie;
	}

	public void setPositie(int positie) {
		this.positie = positie;
	}

}
