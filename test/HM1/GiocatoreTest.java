package HM1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//bisogna ancora creare la classe giocatore per questo riempie di errori
class GiocatoreTest {
	private Giocatore giocatore; 
	
	@BeforeEach
	public void setUp() {
		this.giocatore = new Giocatore();
	}
	
	@Test
	void testCFUIniziali() {
		//verifico che i CFU iniziali del giocatore siano 20
		assertEquals(20, this.giocatore.getCfu());
	}
	
	@Test
	void testBorsaEsiste() {
		//verifico la presenza di una borsa dopo la creazione del giocatore
		assertNotNull(this.giocatore.getBorsa());
	}
	
	@Test
	void testCambioCFU() {
		//verifico se, usato setCFU, i CFU risultano cambiati
		this.giocatore.setCfu(5);
		assertEquals(5, this.giocatore.getCfu());
	}

}
