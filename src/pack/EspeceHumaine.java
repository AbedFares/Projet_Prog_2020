package pack;

public class EspeceHumaine extends NosObjets {
	int immunite;
	boolean vivant;

	EspeceHumaine (String type , int n , int m ) {
		super(type, n, m);
		immunite=5;
		vivant=true;}


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
		System.out.print ("H");
		
	}
	 protected void rencontre (int lig_dest,int col_dest,NosObjets[][] laby) {
		positionX=lig_dest;
		positionY=col_dest;
		if (laby[lig_dest][col_dest].type=="Covid19") 
			{immunite-=3;
			alert() ;}
			else if (laby[lig_dest][col_dest].type=="FaibleVirus")
				immunite-=1;
			else if (laby[lig_dest][col_dest].type=="PotionEnergie")
				immunite=5;
		 }
	 protected void alert () {}
	 protected boolean est_vivant () {
		 if (immunite<=0)
			 vivant=false;
		 return (vivant);
	 }
}


