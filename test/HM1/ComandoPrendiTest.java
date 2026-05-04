package HM1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ComandoPosa;
import it.uniroma3.diadia.ComandoPrendi;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoPrendiTest {
	
	private Partita partita;
	private Attrezzo attrezzo;
	private ComandoPrendi comandoPrendi;
	private IOConsole ioc;
	
	
	@BeforeEach
	public void setUp() {
		this.ioc=new IOConsole();
		this.partita = new Partita();
		this.attrezzo = new Attrezzo("manganello", 2);
	}
	
	@Test
	void testAttrezzoNonInStanza() {
		//NON aggiungo attrezzo in stanza
		//this.partita.getStanzaCorrente().addAttrezzo(attrezzo);
		comandoPrendi = new ComandoPrendi(ioc);
		comandoPrendi.setParametro("manganello");
		comandoPrendi.esegui(partita);
		
		//Mi aspetto che in borsa non abbia l'ogg.
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("manganello"));
	}
	
	@Test
	void testAttrezzoPresoDaStanza() {
		//aggiungo attrezzo in stanza
		this.partita.getStanzaCorrente().addAttrezzo(attrezzo);
		comandoPrendi = new ComandoPrendi(ioc);
		comandoPrendi.setParametro("manganello");
		comandoPrendi.esegui(partita);
		
		//Mi aspetto che in borsa abbia l'ogg.
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo("manganello"));
	}
	
	@Test
	void testAttrezzoRimossoStanzaDopoPrend() {
		//aggiungo attrezzo in stanza
	    this.partita.getStanzaCorrente().addAttrezzo(attrezzo);
	    comandoPrendi = new ComandoPrendi(ioc);
	    comandoPrendi.setParametro("manganello");
	    comandoPrendi.esegui(partita);
	    
	    // dopo averlo preso non deve più essere in stanza
	    assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("manganello"));
	}

	@Test
	void testParametroNull() {
	    comandoPrendi = new ComandoPrendi(ioc);
	    comandoPrendi.setParametro(null);
	    comandoPrendi.esegui(partita);
	    
	    // borsa deve restare vuota
	    assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("manganello"));
	}

}
