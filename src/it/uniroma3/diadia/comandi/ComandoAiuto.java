package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando {
	private IO ioc;
	
	public ComandoAiuto() {
		this.ioc = new IOConsole();
	}
	
	public ComandoAiuto(IO ioc) {
		this.ioc = ioc;
	}
	
	@Override
	public void esegui(Partita partita) {
		StringBuilder aiuti = new StringBuilder();
		
		for (String cmd : AbstractComando.getComandiDisponibili()) {

			if (!cmd.equals("nonvalido") && !cmd.equals("sconosciuto")) {
				aiuti.append(cmd).append(" ");
			}
		}
		this.ioc.mostraMessaggio("Comandi disponibili: " + aiuti.toString());
	}
}