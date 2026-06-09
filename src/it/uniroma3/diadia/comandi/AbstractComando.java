package it.uniroma3.diadia.comandi;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import it.uniroma3.diadia.Tempcomando;

public abstract class AbstractComando implements Tempcomando {
	
	private String parametro;
	

	private static Set<String> comandiDisponibili = new HashSet<>();

	public AbstractComando() {

		comandiDisponibili.add(this.getNome());
	}

	@Override
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	@Override
	public String getParamtro() {
		return this.parametro;
	}
	
	/**
	 * Magia della Reflection: il nome del comando viene dedotto dal nome della classe!
	 * Nessuna sottoclasse dovrà più implementare getNome().
	 */
	@Override
	public String getNome() {
		String nomeClasse = this.getClass().getSimpleName();

		if (nomeClasse.startsWith("Comando")) {
			return nomeClasse.substring(7).toLowerCase(); 
		}
		return nomeClasse.toLowerCase();
	}
	

	public static Collection<String> getComandiDisponibili() {
		return comandiDisponibili;
	}
}