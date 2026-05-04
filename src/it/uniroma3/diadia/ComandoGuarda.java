package it.uniroma3.diadia;

public class ComandoGuarda implements Tempcomando{
	private Partita partita;
	private IOConsole ioc;
	
	public ComandoGuarda(IOConsole ioc) {
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

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub
		
	}

}
