package pack;

public class Labyrinthe {
	private NosObjets*[][] laby ;
	private int nb_ligne;
	private int nb_col;
	Labyrinthe(int lignes,int colonnes)
	{
		nb_ligne=lignes;
		nb_col=colonnes;
		laby=new NosObjets*[nb_ligne][nb_col];
	}
	public void affiche()
	{
		for (int i=0;i<nb_ligne;i++)
		{
			for (int j=0;j<nb_col;j++)
			{
				laby[i][j].affiche();
			}
			System.out.println();
		}
	}
	public void deplacer(String coup)
	{
		int lig_dep=something.get_positionx;
		int col_dep=something.get_positiony;
		int	code_move=laby[lig_dep][col_dep].deplacer(coup);
		if (code_move==1)//haut
		{
			int code_rencontre=laby[lig_dep][col_dep].rencontrer(lig_dep-1,col_dep);
			laby[lig_dep-1][col_dep]=laby[lig_dep][col_dep];
			laby[lig_dep][col_dep]=new NosObjets();
		}
		else if (code_move==2)//droit
		{
			int code_rencontre=laby[lig_dep][col_dep].rencontrer(lig_dep,col_dep+1);
			laby[lig_dep][col_dep+1]=laby[lig_dep][col_dep];
			laby[lig_dep][col_dep]=new NosObjets();			
		}
		else if (code_move==3)//bas
		{
			int code_rencontre=laby[lig_dep][col_dep].rencontrer(lig_dep+1,col_dep);
			laby[lig_dep+1][col_dep]=laby[lig_dep][col_dep];
			laby[lig_dep][col_dep]=new NosObjets();			
		}
		else if (code_move==4)//gauche
		{
			int code_rencontre=laby[lig_dep][col_dep].rencontrer(lig_dep-1,col_dep);
			laby[lig_dep-1][col_dep]=laby[lig_dep][col_dep];
			laby[lig_dep][col_dep]=new NosObjets();			
		}
		else System.out.println("illegal");
	}
	public boolean ligne_valide(int ligne ) {if(ligne>=0 && ligne<nb_ligne) return true;
    else {System.err.print("vous devez choisir un entier entre 0et"+nb_ligne);return false; }}

	public boolean colone_valide(int colone ) {if(colone>=0 && colone<nb_colone) return true;
	else {System.err.print("vous devez choisir un entier entre 0et"+nb_colone);return false; }}

	public boolean nombre_valide(int nb ) {if(nb<=(nb_ligne*nb_colone)) return true;
	else {System.err.print("vous devez choisir un entier entre 0et"+(nb_colone*nb_colone));return false; }}

	public boolean existeEspace(int nb) {int r=0;                     //verifier s'il existe encore des cases vide dans la labyrinthe
	for(int i=0;i<nb_ligne;i++) 
	{for(int j=0;j<nb_colone;j++)
	  {if(laby[i][j].get_caractere()=='.') r+=1;}}
	if(nb<=r)return true;
	else return false ;}

	public void initialisation(){ int ligne,colone,ligneA,coloneA,nbcovid,nbVfaible,nl,nc,nl1,nc1,nbgel,nl2,nc2,nbp,nl3,nc3;
	Scanner put = new Scanner(System.in);
	System.out.println("entrer les positions de votre case de d�part");//entrer les positions du case de d�part
	do{System.out.println("donner le n�ligne");
		ligne=put.nextInt();}
	while(this.ligne_valide(ligne)==false);

	do{System.out.println("donner le n�colone");
		colone=put.nextInt();}
	while(this.colone_valide(colone)==false);
	laby[ligne][colone].set_caractere('D'); // caractere 'D' signifie case de D�part
	laby[ligne][colone]=new Homme(ligne,colone);
	System.out.println("entrer les positions de votre case d'arriv�");//entrer les positions du case d'arriv�
	do{System.out.println("donner le n�ligne");
		ligneA=put.nextInt();}
	while(this.ligne_valide(ligneA)==false);

	do{System.out.println("donner le n�colone");
		coloneA=put.nextInt();}
	while(this.colone_valide(coloneA)==false);	
	laby[ligne][colone].set_caractere('X');// caractere 'X' signifie case d'arriv�

	do{System.out.println("donner le nombre des virus de type covid19");//entrer le nb des virus covid
		nbcovid=put.nextInt();}
	while((this.nombre_valide(nbcovid)==false)||(this.existeEspace(nbcovid)));                                      
	System.out.println("vous devez positioner les virus covid19");             //positionnement des virus covid19                         
	for(int i=0;i<nbcovid;i++) {         
		do{System.out.println("donner le n�ligne de virus covid19"+(i+1));
			nl=put.nextInt();}
		while(this.ligne_valide(nl)==false);      
		do{System.out.println("donner le n�colone de virus covid19"+(i+1));
			nc=put.nextInt();}
		while(this.colone_valide(nc)==false);
		laby[nl][nc]=new Covid19(nl,nc);
	}

	do{System.out.println("donner le nombre des virus faible ");//entrer le nb des virus faible
		nbVfaible=put.nextInt();}
	while((this.nombre_valide(nbVfaible)==false)||(this.existeEspace(nbVfaible)));                                      
	System.out.println("vous devez positioner les virus faible");                     //positionnement des virus faible                 
	for(int i=0;i<nbVfaible;i++) {         
		do{System.out.println("donner le n�ligne de virus faible"+(i+1));
			nl1=put.nextInt();}
		while(this.ligne_valide(nl1)==false);      
		do{System.out.println("donner le n�colone de virus faible"+(i+1));
			nc1=put.nextInt();}
		while(this.colone_valide(nc1)==false);
		laby[nl1][nc1]=new VirusFaible(nl1,nc1);}

	do{System.out.println("donner le nombre du gel d�sinfectant ");//entrer le nb du gel d�sinfectant
		nbgel=put.nextInt();}
	while((this.nombre_valide(nbgel)==false)||(this.existeEspace(nbgel)));                                      
	System.out.println("vous devez positioner le gel d�sinfectant");        //positionnement du gel d�sinfectant                
	for(int i=0;i<nbgel;i++) {         
		do{System.out.println("donner le n�ligne du gel d�sinfectant"+(i+1));
			nl2=put.nextInt();}
		while(this.ligne_valide(nl2)==false);      
		do{System.out.println("donner le n�colone du gel d�sinfectant"+(i+1));
			nc2=put.nextInt();}
		while(this.colone_valide(nc2)==false);
		laby[nl2][nc2]=new GelDesinfectant(nl2,nc2);}

	do{System.out.println("donner le nombre des potions d'�nergie ");//entrer le nb des potions d'�nergie 
		nbp=put.nextInt();}
	while((this.nombre_valide(nbp)==false)||(this.existeEspace(nbp)));                                      
	System.out.println("vous devez positioner les potions d'�nergie");        //positionnement des potions d'�nergie             
	for(int i=0;i<nbp;i++) {         
		do{System.out.println("donner le n�ligne du potion d'�nergie"+(i+1));
			nl3=put.nextInt();}
		while(this.ligne_valide(nl3)==false);      
		do{System.out.println("donner le n�colone du potion d'�nergie"+(i+1));
			nc3=put.nextInt();}
		while(this.colone_valide(nc3)==false);
			laby[nl3][nc3]=new PotionEnergie(nl3,nc3);}

}
}
