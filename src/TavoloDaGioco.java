
public class TavoloDaGioco{

	public TavoloDaGioco()
	{}
	
	public static void tavolo(LogicaDiGioco logica)
	{
		for (int i = 0; i < logica.getGridW()*4+1 ; i++) 
		{
			System.out.print("-");
		}
		System.out.println("");
		
		for (int i = 0; i < logica.getGridH(); i++) 
		{
			System.out.print("| ");
			for (int j = 0; j < logica.getGridW(); j++) 
			{
				System.out.print(logica.tab[i][j]);
				System.out.print(" | ");
			}
			System.out.println("");
			for (int j = 0; j < logica.getGridH()*4+1; j++) 
			{
				System.out.print("-");			
			}
			System.out.println("");
		}
		System.out.println("Legenda: 'x' = player X, 'y' = player Y, '$' = moneta, '%' = gemma, '&' = pozione magica, '@' = roccia, '#' = albero");
	}
	
	
}

