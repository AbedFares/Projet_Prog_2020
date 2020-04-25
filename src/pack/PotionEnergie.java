package pack;

public class PotionEnergie extends AntiVirus {
	PotionEnergie (int i, int j) {super(i,j);type="PotionEnergie";}
	protected void afficher() {
		System.out.print ("\u001b[1;33mP\u001b[0m");
		
	}

}
