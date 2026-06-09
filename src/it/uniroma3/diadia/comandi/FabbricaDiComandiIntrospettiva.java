package it.uniroma3.diadia.comandi;

import java.util.Scanner;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Tempcomando;

public class FabbricaDiComandiIntrospettiva implements FabbricaDiComandi {

	private IO io;

	public FabbricaDiComandiIntrospettiva(IO io) {
		this.io = io;
	}

	@Override
	public Tempcomando costruisciComando(String istruzione) {
		Scanner scannerDiParole = new Scanner(istruzione);
		String nomeComando = null;
		String parametro = null;
		Tempcomando comando = null;

		if (scannerDiParole.hasNext()) {
			nomeComando = scannerDiParole.next();
		}
		if (scannerDiParole.hasNext()) {
			parametro = scannerDiParole.next();
		}

		if (nomeComando == null) {
			nomeComando = "nonValido";
		}

		try {
			// Costruisce dinamicamente il nome della classe: es "vai" -> "ComandoVai"
			String nomeClasse = "it.uniroma3.diadia.comandi.Comando" + 
					Character.toUpperCase(nomeComando.charAt(0)) + 
					nomeComando.substring(1);
			
			// Usa la Reflection per istanziare l'oggetto
			comando = (Tempcomando) Class.forName(nomeClasse).getDeclaredConstructor().newInstance();
			comando.setParametro(parametro);
			
		} catch (Exception e) {
			// Cattura ClassNotFoundException, InstantiationException, IllegalAccessException, ecc.
			// Se la classe non esiste o non può essere istanziata, restituisce ComandoNonValido
			comando = new ComandoNonValido();
			comando.setParametro(parametro);
		} finally {
			scannerDiParole.close();
		}
		
		return comando;
	}
}