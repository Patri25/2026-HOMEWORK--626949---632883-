package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.Tempcomando;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai implements Tempcomando{
	private String direzione;
	private IO ioc;
	private Partita partita;
	
	public ComandoVai(IO ioc) {
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

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return ("vai");
	}

	@Override
	public String getParamtro() {
		// TODO Auto-generated method stub
		return this.direzione;
	}

}
