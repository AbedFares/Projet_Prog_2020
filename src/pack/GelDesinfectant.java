package pack;

public class GelDesinfectant extends AntiVirus {
	
	GelDesinfectant(int i, int j) { //contructeur parametre
		super(i,j);type="Geldesinf";}
	//_________________________________________________________________________________________________
	protected void afficher() {// methode d'affichage (affiche le caractere significatif a gelDesinfectant "G")
		System.out.print ("\u001b[1;32mG\u001b[0m");
		
	}

}
