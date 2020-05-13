package pack;

public class VirusFaible extends Virus{
//__________________________________________________________________________________________________________	
	VirusFaible(int i, int j) {super(i,j); type="FaibleVirus";}
//__________________________________________________________________________________________________________	
	protected void afficher() {// methode d'affichage (affiche le caractere significatif a VirusFaible "F") 
		                        System.out.print ("\u001b[1;35mF\u001b[0m");
		
	                         }

}
