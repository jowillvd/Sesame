package model;

public class Geestkaart {

	private SymboolEnum[] symbolen = new SymboolEnum[9];

	public Geestkaart() {
		for (int i = 0; i < this.symbolen.length; i++) {
			this.symbolen[i] = SymboolEnum.randomIcoon();
		}
		/*for (SymboolEnum symboolEnum : this.symbolen) {
			System.out.println(symboolEnum.toString());
		}*/
	}

	public SymboolEnum[] getSymbolen() {
		return this.symbolen;
	}

}
