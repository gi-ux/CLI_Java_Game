
public class RappresentazioneTestuale {

	public static void Regole() {
	System.out.println("Benvenuto nel mio gioco!");
	System.out.println("-----------------------------------------");
	System.out.println("Eccoti le regole:");
	System.out.println(
			"ogni giocatore, a turno, lancia un dado e muove la pedina (un passo alla volta) raccogliendo\r\n"
					+ "le monete che trova sul suo percorso.\r\n"
					+ "• La strada da percorrere `e scelta dal giocatore specificando, ad ogni passo, la direzione da\r\n"
					+ "prendere (Nord, Est, Sud, Ovest).\r\n"
					+ "• Quando i due giocatori si trovano sulla stessa casella (fermi o di passaggio) viene eseguita una\r\n"
					+ "sfida. Entrambi i giocatori lanciano il dado; chi ottiene il valore pi`u alto ruba una moneta\r\n"
					+ "all’avversario. Il perdente `e costretto a ripartire dalla posizione iniziale. Se il perdente si\r\n"
					+ "trova gi`a sulla casella iniziale va spostato sulla casella iniziale dell’altro giocatore.\r\n"
					+ "• Se la sfida finisce in parit`a, i dadi vanno rilanciati.\r\n"
					+ "• La partita termina quando tutte le monete sono state raccolte e sono in possesso di un unico\r\n"
					+ "giocatore oppure quando il giocatore che perde la sfida non possiede alcuna moneta.");
	System.out.println("-----------------------------------------");
	}

	public static void printMenu()
	{
		System.out.println("\nMENU:");
		System.out.println("0) Esci\n1) Griglia\n2) Muovi\n3) Info");
	}
	
	public static void stampaGiocatori(Giocatore u1, Giocatore u2)
	{
		System.out.println("Giocatore X: ");
		System.out.println(u1);
		System.out.println("\nGiocatore Y: ");
		System.out.println(u2);
	}
}
