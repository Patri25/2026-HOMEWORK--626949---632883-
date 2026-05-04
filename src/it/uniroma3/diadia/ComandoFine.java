package it.uniroma3.diadia;

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

}
