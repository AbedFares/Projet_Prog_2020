package pack;

public class Covid19 extends Virus{
	
	Covid19(int i, int j) { //conctructeur parametre
		super(i,j);type="Covid19";}
//__________________________________________________________________________________________________________
	protected void afficher() {//methode d'affichage (affiche le caractere significatif a Covid19 "C"
		System.out.print ("\u001b[1;31mC\u001b[0m");
		
	}
}
