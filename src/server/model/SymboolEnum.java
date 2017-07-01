package server.model;

import java.io.Serializable;
import java.util.Random;

public enum SymboolEnum implements Serializable {

	KAMEEL("kameel", 0),
	VAAS("vaas", 1),
	MOSKEE("moskee", 2),
	PALMBOOM("palmboom", 3),
	ZWAARD("zwaard", 4),
	TAPIJT("tapijt", 5)
	;

	public final String icoon;
	public final int id;
	private static final SymboolEnum[] iconen = values();
	private static final Random random = new Random();

	private SymboolEnum(final String icoon, final int id) {
		this.icoon = icoon;
		this.id = id;
	}

	public static SymboolEnum randomIcoon()  {
		return iconen[random.nextInt(iconen.length)];
	}


	public int getId() {
		return id;
	}

}
