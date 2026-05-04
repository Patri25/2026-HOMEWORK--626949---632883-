package it.uniroma3.diadia;
import java.util.Scanner;


public class FabbricaDiComandiFisarmonica implements FabbricaDiComandi {
    private IOConsole ioc;

    public FabbricaDiComandiFisarmonica(IOConsole ioc) {
        this.ioc = ioc;
    }

    @Override
    public Tempcomando costruisciComando(String istruzione) {
        Scanner scannerDiParole = new Scanner(istruzione);
        String nomeComando = null;
        String parametro = null;
        Tempcomando comando = null;

        if (scannerDiParole.hasNext())
            nomeComando = scannerDiParole.next();  // prima parola: nome comando

        if (scannerDiParole.hasNext())
            parametro = scannerDiParole.next();    // seconda parola: eventuale parametro

        if (nomeComando == null)
            comando = new ComandoSconosciuto(ioc);
        else if (nomeComando.equals("vai"))
            comando = new ComandoVai(ioc);
        else if (nomeComando.equals("prendi"))
            comando = new ComandoPrendi(ioc);
        else if (nomeComando.equals("posa"))
            comando = new ComandoPosa(ioc);
        else if (nomeComando.equals("aiuto"))
            comando = new ComandoAiuto(ioc);
        else if (nomeComando.equals("fine"))
            comando = new ComandoFine(ioc);
        else
            comando = new ComandoSconosciuto(ioc);

        comando.setParametro(parametro);
        return comando;
    }
}
