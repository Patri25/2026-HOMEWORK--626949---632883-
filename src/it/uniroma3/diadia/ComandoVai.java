package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai implements Tempcomando{
	private String direzione;
	private IOConsole ioc;
	private Partita partita;
	
	public ComandoVai(IOConsole ioc) {
		this.ioc=ioc;
	}

	@Override
	public void esegui(Partita partita) {
		this.partita=partita;
		if(direzione==null) {
			this.ioc.mostraMessaggio("Dove vuoi andare ?");
			return;
		}
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			this.ioc.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.getLabirinto().setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(cfu-1);
		}
		this.ioc.mostraMessaggio(this.partita.getLabirinto().getStanzaCorrente().getDescrizione());
		
	}

	@Override
	public void setParametro(String parametro) {
		this.direzione=parametro;
		
	}

}
