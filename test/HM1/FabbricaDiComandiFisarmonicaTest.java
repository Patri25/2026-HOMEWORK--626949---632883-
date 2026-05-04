package HM1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Tempcomando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

import static org.junit.jupiter.api.Assertions.*;

class FabbricaDiComandiFisarmonicaTest {
    private FabbricaDiComandiFisarmonica fabbrica;

    @BeforeEach
    public void setUp() {
        this.fabbrica = new FabbricaDiComandiFisarmonica(new IOConsole());
    }

    @Test
    void testRiconoscimentoVai() {
        Tempcomando comando = fabbrica.costruisciComando("vai nord");
        assertEquals("vai", comando.getNome());
    }

    @Test
    void testRiconoscimentoVaiParametro() {
        Tempcomando comando = fabbrica.costruisciComando("vai nord");
        assertEquals("nord", comando.getParamtro());
    }

    @Test
    void testRiconoscimentoPrendi() {
        Tempcomando comando = fabbrica.costruisciComando("prendi spada");
        assertEquals("prendi", comando.getNome());
    }

    @Test
    void testRiconoscimentoPrendiParametro() {
        Tempcomando comando = fabbrica.costruisciComando("prendi spada");
        assertEquals("spada", comando.getParamtro());
    }

    @Test
    void testRiconoscimentoPosa() {
        Tempcomando comando = fabbrica.costruisciComando("posa spada");
        assertEquals("posa", comando.getNome());
    }

    @Test
    void testRiconoscimentoAiuto() {
        Tempcomando comando = fabbrica.costruisciComando("aiuto");
        assertEquals("aiuto", comando.getNome());
    }

    @Test
    void testRiconoscimentoFine() {
        Tempcomando comando = fabbrica.costruisciComando("fine");
        assertEquals("fine", comando.getNome());
    }

    @Test
    void testRiconoscimentoGuarda() {
        Tempcomando comando = fabbrica.costruisciComando("guarda");
        assertEquals("guarda", comando.getNome());
    }

    @Test
    void testComandoSconosciuto() {
        Tempcomando comando = fabbrica.costruisciComando("blabla");
        assertEquals("sconosciuto", comando.getNome());
    }

    @Test
    void testComandoVuoto() {
        Tempcomando comando = fabbrica.costruisciComando("");
        assertEquals("sconosciuto", comando.getNome());
    }
}