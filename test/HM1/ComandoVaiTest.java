package HM1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.comandi.ComandoVai;

public class ComandoVaiTest {

	private Partita partita;
	private ComandoVai comando;

	@Before
	public void setUp() {
		this.comando = new ComandoVai();
	}

	@Test
	public void testVaiInMonolocale() {
		Labirinto monolocale = new LabirintoBuilder()
				.addStanzaIniziale("Salotto")
				.getLabirinto();
		this.partita = new Partita(monolocale);
		
		this.comando.setParametro("nord");
		this.comando.esegui(this.partita);
		
		assertEquals("Salotto", this.partita.getStanzaCorrente().getNome());
	}


	@Test
	public void testVaiInBilocaleDirezioneSbagliata() {
		Labirinto bilocale = new LabirintoBuilder()
				.addStanzaIniziale("Salotto")
				.addStanza("Cucina")
				.addAdiacenza("Salotto", "Cucina", "nord")
				.getLabirinto();
		this.partita = new Partita(bilocale);
		
		this.comando.setParametro("sud");
		this.comando.esegui(this.partita);
		
		assertEquals("Salotto", this.partita.getStanzaCorrente().getNome());
	}

	@Test
	public void testVaiSenzaParametro() {
		Labirinto monolocale = new LabirintoBuilder()
				.addStanzaIniziale("Salotto")
				.getLabirinto();
		this.partita = new Partita(monolocale);
		
		this.comando.esegui(this.partita);
		
		assertEquals("Salotto", this.partita.getStanzaCorrente().getNome());
	}
}