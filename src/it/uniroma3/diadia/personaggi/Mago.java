package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio {
	
	private static final String MESSAGGIO_DONO = "Sei un vero simpaticone, " +
			"con una mia magica azione, troverai un nuovo oggetto " +
			"per il tuo borsone!";
	private static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla...";
	
	private Attrezzo attrezzo;

	public Mago(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione);
		this.attrezzo = attrezzo;
	}

	@Override
	public String interagisci(Partita partita) {
		if (this.attrezzo != null) {
			partita.getLabirinto().getStanzaCorrente().addAttrezzo(this.attrezzo);
			this.attrezzo = null;
			return MESSAGGIO_DONO;
		} else {
			return MESSAGGIO_SCUSE;
		}
	}

	@Override
	public String riceviRegalo(Attrezzo regalo, Partita partita) {
		int nuovoPeso = regalo.getPeso() / 2;
		
		// Il mago crea un nuovo attrezzo con lo stesso nome ma peso dimezzato
		Attrezzo attrezzoModificato = new Attrezzo(regalo.getNome(), nuovoPeso);
		
		partita.getLabirinto().getStanzaCorrente().addAttrezzo(attrezzoModificato);
		
		return "Hocus Pocus! Ho dimezzato il peso di questo " + regalo.getNome() + " e l'ho lasciato a terra!";
	}
}