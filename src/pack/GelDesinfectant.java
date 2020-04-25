package pack;

public class GelDesinfectant extends AntiVirus {
	GelDesinfectant(int i, int j) {super(i,j);type="Geldesinf";}
	protected void afficher() {
		System.out.print ("\u001b[1;32mG\u001b[0m");
		
	}

}
