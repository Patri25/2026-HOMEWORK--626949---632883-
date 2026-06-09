package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {

	private static final String MESSAGGIO_MORSO = "Grrr";
	private static final String MESSAGGIO_CIBO = "Il cane apprezza e lascia cadere qualcosa!";
	private static final String CIBO_PREFERITO = "osso";
	private Attrezzo attrezzoDaCedere;

	public Cane(String nome, String presentazione, Attrezzo attrezzoDaCedere) {
		super(nome, presentazione);
		this.attrezzoDaCedere = attrezzoDaCedere;
	}

	@Override
	public String interagisci(Partita partita) {
		int cfuAttuali = partita.getGiocatore().getCfu();
		partita.getGiocatore().setCfu(cfuAttuali - 1);
		return MESSAGGIO_MORSO;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if (attrezzo.getNome().equals(CIBO_PREFERITO)) {
			if (this.attrezzoDaCedere != null) {
				partita.getLabirinto().getStanzaCorrente().addAttrezzo(this.attrezzoDaCedere);
				this.attrezzoDaCedere = null;
			}
			return MESSAGGIO_CIBO;
		} else {
			int cfu = partita.getGiocatore().getCfu();
			partita.getGiocatore().setCfu(cfu - 1);
			return MESSAGGIO_MORSO + " Non mi piace questo regalo!";
		}
	}
}