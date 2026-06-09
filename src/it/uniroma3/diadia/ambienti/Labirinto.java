package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class Labirinto {

    private Stanza stanzaCorrente;
    private Stanza stanzaVincente;

    // Costruttore privato
    public Labirinto() {
    }

    public Stanza getStanzaCorrente() {
        return stanzaCorrente;
    }

    public void setStanzaCorrente(Stanza stanzaCorrente) {
        this.stanzaCorrente = stanzaCorrente;
    }

    public Stanza getStanzaVincente() {
        return stanzaVincente;
    }

    public void setStanzaVincente(Stanza stanzaVincente) {
        this.stanzaVincente = stanzaVincente;
    }

    // Factory method statico
    public static LabirintoBuilder newBuilder() {
        return new LabirintoBuilder();
    }

    public static class LabirintoBuilder {

        private Labirinto labirinto;
        private Map<String, Stanza> nome2stanza;

        public LabirintoBuilder() {
            this.labirinto = new Labirinto();
            this.nome2stanza = new HashMap<>();
        }

        public LabirintoBuilder addStanzaIniziale(String nomeStanza) {
            Stanza s = new Stanza(nomeStanza);
            this.nome2stanza.put(nomeStanza, s);
            this.labirinto.setStanzaCorrente(s);
            return this;
        }

        public LabirintoBuilder addStanzaVincente(String nomeStanza) {
            Stanza s = new Stanza(nomeStanza);
            this.nome2stanza.put(nomeStanza, s);
            this.labirinto.setStanzaVincente(s);
            return this;
        }

        public LabirintoBuilder addStanza(String nomeStanza) {
            Stanza s = new Stanza(nomeStanza);
            this.nome2stanza.put(nomeStanza, s);
            return this;
        }

        public LabirintoBuilder addStanzaMagica(String nome, int soglia) {
            Stanza stanza = new StanzaMagica(nome, soglia);
            this.nome2stanza.put(nome, stanza);
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

        public LabirintoBuilder addAttrezzo(String nomeStanza, String nomeAttrezzo, int peso) {
            Stanza stanza = this.nome2stanza.get(nomeStanza);
            if (stanza != null) {
                Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
                stanza.addAttrezzo(attrezzo);
            }
            return this;
        }

        public LabirintoBuilder addAdiacenza(String stanzaPartenza, String stanzaDestinazione, String direzione) {
            Stanza sPartenza = this.nome2stanza.get(stanzaPartenza);
            Stanza sDestinazione = this.nome2stanza.get(stanzaDestinazione);
            sPartenza.impostaStanzaAdiacente(direzione, sDestinazione);
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

        public Labirinto getLabirinto() {
            return this.labirinto;
        }
    }
}