import java.util.Scanner;

public class Partita {

	public static int cont = 2;

	static void Menu(LogicaDiGioco tabellone, Giocatore u1, Giocatore u2, int scelta) {
		switch (scelta) {
		case 0:
			System.exit(0);
		case 1:
			TavoloDaGioco.tavolo(tabellone);
			break;
		case 2:
			if(cont % 2 == 0)
				System.out.println("Muove X: ");
			else
				System.out.println("Muove Y: ");
			cont++;
			tabellone.muovi(u1,u2);
			break;
		case 3:
			RappresentazioneTestuale.stampaGiocatori(u1, u2);
			break;
		default:
			System.out.println("Errore di inserimento");
			break;
		}
	}

	public void gioca(Giocatore g1, Giocatore g2)
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Sto creando il tabellone");
		LogicaDiGioco tabellone = new LogicaDiGioco();
		tabellone.setPos(g1,g2);
		tabellone.ostacoli();
		int scelta = 5;
		do
		{
			RappresentazioneTestuale.printMenu();
			scelta = input.nextInt();
			Menu(tabellone,g1,g2,scelta);
		}while(LogicaDiGioco.vincita == false);
		input.close();
	}
	
	
}
