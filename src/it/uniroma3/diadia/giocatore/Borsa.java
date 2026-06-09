package it.uniroma3.diadia.giocatore;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.Configurazione;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Map<String, Attrezzo> attrezzi;
    private int pesoMax;
    public Borsa() {
		// Prima era: this(10); oppure this.pesoMax = 10;
		this.pesoMax = Configurazione.getPesoMaxBorsa(); 
		this.attrezzi = new HashMap<>(); 
	}
	
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
        this.attrezzi = new HashMap<>();
	}
	
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax()) {
            return false;
		}
		this.attrezzi.put(attrezzo.getNome(), attrezzo);
        return true;
	}
	
	public int getPesoMax() {
		return this.pesoMax;
	}
	
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}
	
	public int getPeso() {
			int peso = 0;
			for (Attrezzo a : this.attrezzi.values()) {
	            peso += a.getPeso();
	        }
	        return peso;
		}
	
		public boolean isEmpty() {
			return this.attrezzi.isEmpty();
		}
		
		public boolean hasAttrezzo(String nomeAttrezzo) {
			return this.attrezzi.containsKey(nomeAttrezzo);
		}
		
		public Attrezzo removeAttrezzo(String nomeAttrezzo) {
			return this.attrezzi.remove(nomeAttrezzo);
		}
		
		public List<Attrezzo> getContenutoOrdinatoPerPeso() {
			List<Attrezzo> risultato = new ArrayList<>(this.attrezzi.values());
			risultato.sort(Comparator.comparingInt(Attrezzo::getPeso).thenComparing(Attrezzo::getNome));
			return risultato;
		}
		
		public SortedSet<Attrezzo> getContenutoOrdinatoPerNome() {
			SortedSet<Attrezzo> risultato = new TreeSet<>(Comparator.comparing(Attrezzo::getNome));
			risultato.addAll(this.attrezzi.values());
			return risultato;
		}

		public Map<Integer, Set<Attrezzo>> getContenutoRaggruppatoPerPeso() {
			Map<Integer, Set<Attrezzo>> risultato = new HashMap<>();
			for (Attrezzo a : this.attrezzi.values()) {
				if (!risultato.containsKey(a.getPeso())) {
					risultato.put(a.getPeso(), new HashSet<>());
				}
				risultato.get(a.getPeso()).add(a);
			}
			return risultato;
		}
		
		
		public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso() {
			SortedSet<Attrezzo> risultato = new TreeSet<>(Comparator.comparingInt(Attrezzo::getPeso).thenComparing(Attrezzo::getNome));
			risultato.addAll(this.attrezzi.values());
			return risultato;
		}
		
		public String toString() {
	        StringBuilder s = new StringBuilder();
	        if (!this.isEmpty()) {
	            s.append("Contenuto borsa (");
	            s.append(this.getPeso()).append("kg/");
	            s.append(this.getPesoMax()).append("kg): ");
	            
	            for (Attrezzo a : this.attrezzi.values()) {
	                s.append(a.toString()).append(" ");
	            }
	        } else {
	            s.append("Borsa vuota");
	        }
	        return s.toString();
	    }
}
