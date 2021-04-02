import java.util.Scanner;

public class LogicaDiGioco {

	public static Scanner input = new Scanner(System.in);
	public static int CONT = 2;
	public static boolean vincita = false;
	char[][] tab = new char[15][15];
	private int gridH = 15;
	private int gridW = 15;
	
	LogicaDiGioco()
	{}
	
	public void setGridH(int gridH) {
		this.gridH = gridH;
	}
	
	public void setGridW(int gridW) {
		this.gridW = gridW;
	}
	
	public int getGridH() {
		return gridH;
	}
	
	public int getGridW() {
		return gridW;
	}
	
	//creo gli oggetti
	Moneta moneta = new Moneta();
	Albero albero = new Albero();
	Pozione pozione = new Pozione();
	Roccia roccia = new Roccia();
	Gemma gemma = new Gemma();

	//creo un dado
	Dado dado = new Dado();


	//setta i giocatori nella posizione iniziale
	public void setPos(Giocatore p1, Giocatore p2)
	{
		//pulisci();
		p1.setCont('x');
		p2.setCont('y');
		tab[14][0] = p2.getCont();
		tab[0][14] = p1.getCont();
		p1.setPosx(0);
		p1.setPosy(14);
		p2.setPosx(14);
		p2.setPosy(0);
	}

	//metodo per qualsiasi tipo di movimento e sfida
	void muovi(Giocatore p1, Giocatore p2)
	{
		//inizializzo le variabili
		int posizioni[] = new int[2];
		String m="";
		char utente;
		
		//tiro il dado
		int random = dado.tira();
		System.out.println("Sul dado è uscito: " + random);
		
		//ottengo quale utente gioca
		if(CONT % 2 == 0)
			utente = p1.getCont();
		else
			utente = p2.getCont();
		
		//eseguo la scelta della mossa
		for(int i = 0; i < random; i++)
		{
			boolean vittoria = false;
			
			do{
				//controllo che ci sia una vittoria
				vittoria = conta(p1,p2);
				if(vittoria == true)
				{
					verdetto(p1,p2);
					return;
				}
				
				//richiedo la mossa
				System.out.println("Inserisci mossa: (SU,GIU,DX,SX)");
				m = input.next();
				
				//ottengo la posizione
				posizioni = sposta(m,utente,p1,p2);
				int x = posizioni[0];
				int y = posizioni[1];
				
				//controllo se c'è una sfida
				if(tab[x][y] == 'x' || tab[x][y] == 'y')
				{
					vittoria = sfida(p1,p2);

				}else if(tab[x][y] == moneta.getCont())
				{
					raccogli_moneta(p1,p2);
					tab[x][y] = ' ';
					tab[x][y] = utente;
					
				}else if(tab[x][y] == albero.getCont() || tab[x][y] == roccia.getCont())
				{
					raccogli_ostacolo(p1,p2);
					tab[x][y] = ' ';
					tab[x][y] = utente;
					
				}else if(tab[x][y] == gemma.getCont())
				{
					raccogli_gemma(p1,p2);
					tab[x][y] = ' ';
					tab[x][y] = utente;
					
				}else if(tab[x][y] == pozione.getCont())
				{
					raccogli_pozza(p1,p2);
					tab[x][y] = ' ';
					tab[x][y] = utente;
				}else
				//sposto senza alcun problema
				{
					tab[x][y] = utente;
				}
			}while(!m.equals("SU") && !m.equals("GIU") && !m.equals("DX") && !m.equals("SX"));
		}
		CONT ++;
	}
	
	//operazioni sugli spostamenti
	int[] sposta(String s, char u, Giocatore p1, Giocatore p2)
	{
		
		//ottengo la posizione prima di effettuare lo spostamento
		int p[] = new int[2];
		p = posizione(u);
		int x = p[0];
		int y = p[1];
		
		//tolgo il mio carattere per spostarlo
		tab[x][y] = ' '; 
		
		//eseguo tutti i controlli per i limiti della griglia
		if(s.equals("SX"))
		{
			if(y == 0)
				y = 14;
			else
				y--;
		}else if(s.equals("DX"))
		{
			if(y == 14)
				y = 0;
			else
				y++;
		}else if(s.equals("GIU"))
		{
			if(x == 14)
				x = 0;
			else
				x++;
		}else if(s.equals("SU"))
		{
			if(x == 0)
				x = 14;
			else
				x--;
		}
		
		//setto la posizione dell'utente
		int[] def = new int[2];
		def[0] = x;
		def[1] = y;
		if(CONT % 2 == 0)
		{
			p1.setPosx(x);
			p1.setPosy(y);
		}else
		{
			p2.setPosx(x);
			p2.setPosy(y);
		}
		//restituisco la nuova posizione
		return def; 
	}
	
