package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {

	private String direzioneBloccata;
	private String attrezzoPerSbloccare;
	private static String ATTREZZOPERSBLOCCAREDEFAULT="chiave";
	
	public StanzaBloccata(String nome, String direzioneBloccata, String attrezzoPerSbloccare) {
		super(nome);
		this.direzioneBloccata=direzioneBloccata;
		this.attrezzoPerSbloccare=attrezzoPerSbloccare;
	}
	
	public StanzaBloccata(String nome, String direzioneBloccata) {
		this(nome, direzioneBloccata,ATTREZZOPERSBLOCCAREDEFAULT);
	}
	
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		
		if(this.hasAttrezzo(this.attrezzoPerSbloccare) || !direzione.equals(direzioneBloccata)) {
			return super.getStanzaAdiacente(direzione);
		}
		
		else{
			return this;
		}
		
	}
	
	@Override
	public String getDescrizione() {
		if(this.hasAttrezzo(attrezzoPerSbloccare)) {
			return super.getDescrizione();
		}
		
		return "la direzione "+ this.direzioneBloccata + " è bloccata. Hai bisogno di " +  this.attrezzoPerSbloccare + " per aprirla. "+ super.getDescrizione();
	}
	
	

}
