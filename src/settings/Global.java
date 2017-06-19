package settings;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Global {

	private static final Properties properties = new Properties();
	private static InputStream input = null;

	public static void loadProps() {
		try {
			input = new FileInputStream("config.properties");

			properties.load(input);

		} catch(IOException e) {

		}
		
	}



    

}
