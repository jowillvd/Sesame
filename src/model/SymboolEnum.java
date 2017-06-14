package model;

import java.util.Random;

public enum SymboolEnum {

	KAMEEL("kameel"),
	VAAS("vaas"),
	MOSKEE("moskee"),
	PALMBOOM("palmboom"),
	ZWAARD("zwaard"),
	KLEED("kleed")
	;

	private final String icoon;
	private static final SymboolEnum[] iconen = values();
	private static final Random random = new Random();

	private SymboolEnum(final String icoon) {
		this.icoon = icoon;
	}

	public static SymboolEnum randomIcoon()  {
		return iconen[random.nextInt(iconen.length)];
	}

	@Override
    public String toString() {
        return icoon;
    }

	public String getIcoon() {
		return icoon;
	}

}
