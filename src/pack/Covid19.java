package pack;

public class Covid19 extends Virus{
	Covid19(int i, int j) {super(i,j);type="Covid19";}
	protected void afficher() {
		System.out.print ("\u001b[1;31mC\u001b[0m");
		
	}
}
