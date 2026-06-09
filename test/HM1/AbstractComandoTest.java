package HM1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.comandi.AbstractComando;

public class AbstractComandoTest {

	private AbstractComando comandoAstratto;

	@Before
	public void setUp() {
		comandoAstratto = new AbstractComando() {
			@Override
			public void esegui(Partita partita) {
			}

			@Override
			public String getNome() {
				return "comandoFake";
			}
		};
	}

	@Test
	public void testSetEGetParametro() {
		this.comandoAstratto.setParametro("nord");
		assertEquals("nord", this.comandoAstratto.getParamtro());
	}

	@Test
	public void testParametroNulloDiDefault() {
		assertNull(this.comandoAstratto.getParamtro());
	}
}