package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.Configurazione;

/* COSA DEVE FARE?
 * ha la responsabilità di gestire i CFU del giocatore e di
 * memorizzare gli attrezzi in un oggetto istanza della classe Borsa
 */
public class Giocatore {
	
	static final private int CFU_INIZIALI = 20;
	private int cfu;
	private Borsa borsa;
	
	public Giocatore() {

		this.cfu = Configurazione.getCFU(); 
		this.borsa = new Borsa();
	}
	
	public Borsa getBorsa() {
		return this.borsa;
	}
	
	public void setBorsa(Borsa borsaNuova) {
		this.borsa=borsaNuova;
	}
	
	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}
}
