package pack;

public class VirusFaible extends Virus{
	VirusFaible(int i, int j) {super(i,j); type="FaibleVirus";}
	protected void afficher() {
		System.out.print ("\u001b[1;35mF\u001b[0m");
		
	}

}
