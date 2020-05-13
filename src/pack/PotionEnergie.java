package pack;

public class PotionEnergie extends AntiVirus {
//__________________________________________________________________________________________________________
	PotionEnergie (int i, int j) {//constructeur parametre
		super(i,j);type="PotionEnergie";}
//__________________________________________________________________________________________________________
	protected void afficher() {//methode d'affichage (affiche le caractere significatif a PotionEnergie "P"
		                        System.out.print ("\u001b[32mP\u001b[0m");
		
	                          }

}
