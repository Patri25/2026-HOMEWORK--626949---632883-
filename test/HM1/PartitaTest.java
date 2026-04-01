package HM1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PartitaTest {
	
	private Partita partita;
	private Stanza stanzaVincente;
	private Stanza stanzaCorrente;
	
	
	@BeforeEach
	public void setUp() {
		partita = new Partita();
		stanzaVincente=this.partita.getStanzaVincente();
		stanzaCorrente=this.partita.getStanzaCorrente();
	}
	

	//---------------------VINTA, I 3 TEST------------------------

	@Test
	void testVintaCorrispondenteAllaVittoria() {
		this.partita.setStanzaCorrente(stanzaVincente);
		assertTrue(this.partita.vinta());
	}
	
	@Test
	void testVintaNonCorrispondenteAllaVittoria() {
		assertFalse(this.partita.vinta());
	}
	
	@Test
	void testVintaAncheSeNonStanzaInizialeMaNonCorretta() {
		Stanza stanzaCasuale=new Stanza("NInesistente");
		this.partita.setStanzaCorrente(stanzaCasuale);
		assertFalse(this.partita.vinta());
	}
	
	//---------------------ISFINITA, I 3 TEST------------------------
	//Controlla se la stanza è giusta, il numero di cfu e se toran true la funziona "vinta()".
}
