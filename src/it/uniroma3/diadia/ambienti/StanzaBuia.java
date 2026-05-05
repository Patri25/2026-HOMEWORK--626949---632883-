package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza {
	
	
	private String attrezzoSpecifico;
	final static String ATTREZZO_SPECIFICO_DEFAULT="torcia";

	public StanzaBuia(String nome, String attrezzoPerVedere) {
		super(nome);
		this.attrezzoSpecifico=attrezzoPerVedere;
		// TODO Auto-generated constructor stub
	}
	
	public StanzaBuia(String nome) {
		this(nome, ATTREZZO_SPECIFICO_DEFAULT);
	}
	
	@Override
	public String getDescrizione() {
		if(this.hasAttrezzo(attrezzoSpecifico)) {
			return super.getDescrizione();
		}
		else {
			return ("qui c'è buio pesto...");
		}
		
		
    }

}
