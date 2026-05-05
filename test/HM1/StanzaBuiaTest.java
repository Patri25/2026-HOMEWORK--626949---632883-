package HM1;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaTest {

    private StanzaBuia stanzaBuia;
    private Attrezzo lanterna;

    @Before
    public void setUp() {
        stanzaBuia = new StanzaBuia("Cripta", "lanterna");
        lanterna = new Attrezzo("lanterna", 1);
    }

    @Test
    public void testGetDescrizioneSenzaLanterna() {
        String descrizione = stanzaBuia.getDescrizione();
        assertTrue(descrizione.contains("buio"));
    }

    @Test
    public void testGetDescrizioneConLanterna() {
        stanzaBuia.addAttrezzo(lanterna);
        String descrizione = stanzaBuia.getDescrizione();
        assertTrue(descrizione.contains("Cripta"));
    }

    @Test
    public void testGetDescrizioneConAttrezzoSbagliato() {
        stanzaBuia.addAttrezzo(new Attrezzo("spada", 2));
        String descrizione = stanzaBuia.getDescrizione();
        assertTrue(descrizione.contains("buio"));
    }

    @Test
    public void testGetStanzaAdiacenteFunzionaSenzaLanterna() {
        Stanza nord = new Stanza("Salone");
        stanzaBuia.impostaStanzaAdiacente("nord", nord);
        assertSame(nord, stanzaBuia.getStanzaAdiacente("nord"));
    }
}