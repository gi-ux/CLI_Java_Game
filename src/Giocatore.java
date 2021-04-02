public class Giocatore {

	private String id;
	private char cont;
	private int monete;
	private int posx;
	private int posy;
	private int pozione;
	private int gemma;
	private int ostacoli_presi;
	
	public void setOstacoli_presi(int ostacoli_presi) {
		this.ostacoli_presi = ostacoli_presi;
	}
	
	public void setGemma(int gemma) {
		this.gemma = gemma;
	}
	
	public void setPozione(int pozione) {
		this.pozione = pozione;
	}
	
	public void setCont(char cont) {
		this.cont = cont;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setMonete(int monete) {
		this.monete = monete;
	}
	
	public void setPosx(int posx) {
		this.posx = posx;
	}
	
	public void setPosy(int posy) {
		this.posy = posy;
	}
	
	public char getCont() {
		return cont;
	}
	
	public int getOstacoli_presi() {
		return ostacoli_presi;
	}
	
	public int getGemma() {
		return gemma;
	}
	
	public int getPozione() {
		return pozione;
	}
	
	public String getId() {
		return id;
	}
	
	public int getMonete() {
		return monete;
	}
	
	public int getPosx() {
		return posx;
	}
	
	public int getPosy() {
		return posy;
	}
	
	public Giocatore()
	{}
	
	public Giocatore(String i)
	{
		this.id = i;
		this.monete = 0;
	}
	
	public String toString()
	{
		return "\nNome utente: " + id + "\nGiocatore: " + cont + "\nMonete: " + monete + "\nPozioni: " + pozione + "\nGemme: " + gemma + "\nOstacoli presi: " + ostacoli_presi +"\nPosizione: " + posx+"-"+posy;
	}
}
