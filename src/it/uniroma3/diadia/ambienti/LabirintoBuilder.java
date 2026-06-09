package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class LabirintoBuilder {
	
	private Labirinto labirinto;
	private Map<String, Stanza> nome2stanza;
	private Stanza ultimaStanzaAggiunta;

	public LabirintoBuilder() {
		this.labirinto = new Labirinto();
		this.nome2stanza = new HashMap<>();
	}

	public LabirintoBuilder addStanza(String nome) {
		if (!this.nome2stanza.containsKey(nome)) {
			Stanza stanza = new Stanza(nome);
			this.nome2stanza.put(nome, stanza);
		}
		this.ultimaStanzaAggiunta = this.nome2stanza.get(nome);
		return this;
	}

	public LabirintoBuilder addStanzaIniziale(String nome) {
		this.addStanza(nome);
		this.labirinto.setStanzaCorrente(this.ultimaStanzaAggiunta);
		return this;
	}

	public LabirintoBuilder addStanzaVincente(String nome) {
		this.addStanza(nome);
		this.labirinto.setStanzaVincente(this.ultimaStanzaAggiunta);
		return this;
	}

	public LabirintoBuilder addAttrezzo(String nome, int peso) {
		if (this.ultimaStanzaAggiunta != null) {
			Attrezzo attrezzo = new Attrezzo(nome, peso);
			this.ultimaStanzaAggiunta.addAttrezzo(attrezzo);
		}
		return this;
	}

	public LabirintoBuilder addAdiacenza(String stanzaCorrente, String stanzaAdiacente, String direzione) {
		Stanza c = this.nome2stanza.get(stanzaCorrente);
		Stanza a = this.nome2stanza.get(stanzaAdiacente);
		
		if (c != null && a != null) {
			c.impostaStanzaAdiacente(direzione, a);
		}
		return this;
	}

	public Labirinto getLabirinto() {
		return this.labirinto;
	}
	
	
	/**
	 * Aggiunge un attrezzo a una stanza specifica del labirinto
	 */
	public LabirintoBuilder addAttrezzo(String nomeStanza, String nomeAttrezzo, int peso) {

		Stanza stanza = this.nome2stanza.get(nomeStanza); 
		
		if (stanza != null) {

			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso); 
			

			stanza.addAttrezzo(attrezzo);
		}
		return this;
	}
	
	
	public LabirintoBuilder addStanzaMagica(String nome, int soglia) {
		Stanza stanza = new StanzaMagica(nome, soglia);
		this.nome2stanza.put(nome, stanza); // Aggiusta 'nome2stanza' con il nome della tua mappa
		return this;
	}

	public LabirintoBuilder addStanzaBuia(String nome, String attrezzoPerVedere) {
		Stanza stanza = new StanzaBuia(nome, attrezzoPerVedere);
		this.nome2stanza.put(nome, stanza);
		return this;
	}

	public LabirintoBuilder addStanzaBloccata(String nome, String direzioneBloccata, String chiave) {
		Stanza stanza = new StanzaBloccata(nome, direzioneBloccata, chiave);
		this.nome2stanza.put(nome, stanza);
		return this;
	}

	public LabirintoBuilder addMago(String nomeStanza, String nome, String presentazione, String nomeAttrezzo, int peso) {
		Stanza stanza = this.nome2stanza.get(nomeStanza);
		if (stanza != null) {
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			AbstractPersonaggio mago = new Mago(nome, presentazione, attrezzo);
			stanza.setPersonaggio(mago);
		}
		return this;
	}

	public LabirintoBuilder addCane(String nomeStanza, String nome, String presentazione, String nomeAttrezzo, int peso) {
		Stanza stanza = this.nome2stanza.get(nomeStanza);
		if (stanza != null) {
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			AbstractPersonaggio cane = new Cane(nome, presentazione, attrezzo);
			stanza.setPersonaggio(cane);
		}
		return this;
	}

	public LabirintoBuilder addStrega(String nomeStanza, String nome, String presentazione) {
		Stanza stanza = this.nome2stanza.get(nomeStanza);
		if (stanza != null) {
			AbstractPersonaggio strega = new Strega(nome, presentazione);
			stanza.setPersonaggio(strega);
		}
		return this;
	}
}