package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.Tempcomando;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai implements Tempcomando {
	private String direzione;
	private IO ioc;
	private Partita partita;
	
	public ComandoVai() {
	}

	// Costruttore originale
	public ComandoVai(IO ioc) {
		this.ioc = ioc;
	}

	@Override
	public void esegui(Partita partita) {
		this.partita = partita;
		if (direzione == null) {
			// Controllo di sicurezza su ioc
			if (this.ioc != null) {
				this.ioc.mostraMessaggio("Dove vuoi andare ?");
			}
			return;
		}
		
		Stanza prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null) {
			if (this.ioc != null) {
				this.ioc.mostraMessaggio("Direzione inesistente");
			}
		} else {
			this.partita.getLabirinto().setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(cfu - 1);
		}
		
		if (this.ioc != null) {
			this.ioc.mostraMessaggio(this.partita.getLabirinto().getStanzaCorrente().getDescrizione());
		}
	}

	@Override
	public void setParametro(String parametro) {
		this.direzione = parametro;
	}

	@Override
	public String getNome() {
		return "vai";
	}

	@Override
	public String getParamtro() { // Mantenuto uguale alla tua interfaccia
		return this.direzione;
	}
}