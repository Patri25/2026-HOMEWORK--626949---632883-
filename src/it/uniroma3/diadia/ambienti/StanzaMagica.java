package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagica extends Stanza{
	
	private int contaAttrezziPosati;
	private int sogliaMagica;
	final static int SOGLIA_MAGICA_DEFAULT=3;
	
	public StanzaMagica(String nome, int sogliaMagica) {
		super(nome);
		this.sogliaMagica=sogliaMagica;
		this.contaAttrezziPosati=0;
	}
	public StanzaMagica(String nome) {
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
		return super.addAttrezzo(attrezzo);
		
	}
}
