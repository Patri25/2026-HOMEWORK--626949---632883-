package HM1;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilderTest {

	private LabirintoBuilder builder;

	@Before
	public void setUp() {
		this.builder = new LabirintoBuilder();
	}

	@Test
	public void testMonolocale() {
		Labirinto monolocale = builder
				.addStanzaIniziale("salotto")
				.addStanzaVincente("salotto")
				.getLabirinto();
		
		assertEquals("salotto", monolocale.getStanzaCorrente().getNome());
		assertEquals("salotto", monolocale.getStanzaVincente().getNome());
		// Verifica che stanza corrente e vincente puntino esattamente allo stesso oggetto
		assertSame(monolocale.getStanzaCorrente(), monolocale.getStanzaVincente());
	}

	@Test
	public void testBilocaleConAttrezzo() {
		Labirinto bilocale = builder
				.addStanzaIniziale("salotto")
				.addStanzaVincente("camera")
				.addAttrezzo("letto", 10) // Viene messo nell'ultima stanza (camera)
				.addAdiacenza("salotto", "camera", "nord")
				.getLabirinto();
		
		Stanza iniziale = bilocale.getStanzaCorrente();
		Stanza vincente = bilocale.getStanzaVincente();
		
		assertEquals("salotto", iniziale.getNome());
		assertEquals("camera", vincente.getNome());
		
		// Verifica l'adiacenza
		assertEquals(vincente, iniziale.getStanzaAdiacente("nord"));
		
		// Verifica che l'attrezzo sia nella camera e non nel salotto
		assertTrue(vincente.hasAttrezzo("letto"));
		assertFalse(iniziale.hasAttrezzo("letto"));
	}

	@Test
	public void testTrilocaleConAdiacenze() {
		Labirinto trilocale = builder
				.addStanzaIniziale("salotto")
				.addStanza("cucina")
				.addAttrezzo("pentola", 1) // Inserito in cucina
				.addStanzaVincente("camera")
				.addAdiacenza("salotto", "cucina", "nord")
				.addAdiacenza("cucina", "camera", "est")
				.getLabirinto();
		
		Stanza iniziale = trilocale.getStanzaCorrente();
		Stanza cucina = iniziale.getStanzaAdiacente("nord");
		Stanza vincente = trilocale.getStanzaVincente();
		
		assertEquals("salotto", iniziale.getNome());
		assertEquals("cucina", cucina.getNome());
		assertEquals("camera", vincente.getNome());
		
		// Verifica adiacenza a cascata
		assertEquals(vincente, cucina.getStanzaAdiacente("est"));
		
		// Verifica l'attrezzo
		assertTrue(cucina.hasAttrezzo("pentola"));
	}
}