package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoSaluta extends AbstractComando {
	
	private IO io;

	public ComandoSaluta() {
		this.io = new IOConsole();
	}
	
	public ComandoSaluta(IO io) {
		this.io = io;
	}

	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio = partita.getLabirinto().getStanzaCorrente().getPersonaggio();
		if (personaggio != null) {
			this.io.mostraMessaggio(personaggio.saluta());
		} else {
			this.io.mostraMessaggio("Chi vuoi salutare? Non c'e' nessuno qui.");
		}
	}
}