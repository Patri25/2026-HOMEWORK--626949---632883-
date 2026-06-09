package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoFine extends AbstractComando {
	private Partita partita;
	private IO ioc;
	
	public ComandoFine() {
		this.ioc = new IOConsole();
	}
	
	public ComandoFine(IO ioc) {
		this.ioc = ioc;
	}

	@Override
	public void esegui(Partita partita) {
		this.partita=partita;
		this.ioc.mostraMessaggio("Grazie di aver giocato!");  
		partita.setFinita();
	}

}