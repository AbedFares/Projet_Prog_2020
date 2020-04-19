package pack;

public class NosObjets {
	protected int lignes , colonnes ;
	protected int positionX , positionY ;
	protected String type ;
    public NosObjets(){} 
	public NosObjets (String type , int n , int m ){
		
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

