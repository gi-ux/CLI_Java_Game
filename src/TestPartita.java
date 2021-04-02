import java.util.Scanner;

public class TestPartita {
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		RappresentazioneTestuale.Regole();
		System.out.println("Sei pronto a giocare?\nInserisci il tuo nome");
		String utente1 = input.nextLine();
		System.out.println("Perfetto, tu sarai il giocatore X!");
		System.out.println("Ora è il tuo turno giocatore Y!\nInserisci il tuo nome");
		String utente2 = input.nextLine();
		System.out.println("Ho creato i vostri giocatori, preparatevi all'avvio");
		Giocatore g1 = new Giocatore(utente1);
		Giocatore g2 = new Giocatore(utente2);
		Partita inizia = new Partita();
		inizia.gioca(g1, g2);
		input.close();
	}	
}
