package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class IOSimulator implements IO {

	private List<String> comandiLetti;
	private List<String> messaggiProdotti;
	// Usiamo l'indice del comando come chiave per evitare che comandi uguali si sovrascrivano
	private Map<Integer, List<String>> messaggiPerPasso;
	private int indiceProssimoComando;

	public IOSimulator(List<String> comandiLetti) {
		this.comandiLetti = comandiLetti;
		this.messaggiProdotti = new ArrayList<>();
		this.messaggiPerPasso = new LinkedHashMap<>();
		this.indiceProssimoComando = 0;
	}

	@Override
	public String leggiRiga() {
		if (this.indiceProssimoComando >= this.comandiLetti.size()) {
			return null; // Evita eccezioni se i comandi finiscono prima del previsto
		}
		String comando = this.comandiLetti.get(this.indiceProssimoComando);
		this.messaggiPerPasso.put(this.indiceProssimoComando, new ArrayList<>());
		this.indiceProssimoComando++;
		return comando;
	}

	@Override
	public void mostraMessaggio(String msg) {
		this.messaggiProdotti.add(msg);
		if (this.indiceProssimoComando > 0) {
			// Aggiungiamo il messaggio alla lista dell'ultimo comando letto
			int indiceUltimoComando = this.indiceProssimoComando - 1;
			this.messaggiPerPasso.get(indiceUltimoComando).add(msg);
		}
	}

	public String getMessaggio(int posizione) {
		return this.messaggiProdotti.get(posizione);
	}

	public boolean contieneMessaggio(String messaggio) {
		return this.messaggiProdotti.contains(messaggio);
	}

	public List<String> getMessaggi() {
		return this.messaggiProdotti;
	}
	
	// Nuovo metodo utile per i test: recupera i messaggi generati all'i-esimo inserimento
	public List<String> getMessaggiAlPasso(int indice) {
		return this.messaggiPerPasso.get(indice);
	}
}