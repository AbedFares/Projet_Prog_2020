package pack;

public class EspeceHumaine extends NosObjets {
	int immunite; //degre d'immunite de l'etre humain 
//_________________________________________________________________________________________________

	EspeceHumaine (String type , int n , int m ) {// constructeur parametre
		super(type, n, m);
		immunite=5;
		exist=true;}

//_________________________________________________________________________________________________
	public EspeceHumaine() {}		//constructeur par defaut
//_________________________________________________________________________________________________
	 /*do {
	 System.out.println ("entrez une direction");
	 Scanner input=new Scanner (System.in);
	 a=input.nextLine ();}
	 while ( a != "haut" && a!="bas" && a!="gauche" && a!="droite");*/
//_________________________________________________________________________________________________
	protected  void deplacer (String a )	{//methode permet le deplacement de l'etre humain selon la direction 
		 if (( a.contentEquals("haut")) )
	        	positionX+=1;
	        else if((a.contentEquals("bas")) )
	        	positionX-=1;
	        else if((a.contentEquals("droite")) )
	        	positionY+=1;
	        else if((a.contentEquals("gauche")) )
	        	positionY-=1;	
	 }
//_________________________________________________________________________________________________
	protected void afficher() { // methode d'affichage (affiche le caractere significatif a Homme "H") 
		System.out.print("\u001b[1;37mH\u001b[0m");
		
	}
//_________________________________________________________________________________________________
	 protected void rencontre (int lig_dest,int col_dest,NosObjets[][] laby, Timing objet) {// methode qui verifie le contenu de la case choisit
		positionX=lig_dest;
		positionY=col_dest;
		if (laby[lig_dest][col_dest].type=="Covid19") 
			{immunite-=3;
			objet.start() ;} // declonchement du chrono
			else if (laby[lig_dest][col_dest].type=="FaibleVirus")
				immunite-=1;
			else if (laby[lig_dest][col_dest].type=="PotionEnergie")
				immunite=5;
			else if (laby[lig_dest][col_dest].type=="Arrive")
				immunite=6;    //Si il arrive a la case de depart son degre d'immunite =6, juste une convention pour notre code
			else if (laby[lig_dest][col_dest].type=="Geldesinf")
			{
				objet.set_gel_intime(true); // arret du chrono
			}
		 }
//_________________________________________________________________________________________________	
	/*protected void alert () {}*/
//_________________________________________________________________________________________________
	/*protected boolean est_vivant () {
		 if (immunite<=0)
			 exist=false;
		 return (exist);
	 }*/
//_________________________________________________________________________________________________
	public int get_immunite() { // retourne le degre d'immunite de l'etre humain 
		return immunite;} 
//_________________________________________________________________________________________________
	public boolean est_exist() // methode qui indique si l'etre humain est vivant ou non
	{		 
		if (immunite<=0)
			exist=false;
		return (exist);
	}
//_________________________________________________________________________________________________
	public boolean get_test() {// methode qui verifie si l'etre humain est arrive a la case d'arrive 
		if (immunite==6) return true;else return false;} // immunite=6 une convetion pour pouvoir distinguer la case d'arrive

}


