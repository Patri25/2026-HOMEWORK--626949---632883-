package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.Scanner;

public class CaricatoreLabirinto {

	private Scanner scannerDiLinee;
	
	// Utilizziamo il tipo della classe nidificata
	private Labirinto.LabirintoBuilder builder;

	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this.scannerDiLinee = new Scanner(new FileReader(nomeFile));
		// Inizializziamo il builder usando il factory method statico
		this.builder = Labirinto.newBuilder();
	}

	public CaricatoreLabirinto(Reader reader) {
		this.scannerDiLinee = new Scanner(reader);
		// Inizializziamo il builder usando il factory method statico
		this.builder = Labirinto.newBuilder();
	}

	public void carica() {
		try {
			this.leggiStanze();
			this.leggiStanzeMagiche();
			this.leggiStanzeBuie();
			this.leggiStanzeBloccate();
			this.leggiEstremi();
			this.leggiAttrezzi();
			this.leggiUscite();
			this.leggiMaghi();
			this.leggiCani();
			this.leggiStreghe();
		} finally {
			this.scannerDiLinee.close();
		}
	}
	
	public Labirinto getLabirinto() {
		return this.builder.getLabirinto();
	}

	private String leggiRigaCheCominciaPer(String marker) {
		try {
			String riga = this.scannerDiLinee.nextLine();
			if (!riga.startsWith(marker)) {
				throw new RuntimeException("Formato errato: aspettavo " + marker + " ma ho trovato " + riga);
			}
			return riga.substring(marker.length()).trim();
		} catch (Exception e) {
			throw new RuntimeException("Lettura fallita per il marker: " + marker, e);
		}
	}

	private void leggiStanze() {
		String nomiStanze = this.leggiRigaCheCominciaPer("Stanze:");
		try (Scanner scannerDiParole = new Scanner(nomiStanze)) {
			while (scannerDiParole.hasNext()) {
				this.builder.addStanza(scannerDiParole.next());
			}
		}
	}
	
	private void leggiStanzeMagiche() {
		String stanze = this.leggiRigaCheCominciaPer("Magiche:");
		try (Scanner scanner = new Scanner(stanze)) {
			while (scanner.hasNext()) {
				String nomeStanza = scanner.next();
				int soglia = Integer.parseInt(scanner.next());
				this.builder.addStanzaMagica(nomeStanza, soglia);
			}
		}
	}

	private void leggiStanzeBuie() {
		String stanze = this.leggiRigaCheCominciaPer("Buie:");
		try (Scanner scanner = new Scanner(stanze)) {
			while (scanner.hasNext()) {
				String nomeStanza = scanner.next();
				String attrezzoLuce = scanner.next();
				this.builder.addStanzaBuia(nomeStanza, attrezzoLuce);
			}
		}
	}

	private void leggiStanzeBloccate() {
		String stanze = this.leggiRigaCheCominciaPer("Bloccate:");
		try (Scanner scanner = new Scanner(stanze)) {
			while (scanner.hasNext()) {
				String nomeStanza = scanner.next();
				String direzione = scanner.next();
				String chiave = scanner.next();
				this.builder.addStanzaBloccata(nomeStanza, direzione, chiave);
			}
		}
	}

	private void leggiEstremi() {
		String estremi = this.leggiRigaCheCominciaPer("Estremi:");
		try (Scanner scannerDiParole = new Scanner(estremi)) {
			if (scannerDiParole.hasNext()) {
				this.builder.addStanzaIniziale(scannerDiParole.next());
			}
			if (scannerDiParole.hasNext()) {
				this.builder.addStanzaVincente(scannerDiParole.next());
			}
		}
	}

	private void leggiAttrezzi() {
		String attrezzi = this.leggiRigaCheCominciaPer("Attrezzi:");
		try (Scanner scannerDiParole = new Scanner(attrezzi)) {
			while (scannerDiParole.hasNext()) {
				String nomeAttrezzo = scannerDiParole.next();
				int peso = Integer.parseInt(scannerDiParole.next());
				String nomeStanza = scannerDiParole.next();
				this.builder.addAttrezzo(nomeStanza, nomeAttrezzo, peso);
			}
		}
	}

	private void leggiUscite() {
		String uscite = this.leggiRigaCheCominciaPer("Uscite:");
		try (Scanner scannerDiParole = new Scanner(uscite)) {
			while (scannerDiParole.hasNext()) {
				String stanzaPartenza = scannerDiParole.next();
				String direzione = scannerDiParole.next();
				String stanzaDestinazione = scannerDiParole.next();
				this.builder.addAdiacenza(stanzaPartenza, stanzaDestinazione, direzione);
			}
		}
	}

	private void leggiMaghi() {
		String maghi = this.leggiRigaCheCominciaPer("Maghi:");
		try (Scanner scanner = new Scanner(maghi)) {
			while (scanner.hasNext()) {
				String nome = scanner.next();
				String presentazione = scanner.next().replace("_", " ");
				String nomeAttrezzo = scanner.next();
				int peso = Integer.parseInt(scanner.next());
				String nomeStanza = scanner.next();
				this.builder.addMago(nomeStanza, nome, presentazione, nomeAttrezzo, peso);
			}
		}
	}

	private void leggiCani() {
		String cani = this.leggiRigaCheCominciaPer("Cani:");
		try (Scanner scanner = new Scanner(cani)) {
			while (scanner.hasNext()) {
				String nome = scanner.next();
				String presentazione = scanner.next().replace("_", " ");
				String nomeAttrezzo = scanner.next();
				int peso = Integer.parseInt(scanner.next());
				String nomeStanza = scanner.next();
				this.builder.addCane(nomeStanza, nome, presentazione, nomeAttrezzo, peso);
			}
		}
	}

	private void leggiStreghe() {
		String streghe = this.leggiRigaCheCominciaPer("Streghe:");
		try (Scanner scanner = new Scanner(streghe)) {
			while (scanner.hasNext()) {
				String nome = scanner.next();
				String presentazione = scanner.next().replace("_", " ");
				String nomeStanza = scanner.next();
				this.builder.addStrega(nomeStanza, nome, presentazione);
			}
		}
	}
}