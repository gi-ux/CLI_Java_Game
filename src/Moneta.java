
public class Moneta {

	private char cont = '$';
	private int posx;
	private int posy;
	
	public char getCont() {
		return cont;
	}
	
	public Moneta(){
		
	}
	
	public int getPosx(){
		return posx;
	}
	
	public int getPosy(){
		return posy;
	}
	
	public void setPosx(int x){
		posx = x;
	}
	
	public void setPosy(int y){
		posy = y;
	}
	
	public void setCont(char c)
	{
		this.cont = c;
	}
}
