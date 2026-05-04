package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaProtected extends StanzaProtected{
	
	private int contaAttrezziPosati;
	private int sogliaMagica;
	final static int SOGLIA_MAGICA_DEFAULT=3;
	
	public StanzaMagicaProtected(String nome, int sogliaMagica) {
		super(nome);
		this.sogliaMagica=sogliaMagica;
		this.contaAttrezziPosati=0;
	}
	public StanzaMagicaProtected(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}
	
	public Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		StringBuilder nomeInverso = new StringBuilder(attrezzo.getNome());
		nomeInverso = nomeInverso.reverse();
		int doppioPeso= attrezzo.getPeso()*2;
		attrezzo=new Attrezzo(nomeInverso.toString(),doppioPeso);
		
		return attrezzo;
		
	}
	
	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		this.contaAttrezziPosati++;
		
		if(this.contaAttrezziPosati >sogliaMagica) {
			attrezzo = modificaAttrezzo(attrezzo);
		}
		if(this.numeroAttrezzi <this.attrezzi.length) {
			this.attrezzi[this.numeroAttrezzi]=attrezzo;
			this.numeroAttrezzi++;
			
			return true;
		}
		else {
			return false;
		}
		
		
	}
}
