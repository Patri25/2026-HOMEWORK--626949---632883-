package HM1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StanzaTest {
	
	private Stanza stanza;
	private Stanza stanzaAdiacente;
	private Attrezzo attrezzo;
	
	@BeforeEach
	public void setUp() {
		stanza= new Stanza("n11");
		stanzaAdiacente= new Stanza("n12");
		attrezzo = new Attrezzo("Falce", 6);
	}
	

	//---------------------IMPOSTASTANZAADIACENTE, I 3 TEST------------------------
	@Test
	void testImpostaStanzaAdiacente() {
		//Verifica che venga correttamente impostata la staza adiacente
		this.stanza.impostaStanzaAdiacente("nord", stanzaAdiacente);
		//Confronto che queste corrispondano
		assertEquals(stanzaAdiacente, this.stanza.getStanzaAdiacente("Sopra"));

	}
	
	@Test
	void testImpostaStanzaAdiacenteDirezioniPiene() {
		//Verifico che una volta superato il numero massimo di stanze vicine non venga inserita quella in eccesso
		
		//1
		this.stanza.impostaStanzaAdiacente("nord", stanzaAdiacente);
		
		
		//2
		Stanza stanzaSotto=new Stanza("n13");
		this.stanza.impostaStanzaAdiacente("sud", stanzaSotto);
		
		//3
		Stanza stanzaDestra=new Stanza("n14");
		this.stanza.impostaStanzaAdiacente("est", stanzaDestra);
		
		//4
		Stanza stanzaSinistra=new Stanza("n15");
		this.stanza.impostaStanzaAdiacente("ovest", stanzaSinistra);
		
		//5!!
		Stanza stanzaExtra=new Stanza("n16");
		this.stanza.impostaStanzaAdiacente("Extra", stanzaExtra);
		
		//Mi aspetto che non trovi nulla (NULL) cercando la stanza "Extra"
		assertNull(this.stanza.getStanzaAdiacente("Extra"));

	}
	
	@Test
	void testImpostaStanzaAdiacenteCambioDiStanza() {
		this.stanza.impostaStanzaAdiacente("nord", stanzaAdiacente);
		
		Stanza stanzaSostitutiva =new Stanza("n17");
		
		//Sostituzione
		this.stanza.impostaStanzaAdiacente("nord", stanzaSostitutiva);
		
		//Verifica sostituzione
		assertEquals(stanzaSostitutiva, this.stanza.getStanzaAdiacente("nord"));
	}
	
	//----------------ADDATTREZZO,I 3 TEST---------------------
	
	@Test
	void testAddAttrezzo() {//Verifico che venga effettivamente aggiunto un attrezzo con lo stesso nome
		this.stanza.addAttrezzo(attrezzo);
		assertTrue(this.stanza.hasAttrezzo("Falce"));
	}
	
	@Test
	void testAddAttrezziDistinti() {//Verifica che i diversi strumenti vengano aggiunti indipendentemente l'uni dagli altri
		this.stanza.addAttrezzo(attrezzo);
		
		Attrezzo a2=new Attrezzo("Pinsette",1);
		
		this.stanza.addAttrezzo(a2);
		
		assertTrue(this.stanza.hasAttrezzo("Falce") && this.stanza.hasAttrezzo("Pinsette"));
	}
	
	//funzione per generare 10 oggetti
	
	void genera10ogg() {
		
		arrayOggetti[] a = new 
		for(int i=0; i<10;i++) {
			Attrezzo a+1 = new 
		}
	}
	
	@Test
	void testAddAttrezzoMassimoAttrezzi() {//il massimo è di 10 attrezzi.

		for (int i = 1; i <= 10; i++) {
	        Attrezzo a = new Attrezzo("a" + i, 1); // Nome dinamico: a1,a2,...
	        this.stanza.addAttrezzo(a);
	    }
		
		//11!!
		Attrezzo a11=new Attrezzo("Livella",3);
		this.stanza.addAttrezzo(a11);
		
		//Mi aspetto non ci sia tra gli attrezzi
		assertFalse(this.stanza.hasAttrezzo("Livella"));
	}
	
	//----------------GETATTREZZO,I 3 TEST---------------------
	
	@Test
	public void testgetAttrezzoPresente() {//Verifico che trovi e ritorni un attrezzo presente nella stanza
		assertEquals(this.attrezzo, this.stanza.getAttrezzo("Falce"));
	}
	
	@Test
	public void testgetAttrezzoNonPresente() {//Verifico NON che trovi e ritorni un attrezzo NON presente nella stanza
		assertNull(this.stanza.getAttrezzo("Filtro"));
	}
	
	@Test
	public void testgetAttrezzoInseritoSuccessivamente() {//Verifico  che trovi e ritorni un attrezzo inserito successivamente nella stanza
		Attrezzo NuovoAttrezzo=new Attrezzo("Pistola",6);
		this.stanza.addAttrezzo(NuovoAttrezzo);
		assertEquals(NuovoAttrezzo,this.stanza.getAttrezzo("Pistola"));
	}
	
	//----------------REMOVEATTREZZO,I 3 TEST---------------------
	
	@Test
	public void removeAttrezzoPresente() {//Verifico che rimuova l'attrezzo
		this.stanza.removeAttrezzo("Falce");//L'errore è normale finchè non è implementata( E' uno dei prossimi punti dell'hw)
		assertFalse(this.stanza.hasAttrezzo("Falce"));
	}
	
	@Test
	public void removeAttrezzoNonPresente() {//Mi aspetto che torni FALSE (non ho rimosso l'attrezzo che mi hai passato perchè non ci stava)
		assertFalse(this.stanza.removeAttrezzo("Spellacavi"));
	}
	
	@Test
	public void removeAttrezzoSoloIlPrimoConStessoNome() {
		Attrezzo a2= new Attrezzo("Falce", 6);
		this.stanza.addAttrezzo(a2);
		this.stanza.removeAttrezzo("Falce");
		assertTrue(this.stanza.hasAttrezzo("Falce"));
	}

}
