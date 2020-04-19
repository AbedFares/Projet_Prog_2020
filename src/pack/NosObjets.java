package pack;

public class NosObjets {
	protected int positionX , positionY ;
	protected String type ;//Covid19,PotionEnergie,FaibleVirus
    public NosObjets(){} 
	public NosObjets (String type , int n , int m ){	
		positionX = n;
		positionY = m;
		this.type = type ;
}
	NosObjets(int n, int m){ positionX=n;positionY=m;}
	protected void afficher() {
		if (type=="Arrive")
			System.out.print ("A");
		else
			System.out.print(".");
	}
	protected int get_positionx() {
		return (positionX);
	}
	protected int get_positiony() {
		return (positionY);
	}
	protected void deplacer(String a) {}
	 protected void rencontre (int lig_dest,int col_dest,NosObjets[][] laby) {}
	 protected void set_type(String a)
	 {
		 type=a;
	 }
	protected String get_type()
	{
		return (type);
	}
	protected boolean est_vivant() {return false;}
}
