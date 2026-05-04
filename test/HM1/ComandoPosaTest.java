package HM1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ComandoPosa;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoPosaTest {
	private Partita partita;
	private IOConsole ioc;
	private ComandoPosa comandoPosa;
	private Attrezzo attrezzo;
	
	@BeforeEach
	public void setUp() {
		this.ioc=new IOConsole();
		this.partita = new Partita();
		this.attrezzo=new Attrezzo("manganello",2);
	}
	
	@Test
	void testNullaDaPosare() {
		comandoPosa = new ComandoPosa(ioc);
		comandoPosa.setParametro("manganello");
		comandoPosa.esegui(partita);
		
		//mi aspetto si non trovarlo nella stanza
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("manganello"));
	}
	
	@Test
	void testPosoAttrezzo() {
		comandoPosa = new ComandoPosa(ioc);
		this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
		comandoPosa.setParametro("manganello");
		comandoPosa.esegui(partita);
		
		//mi aspetto si non trovarlo nella stanza
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("manganello"));
	}
	
	@Test
	void testPosoNull() {
		comandoPosa = new ComandoPosa(ioc);
		comandoPosa.setParametro(null);
		comandoPosa.esegui(partita);
		
		//mi aspetto si non trovarlo nella stanza
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("manganello"));
	}

}
