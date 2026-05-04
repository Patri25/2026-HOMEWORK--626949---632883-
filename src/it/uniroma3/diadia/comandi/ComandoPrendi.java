package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.Tempcomando;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPrendi implements Tempcomando {
	private Partita partita;
	private String nomeAttrezzo;
	private IOConsole ioc;
	
	public ComandoPrendi(IOConsole ioc) {
        this.ioc = ioc;
    }
	
	@Override
	public void esegui(Partita partita) {
		this.partita=partita;

		//se  l'utente non specifica cosa prende
		if(nomeAttrezzo == null) {
			this.ioc.mostraMessaggio("Prendi cosa? Specifica un oggetto. ;)");
			return;
		}
		
		//controllo se l'oggetto è presente nella stanza
		Stanza stanzaCorrente = this.partita.getLabirinto().getStanzaCorrente(); //!!!! STANZACORRENTE STA IN LABIRITNO NON IN PARTITA
		Borsa borsa = this.partita.getGiocatore().getBorsa();
		if(!stanzaCorrente.hasAttrezzo(nomeAttrezzo)) {
			this.ioc.mostraMessaggio("L'attrezzo non è presente in questa stanza. :(");
			return;
		}
		
		//prendo l'attrezzo
		Attrezzo attrezzoDaPrendere = stanzaCorrente.getAttrezzo(nomeAttrezzo);
		//provo a metterlo in borsa (potrebbe essere troppo pesante)
		boolean aggiuntoInBorsa = borsa.addAttrezzo(attrezzoDaPrendere);
		
		if(aggiuntoInBorsa) {
			stanzaCorrente.removeAttrezzo(attrezzoDaPrendere);
			this.ioc.mostraMessaggio("Hai preso: "+ nomeAttrezzo);
		} else {
			this.ioc.mostraMessaggio("Non puoi prendere "+ nomeAttrezzo+ ", non entra nella borsa!");
		}
		
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo=parametro;
	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return ("prendi");
	}

	@Override
	public String getParamtro() {
		// TODO Auto-generated method stub
		return nomeAttrezzo;
	}

}