	//controllo sulla sfida
	boolean sfida(Giocatore p1, Giocatore p2)
	{
		int random_x;
		int random_y;
		int newpos[] = new int[2];
		//se muove la x
		if(CONT %2 == 0)
		{
			//lo sfidato ha una gemma scappa
			if(p2.getGemma()>=1)
			{
				newpos = sposta_rand(p2);
				p2.setPosx(newpos[0]);
				p2.setPosy(newpos[1]);
				p2.setGemma(p2.getGemma()-1);
				tab[p1.getPosx()][p1.getPosy()] = p1.getCont();
				return false;
			}
			//lo sfidante ha una pozione vince
			if(p1.getPozione()>=1)
			{
				
				p1.setMonete(p1.getMonete()+1);
				p1.setPozione(p1.getPozione()-1);
				p2.setMonete(p2.getMonete()-1);
				if(p2.getMonete() == -1 || p1.getMonete() == 10)
					return true;
				sposta_y(p1,p2);
				
				return false;
			}
		}else
		{
			//lo sfidato ha una gemma scappa
			if(p1.getGemma()>=1)
			{
				newpos = sposta_rand(p1);
				p1.setPosx(newpos[0]);
				p1.setPosy(newpos[1]);
				p1.setGemma(p1.getGemma()-1);
				tab[p2.getPosx()][p2.getPosy()] = p2.getCont();
				return false;
			}
			//lo sfidante ha una pozione vince
			if(p2.getPozione()>=1)
			{
				p2.setMonete(p2.getMonete()+1);
				p2.setPozione(p2.getPozione()-1);
				p1.setMonete(p1.getMonete()-1);
				if(p1.getMonete() == -1 || p2.getMonete() == 10)
					return true;
				sposta_x(p1,p2);
				return false;
			}
		}
		//se non ho ne pozioni ne gemme
		do {
			//tiro i dadi
			random_x = dado.tira();
			random_y = dado.tira();
			
		}while(random_x == random_y);
		
		if(random_x > random_y) //vince l'utente x
		{
			//aggiungo una moneta ad x e ne tolgo una a y
			p1.setMonete(p1.getMonete()+1);
			p2.setMonete(p2.getMonete()-1);
			
			if(p2.getMonete() == -1 || p1.getMonete()== 10) //controllo fine partita
				return true;
			//sposto la y in funzione della sua posizione
			sposta_y(p1,p2);
			
		}else if(random_x < random_y) //vince l'utente x
		{
			//aggiungo una moneta ad y e ne tolgo una a x
			p2.setMonete(p2.getMonete()+1);
			p1.setMonete(p1.getMonete()-1);
			
			if(p1.getMonete()== -1 || p2.getMonete() == 10) //controllo fine partita
				return true;
			//sposto la x in funzione della sua posizione
			sposta_x(p1,p2);
		}
		return false;
	}
	
	//raccolgo una moneta
	void raccogli_moneta(Giocatore p1, Giocatore p2)
	{
		
		if(CONT %2 == 0)
			p1.setMonete(p1.getMonete()+1);
		else
			p2.setMonete(p2.getMonete()+1);
	}
	
	//tolgo un ostacolo con la penalità
	void raccogli_ostacolo(Giocatore p1, Giocatore p2)
	{
		//se l'utente trova un ostacolo perde una moneta (non andando in negativo)
		if(CONT %2 == 0) {
			if(p1.getMonete()>=1)
				p1.setMonete(p1.getMonete()-1);
			p1.setOstacoli_presi(p1.getOstacoli_presi()+1);
		}
		else {
			if(p2.getMonete()>=1)
				p2.setMonete(p2.getMonete()-1);
			p2.setOstacoli_presi(p2.getOstacoli_presi()+1);
		}
	}
	
	//raccolgo una pozione
	void raccogli_pozza(Giocatore p1, Giocatore p2)
	{
		
		if(CONT %2 == 0)
			p1.setPozione(p1.getPozione()+1);
		else
			p2.setPozione(p2.getPozione()+1);
	}
	
	//raccolgo una gemma
	void raccogli_gemma(Giocatore p1, Giocatore p2)
	{
		
		if(CONT %2 == 0)
			p1.setGemma(p1.getGemma()+1);
		else
			p2.setGemma(p2.getGemma()+1);
	}
	
	//mi restituisce una posizione
	int[] posizione(char u)
	{
		int[] posizione = new int[2];
		for(int i = 0; i < tab.length; i++)
		{
			for(int j = 0; j < tab[i].length; j++)
			{
				if(tab[i][j] == u)
				{
					posizione[0] = i;
					posizione[1] = j;
				}
					
			}
		}
		return posizione;
	}
	
	//controlla se ho un vincitore
	boolean conta(Giocatore p1, Giocatore p2)
	{
		if(p1.getMonete()== 10 || p2.getMonete()== 10 || p1.getMonete() == -1 || p2.getMonete() == -1)
			return true;
		return false;
	}
	
