package HM1;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;

public class SimulazionePartitaTest {

	@Test
	public void testPartitaVintaImmediatamente() {
		Labirinto labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Biblioteca")
				.addStanzaVincente("Biblioteca")
				.getLabirinto();
		
		List<String> comandi = new ArrayList<>();
		comandi.add("guarda");
		
		IOSimulator io = new IOSimulator(comandi);
		DiaDia gioco = new DiaDia(labirinto, io);
		
		gioco.gioca();
		
		assertTrue("Il gioco dovrebbe comunicare la vittoria", io.contieneMessaggio("Hai vinto!"));
	}

	@Test
	public void testPartitaVintaConUnMovimento() {
		Labirinto labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", "nord")
				.getLabirinto();
		
		List<String> comandi = new ArrayList<>();
		comandi.add("vai nord");
		
		IOSimulator io = new IOSimulator(comandi);
		DiaDia gioco = new DiaDia(labirinto, io);
		
		gioco.gioca();
		
		assertTrue(io.contieneMessaggio("Hai vinto!"));
	}
	
	@Test
	public void testPartitaTerminataConComandoFine() {
		Labirinto labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addStanzaVincente("Biblioteca")
				.getLabirinto();
		
		List<String> comandi = new ArrayList<>();
		comandi.add("fine");
		
		IOSimulator io = new IOSimulator(comandi);
		DiaDia gioco = new DiaDia(labirinto, io);
		
		gioco.gioca();
		
		assertTrue(io.contieneMessaggio("Grazie di aver giocato!"));
		assertFalse("Non dovrebbe aver vinto", io.contieneMessaggio("Hai vinto!"));
	}
}