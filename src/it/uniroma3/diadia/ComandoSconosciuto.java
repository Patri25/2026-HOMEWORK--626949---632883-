package it.uniroma3.diadia;

public class ComandoSconosciuto implements Tempcomando{
	private Partita partita;
	private IOConsole ioc;
	
	public ComandoSconosciuto(IOConsole ioc) {
		this.ioc=ioc;
	}
	@Override
	public void esegui(Partita partita) {
		this.partita=partita;
		this.ioc.mostraMessaggio("Comando sconosciuto");
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub
		
	}

}
