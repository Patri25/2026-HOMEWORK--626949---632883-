package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

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
	 * Aggiunge una Stanza Magica al labirinto
	 */
	public LabirintoBuilder addStanzaMagica(String nome, int sogliaMagica) {
		Stanza stanza = new StanzaMagica(nome, sogliaMagica);
		this.nome2stanza.put(nome, stanza);
		this.ultimaStanzaAggiunta = stanza;
		return this;
	}

	/**
	 * Aggiunge una Stanza Buia al labirinto
	 */
	public LabirintoBuilder addStanzaBuia(String nome, String attrezzoPerLuce) {
		Stanza stanza = new StanzaBuia(nome, attrezzoPerLuce);
		this.nome2stanza.put(nome, stanza);
		this.ultimaStanzaAggiunta = stanza;
		return this;
	}

	/**
	 * Aggiunge una Stanza Bloccata al labirinto
	 */
	public LabirintoBuilder addStanzaBloccata(String nome, String direzioneBloccata, String attrezzoSbloccante) {
		Stanza stanza = new StanzaBloccata(nome, direzioneBloccata, attrezzoSbloccante);
		this.nome2stanza.put(nome, stanza);
		this.ultimaStanzaAggiunta = stanza;
		return this;
	}
}