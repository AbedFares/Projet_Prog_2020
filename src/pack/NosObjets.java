package pack;

public class NosObjets {
	protected int lignes , colonnes ;
	protected int positionX , positionY ;
	protected String type ;

	NosObjets (String type , int n , int m ){
		
		positionX = n;
		positionY = m;
		this.type = type ;
}
	protected void afficher() {
		System.out.println ("cette case contient  " + type);
		
	}
	protected int get_positionx() {
		return (positionX);
	}
	protected int get_positiony() {
		return (positionY);
	}
}
package labyrinthe;
import java.util.*;


public class EspeceHumaine extends NosObjets {
	int immunite;
	boolean vivant;

	EspeceHumaine (String type , int n , int m ) {
		super(type, n, m);
		immunite=5;
		vivant=true;}
	protected  void deplacement (String a )	{
		
	 /*do {
	 System.out.println ("entrez une direction");
	 Scanner input=new Scanner (System.in);
	 a=input.nextLine ();}
	 while ( a != "haut" && a!="bas" && a!="gauche" && a!="droite");*/
		 if (( a=="haut" ) )
	        	positionX+=1;
	        else if((a=="bas") )
	        	positionX-=1;
	        else if((a=="droite") )
	        	positionY+=1;
	        else if((a=="gauche") )
	        	positionY-=1;
		
		 	 
	 }
	 protected void rencontre (NosObjets objet) {
		if (objet.type=="covid") 
			{immunite-=3;
			alert() ;}
			else if (objet.type=="virus_faible")
				immunite-=1;
			else if (objet.type=="potion_energie")
				immunite=5;
		 }
	 protected void alert () {}
	 protected boolean est_vivant () {
		 if (immunite<=0)
			 vivant=false;
		 return (vivant);
	 }

}
