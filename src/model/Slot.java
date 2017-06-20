package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Slot {

	private List<SymboolEnum> symbolen;
	private int positie = 0;

	public Slot() {
		symbolen = new ArrayList<SymboolEnum>(Arrays.asList(SymboolEnum.values()));
		Collections.shuffle(symbolen);

		//* Debug
		for (SymboolEnum symbool : symbolen) {
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
		if(positie >= 6){
			this.positie = 0;
		}
		else if(positie == 0){
			this.positie = 6;
		}
	}

}
