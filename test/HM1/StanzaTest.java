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
		this.stanza.impostaStanzaAdiacente("Sopra", stanzaAdiacente);
		
		//Confronto che queste corrispondano
		assertEquals(stanzaAdiacente, this.stanza.getStanzaAdiacente("Sopra"));

	}
	
	@Test
	void testImpostaStanzaAdiacenteDirezioniPiene() {
		//Verifico che una volta superato il numero massimo di stanze vicine non venga inserita quella in eccesso
		
		//1
		this.stanza.impostaStanzaAdiacente("Sopra", stanzaAdiacente);
		
		
		//2
		Stanza stanzaSotto=new Stanza("n13");
		this.stanza.impostaStanzaAdiacente("Sotto", stanzaSotto);
		
		//3
		Stanza stanzaDestra=new Stanza("n14");
		this.stanza.impostaStanzaAdiacente("Destra", stanzaDestra);
		
		//4
		Stanza stanzaSinistra=new Stanza("n15");
		this.stanza.impostaStanzaAdiacente("Sinistra", stanzaSinistra);
		
		//5!!
		Stanza stanzaExtra=new Stanza("n16");
		this.stanza.impostaStanzaAdiacente("Extra", stanzaExtra);
		
		//Mi aspetto che non trovi nulla (NULL) cercando la stanza "Extra"
		assertNull(this.stanza.getStanzaAdiacente("Extra"));

	}
	
	@Test
	void testImpostaStanzaAdiacenteCambioDiStanza() {
		this.stanza.impostaStanzaAdiacente("Sopra", stanzaAdiacente);
		
		Stanza stanzaSostitutiva =new Stanza("n17");
		
		//Sostituzione
		this.stanza.impostaStanzaAdiacente("Sopra", stanzaSostitutiva);
		
		//Verifica sostituzione
		assertEquals(stanzaSostitutiva, this.stanza.getStanzaAdiacente("Sopra"));
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
	
	@Test
	void testAddAttrezzoMassimoAttrezzi() {//il massimo è di 10 attrezzi.
		//1
		this.stanza.addAttrezzo(attrezzo);
		
		//2
		Attrezzo a2= new Attrezzo("Osso", 1);
		this.stanza.addAttrezzo(a2);
		
		//3
		Attrezzo a3= new Attrezzo("Spada", 6);
		this.stanza.addAttrezzo(a3);
		
		//4
		Attrezzo a4= new Attrezzo("Scudo", 8);
		this.stanza.addAttrezzo(a4);
		
		//5
		Attrezzo a5= new Attrezzo("Torcia", 3);
		this.stanza.addAttrezzo(a5);
		
		//6
		Attrezzo a6= new Attrezzo("shuriken", 4);
		this.stanza.addAttrezzo(a6);
		
		//7
		Attrezzo a7=new Attrezzo("Pala",5);
		this.stanza.addAttrezzo(a7);
		
		//8
		Attrezzo a8=new Attrezzo("Accendino",1);
		this.stanza.addAttrezzo(a8);
		
		//9
		Attrezzo a9=new Attrezzo("Bastone",4);
		this.stanza.addAttrezzo(a9);
		
		//10
		Attrezzo a10=new Attrezzo("Piccone",6);
		this.stanza.addAttrezzo(a10);
		
		//11!!
		Attrezzo a11=new Attrezzo("Livella",3);
		this.stanza.addAttrezzo(a11);
		
		//Mi aspetto non ci sia tra gli attrezzi
		assertFalse(this.stanza.hasAttrezzo("Livella"));
	}
	
	//----------------GETATTREZZO,I 3 TEST---------------------
	//Da fare
	
	//----------------REMOVEATTREZZO,I 3 TEST---------------------
	//Da fare


}
