package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPosa implements Tempcomando {
	private String nomeAttrezzo;
	private IOConsole ioc;
	private Partita partita;
	
	
	public ComandoPosa(IOConsole ioc) { 
        this.ioc = ioc;
    }
	
	@Override
	public void esegui(Partita partita) {
		this.partita=partita;

		//se  l'utente non specifica cosa prende
				if(nomeAttrezzo == null) {
					this.ioc.mostraMessaggio("Posi cosa? Specifica un oggetto. ;)");
					return;
				}
				
				//controllo se l'oggetto è presente nella borsa
				Stanza stanzaCorrente = this.partita.getLabirinto().getStanzaCorrente();
				Borsa borsa = this.partita.getGiocatore().getBorsa();
				if(!borsa.hasAttrezzo(nomeAttrezzo)) {
					this.ioc.mostraMessaggio("L'attrezzo non è presente nella tua borsa. :(");
					return;
				}
				
				//prendo l'attrezzo
				Attrezzo attrezzoDaPosare = borsa.getAttrezzo(nomeAttrezzo);
				//provo a lasciarlo nella stanza(potrebbero già esserci 10 attrezzi che è il massimo)
				boolean aggiuntoInStanza = stanzaCorrente.addAttrezzo(attrezzoDaPosare);
				
				if(aggiuntoInStanza) {
					borsa.removeAttrezzo(nomeAttrezzo);
					this.ioc.mostraMessaggio("Hai posato: "+ nomeAttrezzo);
				} else {
					this.ioc.mostraMessaggio("Non puoi posare attrezzi in questa stanza, è già piena!");
				}
		
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo=parametro;
		
	}

}