	//genero caualmente le monete
	private void monete()
	{
		int cont = 0;
		do
		{
			int i = ((int) (Math.random() * 14));
			int j = ((int) (Math.random() * 14));
			if(tab[i][j] == '\0')
			{
				tab[i][j] = moneta.getCont();
				cont ++;
			}
		}while(cont < 10);
	}
	
	//genero caualmente gli alberi
	private void alberi()
	{
		int cont = 0;
		do
		{
			int i = ((int) (Math.random() * 14));
			int j = ((int) (Math.random() * 14));
			if(tab[i][j] == '\0')
			{
				tab[i][j] =albero.getCont();
				cont ++;
			}
		}while(cont < 7);
	}
	
	//genero caualmente le pozioni
	private void pozioni()
	{
		int cont = 0;
		do
		{
			int i = ((int) (Math.random() * 14));
			int j = ((int) (Math.random() * 14));
			if(tab[i][j] == '\0')
			{
				tab[i][j] =pozione.getCont();
				cont ++;
			}
		}while(cont < 3);
	}
	
	//genero caualmente le rocce
	private void rocce()
	{
		int cont = 0;
		do
		{
			int i = ((int) (Math.random() * 14));
			int j = ((int) (Math.random() * 14));
			if(tab[i][j] == '\0')
			{
				tab[i][j] =roccia.getCont();
				cont ++;
			}
		}while(cont < 5);
	}

	//genero caualmente le gemme
	private void gemme()
	{
		int cont = 0;
		do
		{
			int i = ((int) (Math.random() * 14));
			int j = ((int) (Math.random() * 14));
			if(tab[i][j] == '\0')
			{
				tab[i][j] = gemma.getCont();
				cont ++;
			}
		}while(cont < 5);
	}
	
	//stampo gli ostacoli e gemme/pozioni
	void ostacoli()
	{
		monete();
		alberi();
		pozioni();
		rocce();
		gemme();
	}

	//in caso di vittoria chiudo lo scanner e termino il programma stampando il vincitore
	void verdetto(Giocatore p1, Giocatore p2)
	{
		vincita = true;
		if(p2.getMonete() == -1 || p1.getMonete()== 10)
			System.out.println("HA VINTO " + p1.getCont()+ " (" + p1.getId()+ ")");
		else if(p1.getMonete() == -1 || p2.getMonete()== 10)
			System.out.println("HA VINTO " + p2.getCont()+ " (" + p2.getId()+ ")");
		input.close();
		return;
		//System.exit(0);
	}
	
	//sposto in modo random se durante la sfida l'utente ha una gemma
	private int[] sposta_rand(Giocatore p)
	{
		//ottengo la posizione prima di effettuare lo spostamento
		int pos[] = new int[2];
		pos = posizione(p.getCont());
		int x = pos[0];
		int y = pos[1];
			
		//tolgo il mio carattere per spostarlo
		tab[x][y] = ' ';
		int c = 0;
		int i,j;
		do {
			i = ((int) (Math.random() * 14));
			j = ((int) (Math.random() * 14));
			if(tab[i][j] == '\0')
			{
				tab[i][j] = p.getCont();
				pos[0] = i;
				pos[1] = j;
				c ++;
			}
		}while(c == 1);
		return pos;
	}

	//sposto x in funzione della sua posizione
	private void sposta_x(Giocatore p1, Giocatore p2)
	{
		if(p1.getPosx() == 0 && p1.getPosy()== 14)
		{
			p1.setPosx(0);
			p1.setPosy(14);
			p2.setPosx(14);
			p2.setPosy(0);
			tab[0][14] = ' ';
			tab[0][14] = p1.getCont();
			tab[14][0] = p2.getCont();
			
		}else
		{
			int[] cancellazione = new int[2];
			cancellazione = posizione('x');
			tab[cancellazione[0]][cancellazione[1]] = ' ';
			p1.setPosx(0);
			p1.setPosy(14);
			tab[0][14] = p1.getCont();
		}
	}
	
	//sposto y in funzione della sua posizione
	private void sposta_y(Giocatore p1, Giocatore p2)
	{
		if(p2.getPosx()== 14 && p2.getPosy()== 0) //controllo posizione iniziale
		{
			//x vince ed y è nella pos iniziale
			p2.setPosx(0);
			p2.setPosy(14);
			p1.setPosx(14);
			p1.setPosy(0);
			tab[14][0] = ' ';
			tab[0][14] = p2.getCont();
			tab[14][0] = p1.getCont();
			
		}else //qualsiasi posizione
		{
			int[] cancellazione = new int[2];
			cancellazione = posizione('y');
			tab[cancellazione[0]][cancellazione[1]] = ' ';
			p2.setPosx(14);
			p2.setPosy(0);
			tab[14][0] = p2.getCont();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
