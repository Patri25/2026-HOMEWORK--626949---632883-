package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {

	private static final String MESSAGGIO_SALUTATA = "Gentile da parte tua, seguimi dove c'è ciò che ti è utile";
	private static final String MESSAGGIO_NON_SALUTATA = "Maleducato, non meriti di stare qui";

	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
	}

	@Override
	public String interagisci(Partita partita) {
		// Logica del teletrasporto (mantenuta uguale a prima)
		return this.haSalutato() ? MESSAGGIO_SALUTATA : MESSAGGIO_NON_SALUTATA;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		return "AHAHAHAHAH! Questo " + attrezzo.getNome() + " ora e' mio! AHAHAHAHAH!";
	}
}