package HM1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

class BorsaTest {
	private Borsa borsa;
	private Attrezzo piuma;
	private Attrezzo libro;
	private Attrezzo piombo;
	
	@BeforeEach
	public void setUp() {
		this.borsa = new Borsa();
		this.piuma = new Attrezzo("piuma", 1);
		this.libro = new Attrezzo("libro", 3);
		this.piombo = new Attrezzo("piombo", 3);
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
	
	@Test
	public void testGetSortedSetOrdinatoPerPeso_StessoPesoNomeDiverso() {

		Attrezzo osso = new Attrezzo("osso", 2);
		Attrezzo ferro = new Attrezzo("ferro", 2);
		
		this.borsa.addAttrezzo(osso);
		this.borsa.addAttrezzo(ferro);
		
		SortedSet<Attrezzo> ordinati = this.borsa.getSortedSetOrdinatoPerPeso();
		
		assertEquals(2, ordinati.size());
		
		Iterator<Attrezzo> it = ordinati.iterator();
		assertEquals("ferro", it.next().getNome()); 
		assertEquals("osso", it.next().getNome());
	}
	
	@Test
	public void testGetContenutoOrdinatoPerPeso() {
		this.borsa.addAttrezzo(piombo);
		this.borsa.addAttrezzo(piuma);
		this.borsa.addAttrezzo(libro);

		List<Attrezzo> lista = this.borsa.getContenutoOrdinatoPerPeso();
		assertEquals(3, lista.size());
		assertEquals(this.piuma, lista.get(0));
		assertEquals(this.libro, lista.get(1));
		assertEquals(this.piombo, lista.get(2));
	}

	@Test
	public void testGetContenutoOrdinatoPerNome() {
		this.borsa.addAttrezzo(piombo);
		this.borsa.addAttrezzo(piuma);
		this.borsa.addAttrezzo(libro);

		SortedSet<Attrezzo> set = this.borsa.getContenutoOrdinatoPerNome();
		assertEquals(3, set.size());
		Iterator<Attrezzo> it = set.iterator();
		assertEquals(this.libro, it.next());
		assertEquals(this.piombo, it.next());
		assertEquals(this.piuma, it.next());
	}

	@Test
	public void testGetContenutoRaggruppatoPerPeso() {
		this.borsa.addAttrezzo(piombo); 
		this.borsa.addAttrezzo(piuma);  
		this.borsa.addAttrezzo(libro);  

		Map<Integer, Set<Attrezzo>> mappa = this.borsa.getContenutoRaggruppatoPerPeso();
		
		assertTrue(mappa.containsKey(1), "Manca la chiave 1");
		assertTrue(mappa.containsKey(3), "Manca la chiave 3");
		
		assertEquals(1, mappa.get(1).size());
		assertTrue(mappa.get(1).contains(this.piuma));
		
		assertTrue(mappa.get(3).contains(this.libro));
		assertTrue(mappa.get(3).contains(this.piombo));
	}
	
	
	
}
