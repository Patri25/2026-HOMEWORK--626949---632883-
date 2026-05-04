package it.uniroma3.diadia;

public interface Tempcomando {
	public void esegui(Partita partita);
	public void setParametro(String parametro);
	
	public String getNome();
	public String getParamtro();
}
