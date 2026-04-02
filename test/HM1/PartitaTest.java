package HM1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PartitaTest {
	
	private Partita partita;
	private Stanza stanzaVincente;
	private Labirinto labirinto;
	private Stanza stanzaCorrente;
	private Giocatore giocatore;
	
	
	@BeforeEach
	public void setUp() {
		partita = new Partita();
		labirinto = new Labirinto();
		giocatore=new Giocatore();
		stanzaVincente=this.labirinto.getStanzaVincente();
		stanzaCorrente=this.labirinto.getStanzaCorrente();
	}
	

	//---------------------VINTA, I 3 TEST------------------------

	@Test
	void testVintaCorrispondenteAllaVittoria() {
		this.labirinto.setStanzaCorrente(this.stanzaVincente);
		assertTrue(this.partita.vinta());
	}
	
	@Test
	void testVintaNonCorrispondenteAllaVittoria() {
		assertFalse(this.partita.vinta());
	}
	
	@Test
	void testVintaAncheSeNonStanzaInizialeMaNonCorretta() {
		Stanza stanzaCasuale=new Stanza("NInesistente");
		this.labirinto.setStanzaCorrente(stanzaCasuale);
		assertFalse(this.partita.vinta());
	}
	
	//---------------------ISFINITA, I 3 TEST------------------------
	//Controlla se la stanza è giusta, il numero di cfu e se toran true la funziona "vinta()".
	@Test
	void testIsFinitaCfuFiniti() {
		this.giocatore.setCfu(0);
		assertTrue(this.partita.isFinita());
		
		}
	
	@Test
	void testIsFinitaMetodoVinta() {
		this.labirinto.setStanzaCorrente(this.stanzaVincente);
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	void testIsFinitaManuale() {
		this.partita.setFinita();
	    assertTrue(this.partita.isFinita());
	}
}
