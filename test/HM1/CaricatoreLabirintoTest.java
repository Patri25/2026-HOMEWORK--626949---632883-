package HM1;

import static org.junit.Assert.*;

import java.io.StringReader;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.CaricatoreLabirinto;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

public class CaricatoreLabirintoTest {

	@Test
	public void testCaricatoreMonolocale() {
		// Fixture stringa
		String specifica = 
				"Stanze: Salotto\n" +
				"Estremi: Salotto Salotto\n" +
				"Attrezzi:\n" +
				"Uscite:\n";
		
		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(specifica));
		caricatore.carica();
		Labirinto labirinto = caricatore.getLabirinto();
		
		// Verifiche
		assertEquals("Salotto", labirinto.getStanzaCorrente().getNome());
		assertEquals("Salotto", labirinto.getStanzaVincente().getNome());
	}

	@Test
	public void testCaricatoreBilocale() {
		String specifica = 
				"Stanze: N10 Biblioteca\n" +
				"Estremi: N10 Biblioteca\n" +
				"Attrezzi: Osso 5 N10\n" +
				"Uscite: N10 nord Biblioteca\n";
		
		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(specifica));
		caricatore.carica();
		Labirinto labirinto = caricatore.getLabirinto();
		
		// Verifica stanze
		assertEquals("N10", labirinto.getStanzaCorrente().getNome());
		assertEquals("Biblioteca", labirinto.getStanzaVincente().getNome());
		
		// Verifica uscita
		Stanza adiacente = labirinto.getStanzaCorrente().getStanzaAdiacente("nord");
		assertNotNull(adiacente);
		assertEquals("Biblioteca", adiacente.getNome());
		
		// Verifica attrezzo
		assertTrue(labirinto.getStanzaCorrente().hasAttrezzo("Osso"));
	}

	@Test
	public void testCaricatoreTrilocaleConPiuAttrezzi() {
		String specifica = 
				"Stanze: Ingresso Cucina Giardino\n" +
				"Estremi: Ingresso Giardino\n" +
				"Attrezzi: Coltello 2 Cucina Pala 5 Giardino\n" +
				"Uscite: Ingresso nord Cucina Cucina ovest Giardino\n";
		
		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(new StringReader(specifica));
		caricatore.carica();
		Labirinto labirinto = caricatore.getLabirinto();
		
		assertEquals("Ingresso", labirinto.getStanzaCorrente().getNome());
		assertEquals("Giardino", labirinto.getStanzaVincente().getNome());
		
		Stanza cucina = labirinto.getStanzaCorrente().getStanzaAdiacente("nord");
		assertEquals("Cucina", cucina.getNome());
		assertTrue(cucina.hasAttrezzo("Coltello"));
		
		Stanza giardino = cucina.getStanzaAdiacente("ovest");
		assertTrue(giardino.hasAttrezzo("Pala"));
	}
}