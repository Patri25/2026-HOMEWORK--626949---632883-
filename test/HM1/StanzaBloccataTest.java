package HM1;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {

    private StanzaBloccata stanzaBloccata;
    private Stanza stanzaNord;
    private Attrezzo chiave;

    @BeforeEach
    public void setUp() {
        stanzaBloccata = new StanzaBloccata("Prigione", "nord", "chiave");
        stanzaNord = new Stanza("Giardino");
        stanzaBloccata.impostaStanzaAdiacente("nord", stanzaNord);
        chiave = new Attrezzo("chiave", 1);
    }

    @Test
    public void testGetStanzaAdiacenteDirezioneBloccataSenzaAttrezzo() {
        assertSame(stanzaBloccata, stanzaBloccata.getStanzaAdiacente("nord"));
    }

    @Test
    public void testGetStanzaAdiacenteDirezioneBloccataConAttrezzo() {
        stanzaBloccata.addAttrezzo(chiave);
        assertSame(stanzaNord, stanzaBloccata.getStanzaAdiacente("nord"));
    }

    @Test
    public void testGetStanzaAdiacenteDirezioneNonBloccata() {
        Stanza stanzaEst = new Stanza("Torre");
        stanzaBloccata.impostaStanzaAdiacente("est", stanzaEst);
        assertSame(stanzaEst, stanzaBloccata.getStanzaAdiacente("est"));
    }

    @Test
    public void testGetDescrizioneSenzaAttrezzoMenzionaDirezioneBloccata() {
        String descrizione = stanzaBloccata.getDescrizione();
        assertTrue(descrizione.contains("nord"));
        assertTrue(descrizione.contains("chiave"));
    }
}