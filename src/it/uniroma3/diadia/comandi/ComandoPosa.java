package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPosa extends AbstractComando {
	private IO ioc;
	private Partita partita;
	
	public ComandoPosa() {
		this.ioc = new IOConsole();
	}
	
	public ComandoPosa(IO ioc) { 
		this.ioc = ioc;
	}
	
	@Override
	public void esegui(Partita partita) {
		this.partita = partita;
		String nomeAttrezzo = this.getParamtro(); 


		if(nomeAttrezzo == null) {
			this.ioc.mostraMessaggio("Posi cosa? Specifica un oggetto. ;)");
			return;
		}
		

		Stanza stanzaCorrente = this.partita.getLabirinto().getStanzaCorrente();
		Borsa borsa = this.partita.getGiocatore().getBorsa();
		if(!borsa.hasAttrezzo(nomeAttrezzo)) {
			this.ioc.mostraMessaggio("L'attrezzo non è presente nella tua borsa. :(");
			return;
		}
		

		Attrezzo attrezzoDaPosare = borsa.getAttrezzo(nomeAttrezzo);

		boolean aggiuntoInStanza = stanzaCorrente.addAttrezzo(attrezzoDaPosare);
		
		if(aggiuntoInStanza) {
			borsa.removeAttrezzo(nomeAttrezzo);
			this.ioc.mostraMessaggio("Hai posato: "+ nomeAttrezzo);
		} else {
			this.ioc.mostraMessaggio("Non puoi posare attrezzi in questa stanza, è già piena!");
		}
	}

}