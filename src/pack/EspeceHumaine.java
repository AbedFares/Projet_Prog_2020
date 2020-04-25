package pack;

public class EspeceHumaine extends NosObjets {
	int immunite;


	EspeceHumaine (String type , int n , int m ) {
		super(type, n, m);
		immunite=5;
		exist=true;}


	public EspeceHumaine() {}		
	 /*do {
	 System.out.println ("entrez une direction");
	 Scanner input=new Scanner (System.in);
	 a=input.nextLine ();}
	 while ( a != "haut" && a!="bas" && a!="gauche" && a!="droite");*/
protected  void deplacer (String a )	{
		 if (( a.contentEquals("haut")) )
	        	positionX+=1;
	        else if((a.contentEquals("bas")) )
	        	positionX-=1;
	        else if((a.contentEquals("droite")) )
	        	positionY+=1;
	        else if((a.contentEquals("gauche")) )
	        	positionY-=1;
		
		
	 }
	protected void afficher() {
		System.out.print("\u001b[1;37mH\u001b[0m");
		
	}
	 protected void rencontre (int lig_dest,int col_dest,NosObjets[][] laby, Timing objet) {
		positionX=lig_dest;
		positionY=col_dest;
		if (laby[lig_dest][col_dest].type=="Covid19") 
			{immunite-=3;
			objet.start() ;}
			else if (laby[lig_dest][col_dest].type=="FaibleVirus")
				immunite-=1;
			else if (laby[lig_dest][col_dest].type=="PotionEnergie")
				immunite=5;
			else if (laby[lig_dest][col_dest].type=="Arrive")
				immunite=6;//Si il arrive a la case de depart 
			else if (laby[lig_dest][col_dest].type=="Geldesinf")
			{
				objet.set_gel_intime(true);
			}
		 }
	 protected void alert () {}
	 protected boolean est_vivant () {
		 if (immunite<=0)
			 exist=false;
		 return (exist);
	 }
	 public int get_immunite() {return immunite;}
	public boolean est_exist() 
	{		 
		if (immunite<=0)
			exist=false;
		return (exist);
	}
	public boolean get_test() { if (immunite==6) return true;else return false;}

}


