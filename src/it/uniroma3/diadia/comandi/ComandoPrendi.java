package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPrendi extends AbstractComando {
	private Partita partita;
	private IO ioc;
	
	public ComandoPrendi() {
		this.ioc = new IOConsole();
	}
	
	public ComandoPrendi(IO ioc) {
		this.ioc = ioc;
	}
	
	@Override
	public void esegui(Partita partita) {
		this.partita = partita;
		String nomeAttrezzo = this.getParamtro(); 


		if(nomeAttrezzo == null) {
			this.ioc.mostraMessaggio("Prendi cosa? Specifica un oggetto. ;)");
			return;
		}
		

		Stanza stanzaCorrente = this.partita.getLabirinto().getStanzaCorrente(); 
		Borsa borsa = this.partita.getGiocatore().getBorsa();
		if(!stanzaCorrente.hasAttrezzo(nomeAttrezzo)) {
			this.ioc.mostraMessaggio("L'attrezzo non è presente in questa stanza. :(");
			return;
		}
		

		Attrezzo attrezzoDaPrendere = stanzaCorrente.getAttrezzo(nomeAttrezzo);

		boolean aggiuntoInBorsa = borsa.addAttrezzo(attrezzoDaPrendere);
		
		if(aggiuntoInBorsa) {
			stanzaCorrente.removeAttrezzo(attrezzoDaPrendere);
			this.ioc.mostraMessaggio("Hai preso: "+ nomeAttrezzo);
		} else {
			this.ioc.mostraMessaggio("Non puoi prendere "+ nomeAttrezzo+ ", non entra nella borsa!");
		}
	}

}