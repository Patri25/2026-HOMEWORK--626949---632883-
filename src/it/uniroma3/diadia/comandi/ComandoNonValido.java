package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando {

	private IO io;

	public ComandoNonValido() {
		this.io = new IOConsole(); 
	}
	
	public ComandoNonValido(IO io) {
		this.io = io;
	}

	@Override
	public void esegui(Partita partita) {
		this.io.mostraMessaggio("Comando inesistente o non valido!");
	}

}