package pack;

public class NosObjets {
	
	protected int positionX , positionY ; // indique les postions des objets 
	protected String type ;              //Covid19,PotionEnergie,FaibleVirus,Homme,..
	protected boolean exist;          // un boolean qui indique si l'objet est encore existant ou non plus 
//__________________________________________________________________________________________________________
    public NosObjets(){} //constructeur par defaut 
 //__________________________________________________________________________________________________________    
	public NosObjets (String type , int n , int m ){	// constructeur parametre
		positionX = n;
		positionY = m;
		this.type = type ;
                           }
//__________________________________________________________________________________________________________
	NosObjets(int n, int m){ //constructeur parametre
		positionX=n;positionY=m;}
//__________________________________________________________________________________________________________
	protected void afficher() { // methode d'affichage
		if (type=="Arrive")  // Arrive => indique la case d'arrive 
			System.out.print ("A");
		else
			System.out.print("."); // les cases vides ont comme caractere "."
	}
//__________________________________________________________________________________________________________
	protected int get_positionx() { //retourne la position x de l'objet
		return (positionX);
	}
//__________________________________________________________________________________________________________	
	protected int get_positiony() { //retourne la position y de l'objet
		return (positionY);
	}
//__________________________________________________________________________________________________________	
	protected void deplacer(String a) {}
//__________________________________________________________________________________________________________	
	protected void rencontre (int lig_dest,int col_dest,NosObjets[][] laby,Timing objet) {}
//__________________________________________________________________________________________________________	
	protected void set_type(String a) // methode permet de modifier le type de l'objet
	 {
		 type=a;
	 }
//__________________________________________________________________________________________________________	
	protected String get_type()    // retourne le type de l'objet (Homme , Covid,Gel...)
	{
		return (type);
	}
//__________________________________________________________________________________________________________	
	public boolean est_exist() { // retourne l'attribut existe 
		return exist;}
//__________________________________________________________________________________________________________
	public int get_immunite() {return 100;}
//__________________________________________________________________________________________________________
	public boolean get_test() { return false;}
}
