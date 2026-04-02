package HM1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LabirintoTest {
	
	private Labirinto labirinto;
	
	@BeforeEach
	public void setUp() {
		this.labirinto = new Labirinto();
	}
	@Test
	void testStanzaInizialeCorretta() {
		//Verifico che l'atrio sia la stanza di partenza
		Stanza partenza = this.labirinto.getStanzaCorrente();
		
		assertNotNull(partenza); //verifico esista partenza
		assertEquals("Atrio", partenza.getNome());
	}

	@Test
	void testStanzaVincenteCorretta() {
		Stanza arrivo = this.labirinto.getStanzaVincente();
		assertNotNull(arrivo);
		assertEquals("Biblioteca", arrivo.getNome());
		
	}

}
