package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.Tempcomando;

public class ComandoAiuto implements Tempcomando {
	private IO ioc;
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};
	
	
	public ComandoAiuto(IO ioc) {
        this.ioc = ioc;
    }
	
	@Override
	public void esegui(Partita partita) {

		for(int i=0; i< elencoComandi.length; i++) 
			this.ioc.mostraMessaggio(elencoComandi[i]+" ");
		this.ioc.mostraMessaggio(""); 
		
	}

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getNome() {
		return ("aiuto");
	}

	@Override
	public String getParamtro() {
		return null;
	}

}
