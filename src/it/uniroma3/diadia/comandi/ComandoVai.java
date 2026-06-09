package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai extends AbstractComando {
	
	private IO ioc;
	private Partita partita;
	
	public ComandoVai() {
		this.ioc = new IOConsole();
	}

	public ComandoVai(IO ioc) {
		this.ioc = ioc;
	}

	@Override
	public void esegui(Partita partita) {
		this.partita = partita;
		String direzioneStringa = this.getParamtro(); 
		
		if (direzioneStringa == null) {
			this.ioc.mostraMessaggio("Dove vuoi andare ?");
			return;
		}
		
		Direzione direzioneEnum;
		try {

			//NON RIESCO A RISOLVERE QUESTO PROBLEMA, SE CAMBIO QUESTO ALTRE CLASSI DANNO ERRORE E ECLIPSE NON DA SUGGERIMENTI UTILI
			direzioneEnum = Direzione.valueOf(direzioneStringa);
		} catch (IllegalArgumentException e) {

			this.ioc.mostraMessaggio("Direzione inesistente o non valida!");
			return;
		}
		

		Stanza prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzioneEnum);
		
		if (prossimaStanza == null) {
			this.ioc.mostraMessaggio("Non c'e' una porta in quella direzione");
		} else {
			this.partita.getLabirinto().setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(cfu - 1);
		}
		
		this.ioc.mostraMessaggio(this.partita.getLabirinto().getStanzaCorrente().getDescrizione());
	}
}