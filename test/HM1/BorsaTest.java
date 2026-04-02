package HM1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BorsaTest {
	private Borsa borsa;
	
	@BeforeEach
	public void setUp() {
		this.borsa = new Borsa();
	}
	
	@Test
	public void testAttrezzo() {
		//verifico funzioni correttamente l'aggiunta di un attrezzo
		Attrezzo spillatrice = new Attrezzo("spillatrice", 1);
		assertTrue(this.borsa.addAttrezzo(spillatrice));
		assertTrue(this.borsa.hasAttrezzo("spillatrice"));
	}
	
	@Test
	public void testAttrezzoTroppoPesante() {
		//verifico che succede aggiungendo un oggetto troppo pesante
		Attrezzo incudine = new Attrezzo("Incudine", 20);
		boolean risultato = this.borsa.addAttrezzo(incudine);
		assertFalse(risultato);
		assertFalse(this.borsa.hasAttrezzo("Incudine"));
	}
	
	@Test
	public void testAddAttrezzoMassimoAttrezzi() {//il massimo è di 10 attrezzi.

		for (int i = 1; i <= 10; i++) {
	        Attrezzo a = new Attrezzo("a" + i, 1); // Nome dinamico: a1,a2,...
	        this.borsa.addAttrezzo(a);
	    }
		
		//11!!!
		Attrezzo a11=new Attrezzo("Livella",3);
		this.borsa.addAttrezzo(a11);
		
		//Mi aspetto non ci sia tra gli attrezzi
		assertFalse(this.borsa.hasAttrezzo("Livella"));
	}
	
	@Test
	public void testPeso() {
		//verifico che la borsa aggiunga in modo corretto il peso supportato
		Attrezzo penna = new Attrezzo("penna", 1);
		Attrezzo martello = new Attrezzo("martello", 7);
		
		this.borsa.addAttrezzo(penna);
		this.borsa.addAttrezzo(martello);
		
		assertEquals(8, this.borsa.getPeso());
	}
	
	@Test
	public void testNome() {
		//verifico se cercando un attrezzo me lo restituisce correttamente
		Attrezzo flauto = new Attrezzo("flauto", 1);
		this.borsa.addAttrezzo(flauto);
		assertEquals(flauto, this.borsa.getAttrezzo("flauto"));
	}
	
}
