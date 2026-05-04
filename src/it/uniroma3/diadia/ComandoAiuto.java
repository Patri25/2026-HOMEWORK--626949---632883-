package it.uniroma3.diadia;

public class ComandoAiuto implements Tempcomando {
	private IOConsole ioc;
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};
	
	
	public ComandoAiuto(IOConsole ioc) {
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

}
