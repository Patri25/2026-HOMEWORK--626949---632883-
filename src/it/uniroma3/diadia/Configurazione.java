package it.uniroma3.diadia;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configurazione {

	private static final String NOME_FILE = "diadia.properties";
	private static Properties prop;

	static {
		prop = new Properties();
		try {
			// Questo è il trucco per leggere i file da dentro il .jar!
			InputStream input = Configurazione.class.getClassLoader().getResourceAsStream(NOME_FILE);
			if (input != null) {
				prop.load(input);
			} else {
				System.err.println("File " + NOME_FILE + " non trovato. Uso valori di default.");
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static int getCFU() {
		if(prop == null) return 20;
		return Integer.parseInt(prop.getProperty("cfu_iniziali", "20"));
	}

	public static int getPesoMaxBorsa() {
		if(prop == null) return 10;
		return Integer.parseInt(prop.getProperty("peso_max_borsa", "10"));
	}
}