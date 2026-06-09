package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda extends AbstractComando {
	private Partita partita;
	private IO ioc;
	
	public ComandoGuarda() {
		this.ioc = new IOConsole();
	}
	
	public ComandoGuarda(IO ioc) {
		this.ioc=ioc;
	}
	
	//«guarda» stampa le informazioni sulla stanza corrente e sullo stato della partita
	

	@Override
	public void esegui(Partita partita) {
		// Dalla partita ricavo le altre info richieste
		this.partita=partita;
		//Info stanza corrente
		this.ioc.mostraMessaggio(this.partita.getLabirinto().getStanzaCorrente().getDescrizione());
		//Stato della partita
		this.ioc.mostraMessaggio("CFU rimanenti: " + partita.getGiocatore().getCfu());
        this.ioc.mostraMessaggio("Contenuto borsa: " + partita.getGiocatore().getBorsa().toString());
		
		
	}

}