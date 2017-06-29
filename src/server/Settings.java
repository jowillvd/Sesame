package server;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Settings {

	private static final Properties config;
	public static int MAX_SLANG = 0;

	private Settings() {
		throw new AssertionError();
	}

	static {
		Properties fallback = new Properties();
		fallback.put("key", "default");
		config = new Properties(fallback);
		try {
			InputStream stream = ClassLoader.class.getResourceAsStream("foo.properties");
			try {
				config.load(stream);
				MAX_SLANG = 100;//Integer.parseInt(config.getProperty("slang.max"));
			}
			finally {
				stream.close();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
