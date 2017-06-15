package model;

import view.GeestkaartView;

public class Geestkaart {

	private SymboolEnum[] symbolen = new SymboolEnum[9];
	GeestkaartView geestkaartView;

	public Geestkaart() {
		for (int i = 0; i < this.symbolen.length; i++) {
			this.symbolen[i] = SymboolEnum.randomIcoon();
		}

		/* Debug
		for (SymboolEnum symboolEnum : this.symbolen) {
			System.out.println(symboolEnum.toString());
		}
		// */
	}

	public SymboolEnum[] getSymbolen() {
		return this.symbolen;
	}

	public void setGeestkaartView(GeestkaartView geestkaartView) {
		this.geestkaartView = geestkaartView;
	}

	public SymboolEnum getSymbool(int positie) {
		return this.symbolen[positie];
	}

}
