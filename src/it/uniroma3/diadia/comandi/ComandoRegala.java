package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoRegala extends AbstractComando {

	private IO io;

	public ComandoRegala() {
		this.io = new IOConsole();
	}
	
	public ComandoRegala(IO io) {
		this.io = io;
	}

	@Override
	public void esegui(Partita partita) {
		String nomeAttrezzo = this.getParamtro();
		
		if (nomeAttrezzo == null) {
			this.io.mostraMessaggio("Cosa vuoi regalare? Specifica un attrezzo.");
			return;
		}
		
		AbstractPersonaggio personaggio = partita.getLabirinto().getStanzaCorrente().getPersonaggio();
		if (personaggio == null) {
			this.io.mostraMessaggio("Non c'e' nessuno a cui regalare qualcosa qui!");
			return;
		}
		
		if (!partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo)) {
			this.io.mostraMessaggio("Non hai questo attrezzo nella borsa!");
			return;
		}
		
		Attrezzo attrezzoDaRegalare = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
		partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
		
		String risposta = personaggio.riceviRegalo(attrezzoDaRegalare, partita);
		this.io.mostraMessaggio(risposta);
	}

}