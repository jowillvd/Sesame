package server.model;

import java.io.Serializable;

public class Geestkaart implements Serializable {

	private static final long serialVersionUID = 1L;
	private SymboolEnum[] symbolen = new SymboolEnum[9];

	public Geestkaart() {
		for (int i = 0; i < this.symbolen.length; i++) {
			this.symbolen[i] = SymboolEnum.randomIcoon();
		}
	}

	public int[] getSymboolIds() {
		int[] ids = new int[9];
		for (int i = 0; i < symbolen.length; i++) {
			ids[i] = symbolen[i].getId();
		}
		return ids;
	}

	/*public String[] getSymbolen() {
		String[] symbolen = new String[9];
		for (int i = 0; i < this.symbolen.length; i++) {
			symbolen[i] = this.symbolen[i].icoon;
		}
		return symbolen;
	}*/

	public SymboolEnum[] getSymbolen() {
		return symbolen;
	}


}
