package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.Tempcomando;

public class ComandoFine implements Tempcomando {
	private Partita partita;
	private IOConsole ioc;
	
	public ComandoFine(IOConsole ioc) {
        this.ioc = ioc;
    }

	@Override
	public void esegui(Partita partita) {
		this.partita=partita;
		this.ioc.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
		partita.setFinita();
		
	}

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getNome() {
		return("fine");
	}

	@Override
	public String getParamtro() {
		return null;
	}

}
