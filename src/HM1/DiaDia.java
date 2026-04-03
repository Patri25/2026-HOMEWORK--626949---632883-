package HM1;


import java.util.Scanner;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};

	private Partita partita;
	private Giocatore giocatore;
	private Labirinto labiritno;

	public DiaDia() {
		this.partita = new Partita();
	}

	public void gioca() {
		String istruzione; 
		Scanner scannerDiLinee;

		System.out.println(MESSAGGIO_BENVENUTO);
		scannerDiLinee = new Scanner(System.in);		
		do		
			istruzione = scannerDiLinee.nextLine();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		
		
		Comando comandoDaEseguire = new Comando(istruzione);
		
		if (comandoDaEseguire.sconosciuto()) { //Se comando vuoto o non riconosciuto da la possibilità di scriverne in altro senza crashare
	        return false; 
	    }

		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if(comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		else if(comandoDaEseguire.getNome().equals("posa"))
			this.posa(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else
			System.out.println("Comando sconosciuto");
		if (this.partita.vinta()) {
			System.out.println("Hai vinto!");
			return true;
		} else
			return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			System.out.print(elencoComandi[i]+" ");
		System.out.println();
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			System.out.println("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.labiritno.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			System.out.println("Direzione inesistente");
		else {
			this.labiritno.setStanzaCorrente(prossimaStanza);
			int cfu = this.giocatore.getCfu();
			this.giocatore.setCfu(cfu-1);
		}
		System.out.println(labiritno.getStanzaCorrente().getDescrizione());
	}
	//Cerca di prendere un attrezzo dalla stanza e metterlo nella borsa
	private void prendi(String nomeAttrezzo) {
		//se  l'utente non specifica cosa prende
		if(nomeAttrezzo == null) {
			System.out.println("Prendi cosa? Specifica un oggetto. ;)");
			return;
		}
		
		//controllo se l'oggetto è presente nella stanza
		Stanza stanzaCorrente = this.partita.getStanzaCorrente();
		Borsa borsa = this.partita.getGiocatore().getBorsa();
		if(!stanzaCorrente.hasAttrezzo(nomeAttrezzo)) {
			System.out.println("L'attrezzo non è presente in questa stanza. :(");
			return;
		}
		
		//prendo l'attrezzo
		Attrezzo attrezzoDaPrendere = stanzaCorrente.getAttrezzo(nomeAttrezzo);
		//provo a metterlo in borsa (potrebbe essere troppo pesante)
		boolean aggiuntoInBorsa = borsa.addAttrezzo(attrezzoDaPrendere);
		
		if(aggiuntoInBorsa) {
			stanzaCorrente.removeAttrezzo(attrezzoDaPrendere);
			System.out.println("Hai preso: "+ nomeAttrezzo);
		} else {
			System.out.println("Non puoi prendere "+ nomeAttrezzo+ ", non entra nella borsa!");
		}
	}
	
	//tolgo un oggetto dalla borsa e lo poggio nella stanza
	private void posa(String nomeAttrezzo) {
		//se  l'utente non specifica cosa prende
				if(nomeAttrezzo == null) {
					System.out.println("Posi cosa? Specifica un oggetto. ;)");
					return;
				}
				
				//controllo se l'oggetto è presente nella borsa
				Stanza stanzaCorrente = this.partita.getStanzaCorrente();
				Borsa borsa = this.partita.getGiocatore().getBorsa();
				if(!borsa.hasAttrezzo(nomeAttrezzo)) {
					System.out.println("L'attrezzo non è presente nella tua borsa. :(");
					return;
				}
				
				//prendo l'attrezzo
				Attrezzo attrezzoDaPosare = borsa.getAttrezzo(nomeAttrezzo);
				//provo a lasciarlo nella stanza(potrebbero già esserci 10 attrezzi che è il massimo)
				boolean aggiuntoInStanza = stanzaCorrente.addAttrezzo(attrezzoDaPosare);
				
				if(aggiuntoInStanza) {
					borsa.removeAttrezzo(nomeAttrezzo);
					System.out.println("Hai posato: "+ nomeAttrezzo);
				} else {
					System.out.println("Non puoi posare attrezzi in questa stanza, è già piena!");
				}
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		System.out.println("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}
}