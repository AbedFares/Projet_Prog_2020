package pack;

import java.util.Scanner;
//import java.io.BufferedReader;
//import java.io.DataInputStream;
import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStreamReader;
//import java.util.*;
import java.util.Random;
public class Labyrinthe {
	
	private NosObjets[][] laby ;
	private int nb_ligne;
	private int nb_colone;
	private boolean termine;
//__________________________________________________________________________________________________________
	Labyrinthe(){} //constructeur par d�faut 
//__________________________________________________________________________________________________________	
	Labyrinthe(int lignes,int colonnes) // constructeur parametre
	{
		nb_ligne=lignes;
		nb_colone=colonnes;
		laby=new NosObjets[nb_ligne][nb_colone];
	}
//__________________________________________________________________________________________________________
	public void affiche() // methode d'affichage
	{
		for (int i=0;i<nb_ligne;i++)
		{
			for (int j=0;j<nb_colone;j++)
			{
				laby[i][j].afficher();
				System.out.print(" ");
			}
			System.out.println();
		}
	}
//__________________________________________________________________________________________________________
	public boolean test_termine(NosObjets homme)// methode qui verifie si le joueur est gagne ou non 
	{
		return laby[homme.positionX][homme.positionY].get_test();
	}
//__________________________________________________________________________________________________________
	public void mise_jour(String coup,Timing objet) // methode permettant la mise a jour de le labyrinthe selon le deplacement du joueur
	{
		int lig_dep=0;
		int col_dep=0;
		for (int i=0;i<nb_ligne;i++)
		{
			for (int j=0;j<nb_colone;j++)
			{
				if (laby[i][j].get_type()=="Homme")
				{
					lig_dep=i;
					col_dep=j;
				}
			}
		}
		//System.out.println(lig_dep+" " +col_dep);
		if( (coup.contentEquals("haut")) && (lig_dep-1>=0) )//haut
		{
			laby[lig_dep][col_dep].deplacer(coup);
			laby[lig_dep][col_dep].rencontre(lig_dep-1,col_dep,laby,objet);
			laby[lig_dep-1][col_dep]=laby[lig_dep][col_dep];
			laby[lig_dep][col_dep]=new NosObjets(".",lig_dep,col_dep);	
		}
		else if( (coup.contentEquals("droite")) && (col_dep+1<nb_colone) )//droite
		{
			laby[lig_dep][col_dep].deplacer(coup);
			laby[lig_dep][col_dep].rencontre(lig_dep,col_dep+1,laby,objet);
			laby[lig_dep][col_dep+1]=laby[lig_dep][col_dep];
			laby[lig_dep][col_dep]=new NosObjets(".",lig_dep,col_dep);			
		}
		else if( (coup.contentEquals("bas")) && (lig_dep+1<nb_ligne) )//bas
		{
			laby[lig_dep][col_dep].deplacer(coup);
			laby[lig_dep][col_dep].rencontre(lig_dep+1,col_dep,laby,objet);
			laby[lig_dep+1][col_dep]=laby[lig_dep][col_dep];
			laby[lig_dep][col_dep]=new NosObjets(".",lig_dep,col_dep);			
		}
		else if ((coup.contentEquals("gauche")) && (col_dep-1>=0) )//gauche
		{
			laby[lig_dep][col_dep].deplacer(coup);
			laby[lig_dep][col_dep].rencontre(lig_dep,col_dep-1,laby,objet);
			laby[lig_dep][col_dep-1]=laby[lig_dep][col_dep];
			laby[lig_dep][col_dep]=new NosObjets(".",lig_dep,col_dep);			
		}
		else {System.out.println("illegal");System.out.println(" ");}
	}
//__________________________________________________________________________________________________________
	public String lire_coup() // methode permettant la saisie de la direction du joueur 
	{
		   Scanner put = new Scanner(System.in);
		   String coup=put.nextLine();
		   while (!coup.contentEquals("haut") && !coup.contentEquals("bas") && !coup.contentEquals("gauche") && !coup.contentEquals("droite"))
		   {
			   coup=put.nextLine();
		   }
		   return coup;
		   
	}
//__________________________________________________________________________________________________________
	public boolean ligne_valide(int ligne ) //methode qui verifie  la validite de du numero de la ligne saisie
	{
		if(ligne>=0 && ligne<nb_ligne) 
			return true;
        else 
        {
        	System.err.println("vous devez choisir un entier entre 0 et "+ (nb_ligne-1));
        	return false; 
        }
	}
//__________________________________________________________________________________________________________
	public boolean colone_valide(int colone ) //methode qui verifie  la validite de du numero de la colonne saisie
	{
		if(colone>=0 && colone<nb_colone) 
			return true;
	    else 
	    {
	    	System.err.println("vous devez choisir un entier entre 0 et "+(nb_colone-1));
	    	return false; 
	    }
    }
//__________________________________________________________________________________________________________
	public boolean nombre_valide(int nb ) //methode qui verifie la validite du nombre saisie
	{
		if(nb<=(nb_ligne*nb_colone)) 
			return true;
	    else 
	    {
	    	System.err.println("vous devez choisir un entier entre 0 et"+(nb_ligne*nb_colone));
	    	return false;
	    }
	}
//__________________________________________________________________________________________________________
	public boolean existeEspace(int nb) //verifier s'il existe encore des cases vide dans la labyrinthe
	{
		int r=0;                     
	    for(int i=0;i<nb_ligne;i++) 
	    {
	    	for(int j=0;j<nb_colone;j++)
	        {
	    		if(laby[i][j].get_type()==".") 
	    			r+=1;
	    	}
	    }
	    if(nb<=r)
	    	return true;
	    else 
	    	return false ;
	  }
//__________________________________________________________________________________________________________
	public void initialisation() //methode initialiser avec saisie clavier des positions des virus et anti virus et leurs nombres
	{
	    Scanner put = new Scanner(System.in);
	    boolean test=true;
	    while (test)
	    {
	    	System.out.println("Entrer le nombre de lignes de la labyrinthe :");
	    	int nbre_ligne=put.nextInt();
	    	System.out.println("Entrer le nombre de colonnes de la labyrinthe :");
	    	int nbre_colone=put.nextInt();
	    	if (nbre_ligne>0 && nbre_colone>0)
	    	{
	    		laby= new NosObjets[nbre_ligne][nbre_colone];
	    		nb_ligne=nbre_ligne;
	    		nb_colone=nbre_colone;
	    		test=false;
	    	}
	    	else
	    	{
	    		System.err.println("Le nombre de ligne et le nombre de colonne doivent etre > 0 ");
	    	}
	    }
		for (int i=0;i<nb_ligne;i++)
		{
			for (int j=0;j<nb_colone;j++)
			{
				laby[i][j]=new NosObjets(".",i,j);
			}
		}
		int ligne,colone,ligneA,coloneA,nbcovid,nbVfaible,nl,nc,nl1,nc1,nbgel,nl2,nc2,nbp,nl3,nc3;
	    System.out.println("entrer les positions de votre case de depart");//entrer les positions du case de départ
	   do
	   {
		   System.out.println("donner le num de ligne");
		   ligne=put.nextInt();
		}
	   while(this.ligne_valide(ligne)==false);

	   do
	   {
		   System.out.println("donner le num de colone");
		   colone=put.nextInt();
		}
	   while(this.colone_valide(colone)==false);
	  // laby[ligne][colone].set_type("Homme"); // caractere 'D' signifie case de Départ // type Homme !
	   laby[ligne][colone]=new EspeceHumaine("Homme",ligne,colone);
	   boolean bo=true;
while (bo)
{
	   System.out.println("entrer les positions de votre case d'arrivee");//entrer les positions du case d'arrivé
	   do
	   {
		   System.out.println("donner le num de ligne");
		   ligneA=put.nextInt();
		}
	   while(this.ligne_valide(ligneA)==false);

	do
	  {
		  System.out.println("donner le num de colonne");
		  coloneA=put.nextInt();
	  }
	  while(this.colone_valide(coloneA)==false);	
	   if (laby[ligneA][coloneA].get_type()==".") {laby[ligneA][coloneA].set_type("Arrive");bo=false;}// caractere 'X' signifie case d'arrivé // type "Arrivee" signifie case d'arrive
	   else { System.out.println("Cette case n'est pas vide!");}
}
	  do
	  {
		  System.out.println("donner le nombre des virus de type covid19");//entrer le nb des virus covid
		  nbcovid=put.nextInt();
	  }
	  while((this.nombre_valide(nbcovid)==false)||(!this.existeEspace(nbcovid)));                                      
	  if (nbcovid!=0) System.out.println("vous devez positioner les virus covid19");             //positionnement des virus covid19                         
	  for(int i=0;i<nbcovid;i++) 
	      {         
		     do
		      {
		    	 System.out.println("donner le num de ligne de virus covid19"+(i+1));
			     nl=put.nextInt();
			   }
		     while(this.ligne_valide(nl)==false);      
		     do
		     {
		    	 System.out.println("donner le num de colonne de virus covid19"+(i+1));
			     nc=put.nextInt();
			 }
		     while(this.colone_valide(nc)==false);
		     if (laby[nl][nc].get_type()==".") laby[nl][nc]=new Covid19(nl,nc);
			 else { i--; System.out.println("Cette case n'est pas vide!");}
	      }

	  do
	  {
		  System.out.println("donner le nombre des virus faible ");//entrer le nb des virus faible
		  nbVfaible=put.nextInt();
	  }
	  while((this.nombre_valide(nbVfaible)==false)||(!this.existeEspace(nbVfaible)));                                      
	  if (nbVfaible!=0) System.out.println("vous devez positioner les virus faible");                     //positionnement des virus faible                 
	  for(int i=0;i<nbVfaible;i++) 
	     {         
		     do
		       {
		    	 System.out.println("donner le num de ligne de virus faible"+(i+1));
			     nl1=put.nextInt();
			    }
		     while(this.ligne_valide(nl1)==false);      
		     do
		       {
		    	 System.out.println("donner le num de colone de virus faible"+(i+1));
			     nc1=put.nextInt();
			     }
		     while(this.colone_valide(nc1)==false);
		    if (laby[nl1][nc1].get_type()==".") laby[nl1][nc1]=new VirusFaible(nl1,nc1);
			else { i--; System.out.println("Cette case n'est pas vide!");}
		  }

	do
	 {
		System.out.println("donner le nombre du gel desinfectant ");//entrer le nb du gel désinfectant
		nbgel=put.nextInt();
     }
	while((this.nombre_valide(nbgel)==false)||(!this.existeEspace(nbgel)));                                      
	if (nbgel!=0) System.out.println("vous devez positioner le gel desinfectant");        //positionnement du gel désinfectant                
	for(int i=0;i<nbgel;i++) 
	{         
		do
		{
			System.out.println("donner le num de ligne du gel desinfectant"+(i+1));
			nl2=put.nextInt();
		}
		while(this.ligne_valide(nl2)==false);      
		do
		{
			System.out.println("donner le num de colone du gel desinfectant"+(i+1));
			nc2=put.nextInt();
		}
		while(this.colone_valide(nc2)==false);
		if (laby[nl2][nc2].get_type()==".") laby[nl2][nc2]=new GelDesinfectant(nl2,nc2);
		else { i--; System.out.println("Cette case n'est pas vide!");}
	}

	do
	 {
		System.out.println("donner le nombre des potions d'energie ");//entrer le nb des potions d'énergie 
		nbp=put.nextInt();
	  }
	while((this.nombre_valide(nbp)==false)||(!this.existeEspace(nbp)));                                      
	if (nbp!=0) System.out.println("vous devez positioner les potions d'energie");        //positionnement des potions d'énergie             
	for(int i=0;i<nbp;i++) 
	    {         
		  do
		    {
			  System.out.println("donner le num de ligne du potion d'energie"+(i+1));
			  nl3=put.nextInt();
			 }
		  while(this.ligne_valide(nl3)==false);      
		  do
		    {
			  System.out.println("donner le num de colone du potion d'energie"+(i+1));
			  nc3=put.nextInt();
			 }
		  while(this.colone_valide(nc3)==false);
		  if (laby[nl3][nc3].get_type()==".") laby[nl3][nc3]=new PotionEnergie(nl3,nc3);
		  else { i--; System.out.println("Cette case n'est pas vide!");}
	    }

	}
//__________________________________________________________________________________________________________
	public void initialiseRandom()//methode initialiser avec positionnement al�atoire des virus et anti virus
	{
	    Scanner put = new Scanner(System.in);
	    System.out.println("Entrer le nombre de lignes de la labyrinthe :");
		int nbre_ligne=put.nextInt();
		System.out.println("Entrer le nombre de colonnes de la labyrinthe :");
		int nbre_colone=put.nextInt();
		if (nbre_ligne>0 && nbre_colone>0)
		{
			laby= new NosObjets[nbre_ligne][nbre_colone];
			nb_ligne=nbre_ligne;
			nb_colone=nbre_ligne;
		}
		else
		{
			System.err.println("Le nombre de ligne et le nombre de colonne doivent etre > 0 ");
		}
		for (int i=0;i<nb_ligne;i++)
		{
			for (int j=0;j<nb_colone;j++)
			{
				laby[i][j]=new NosObjets(".",i,j);
			}
		}
		int ligne,colone,ligneA,coloneA,nbcovid,nbVfaible,nl,nc,nl1,nc1,nbgel,nl2,nc2,nbp,nl3,nc3;
	    System.out.println("entrer les positions de votre case de depart");//entrer les positions du case de départ
	   do
	   {
		   System.out.println("donner le num de ligne");
		   ligne=put.nextInt();
		}
	   while(this.ligne_valide(ligne)==false);

	   do
	   {
		   System.out.println("donner le n°colone");
		   colone=put.nextInt();
		}
	   while(this.colone_valide(colone)==false);
	   //laby[ligne][colone].set_type('D'); // caractere 'D' signifie case de Départ
	   laby[ligne][colone]=new EspeceHumaine("Homme",ligne,colone);
	   System.out.println("entrer les positions de votre case d'arrivé");//entrer les positions du case d'arrivé
	   do
	   {
		   System.out.println("donner le n°ligne");
		   ligneA=put.nextInt();
		}
	   while(this.ligne_valide(ligneA)==false);

	  do
	  {
		  System.out.println("donner le n°colone");
		  coloneA=put.nextInt();
	  }
	  while(this.colone_valide(coloneA)==false);	
	   laby[ligneA][coloneA].set_type("Arrive");//String "Arrive" signifie case d'arrivé
	   
	  do
	  {
		  System.out.println("donner le nombre des virus de type covid19");//entrer le nb des virus covid
		  nbcovid=put.nextInt();
	     //s System.out.println("s");

	  }
	  while( (this.nombre_valide(nbcovid)==false) || (!this.existeEspace(nbcovid)) );                                      

	   //positionnement des virus covid19 aleatoirement   

	  Random rand = new Random();
	  for(int i=0;i<nbcovid;i++) 
	      {         
            nl = rand.nextInt(nb_ligne); 
            nc = rand.nextInt(nb_colone);
            if (laby[nl][nc].get_type().contentEquals("."))
            	laby[nl][nc]=new Covid19(nl,nc);
            else 
            	i--;
	      }

	  do
	  {
		  System.out.println("donner le nombre des virus faible ");//entrer le nb des virus faible
		  nbVfaible=put.nextInt();
	  }
	  while((this.nombre_valide(nbVfaible)==false)||(!this.existeEspace(nbVfaible)));                                      
	 
	  //positionnement des virus faible                 
	  for(int i=0;i<nbVfaible;i++) 
	     {         
          nl1 = rand.nextInt(nb_ligne); 
          nc1 = rand.nextInt(nb_colone);
          if (laby[nl1][nc1].get_type().contentEquals("."))
          	laby[nl1][nc1]=new VirusFaible(nl1,nc1);
          else 
          	i--;
		  }

	do
	 {
		System.out.println("donner le nombre du gel désinfectant ");//entrer le nb du gel désinfectant
		nbgel=put.nextInt();
     }
	while((this.nombre_valide(nbgel)==false)||(!this.existeEspace(nbgel)));                                      
	 
	//positionnement du gel désinfectant                
	for(int i=0;i<nbgel;i++) 
	  {         
        nl2 = rand.nextInt(nb_ligne); 
        nc2= rand.nextInt(nb_colone);
        if (laby[nl2][nc2].get_type().contentEquals("."))
        	laby[nl2][nc2]=new GelDesinfectant(nl2,nc2);
        else 
        	i--;
	   }

	do
	 {
		System.out.println("donner le nombre des potions d'énergie ");//entrer le nb des potions d'énergie 
		nbp=put.nextInt();
	  }
	while((this.nombre_valide(nbp)==false)||(!this.existeEspace(nbp)));                                      

	//positionnement des potions d'énergie             
	for(int i=0;i<nbp;i++) 
	    {         
        nl3 = rand.nextInt(nb_ligne); 
        nc3 = rand.nextInt(nb_colone);
        if (laby[nl3][nc3].get_type().contentEquals("."))
        	laby[nl3][nc3]=new PotionEnergie(nl3,nc3);
        else 
        	i--;
		}

	}
//__________________________________________________________________________________________________________
	public NosObjets Homme_dep() {  //methode qui cherche l'emplacement de l'etre humain
		for (int i=0;i<nb_ligne;i++)
		{
			for (int j=0;j<nb_colone;j++)
			{
				if (laby[i][j].get_type()=="Homme")
				{
					return laby[i][j];
				}
			}
		}
		return laby[0][0];
	}
//__________________________________________________________________________________________________________
	public void initialiseFichier() //methode initialiser a partir d'un fichier contanant toutes les informations necessaires
	   {
			String nomfich;
			// int ligne,colone,ligneA,coloneA,nbcovid,nbVfaible,nl,nc,nl1,nc1,nbgel,nl2,nc2,nbp,nl3,nc3;
				
			Scanner sc= new Scanner(System.in);
			System.out.print("donnez le nom du fichier a lister : ") ; 
			nomfich= sc.next() ;
			nomfich+=".txt";
			 
			try{     
				Scanner scan=new Scanner(new File(nomfich));
			 	/*while(scan.hasNextInt()) {
			 		System.out.println(scan.nextInt());
			 	}*/
				int nbre_ligne=scan.nextInt();
				int nbre_colone=scan.nextInt();
				if (nbre_ligne>0 && nbre_colone>0)
				{
					laby= new NosObjets[nbre_ligne][nbre_colone];
				}
				else
				{
					System.err.println("Le nombre de ligne et le nombre de colonne doivent etre > 0 ");
				}
				nb_ligne=nbre_ligne;
				nb_colone=nbre_colone;
				for (int i=0;i<nb_ligne;i++)
				{
					for (int j=0;j<nb_colone;j++)
					{
						laby[i][j]=new NosObjets(".",i,j);
					}
				}
				int dep_lig=scan.nextInt();
				int dep_col=scan.nextInt();
				if(this.ligne_valide(dep_lig)&&this.ligne_valide(dep_col))
				{laby[dep_lig][dep_col]=new EspeceHumaine("Homme",dep_lig,dep_col);}//case de depart
				else
				{
					System.out.println("Le positionnement de la case de depart est invalide");
					System.exit(0);
				}
					
				int ligneA=scan.nextInt();
				int coloneA=scan.nextInt();
				if(this.ligne_valide(ligneA)&&this.ligne_valide(coloneA))
				{laby[ligneA][coloneA].set_type("Arrive");}// case d'arrive
				else
				{
					System.out.println("Le positionnement de la case d'arrivee est invalide");
					System.exit(0);
				}
				int nbcovid=scan.nextInt();
				int i=0;
				if (this.nombre_valide(nbcovid) && this.existeEspace(nbcovid)) {
					while(i<nbcovid)
					{
						int nl=scan.nextInt();
						int nc=scan.nextInt();
						laby[nl][nc]=new Covid19(nl,nc);
						if(this.ligne_valide(nl)&&this.ligne_valide(nc))
						{laby[nl][nc]=new Covid19(nl,nc);}
						else
						{
							System.out.println("Le positionnement de Covid19 est invalide");
							System.exit(0);
						}
						i++;
					}
				}
				else
				{
					System.out.println("Le nombre de COVID19 est invalide");
					System.exit(0);
				}
				int nbVfaible=scan.nextInt();
				int j=0;
				if (this.nombre_valide(nbVfaible)&& this.existeEspace(nbVfaible))
				{
					while(j<nbVfaible)
					{
						int nl1=scan.nextInt();
						int nc1=scan.nextInt();
						if(this.ligne_valide(nl1)&&this.ligne_valide(nc1))
						{laby[nl1][nc1]=new VirusFaible(nl1,nc1);}
						else
						{
							System.out.println("Le positionnement de Virus Faible est invalide");
							System.exit(0);
						}
						j++;
					}
				}
				else
				{
					System.out.println("Le nombre de virus faible est invalide");
					System.exit(0);
				}
				int nbgel=scan.nextInt();
				int a=0;
				if (this.nombre_valide(nbgel)&& this.existeEspace(nbgel))
				{
					while(a<nbgel)
					{
						int nl2=scan.nextInt();
						int nc2= scan.nextInt();
						if(this.ligne_valide(nl2)&&this.ligne_valide(nc2))
						{laby[nl2][nc2]=new GelDesinfectant(nl2,nc2);}
						else
						{
							System.out.println("Le positionnement de gel Desinfectant est invalide");
							System.exit(0);
						}
						a++;
					}
				}
				else
				{
					System.out.println("Le nombre de gel desinfectant est invalide");
					System.exit(0);
				}
				int nbp=scan.nextInt();
				int b=0;
				if (this.nombre_valide(nbp)&& this.existeEspace(nbp))
				{
					while(b<nbp)
					{
						int nl3=scan.nextInt();
						int nc3=scan.nextInt();
						if(this.ligne_valide(nl3)&&this.ligne_valide(nc3))
						{laby[nl3][nc3]=new PotionEnergie(nl3,nc3);}
						else
						{
							System.out.println("Le positionnement de potion d'energie est invalide");
							System.exit(0);
						}
						b++;
					}
				}
				else
				{
					System.out.println("Le nombre de potion energie est invalide");
					System.exit(0);
				}
			}
			catch (Exception e){
				System.out.println(e.toString());
					
			}
		 
	   }
//__________________________________________________________________________________________________________
	public void choisirgenre() { //methode permettant au joueur de choisir entre homme femme ou enfant 
		System.out.println("Vous etes (enfant/femme/homme)");
	    Scanner put = new Scanner(System.in);
		   String choix=put.nextLine();
		   while (!choix.contentEquals("homme") && !choix.contentEquals("femme") && !choix.contentEquals("enfant"))
		   {
			   choix=put.nextLine();
		   }
		   if(choix.contentEquals("enfant")) {System.out.println ("niveau des enfants");
			   this.initialisation();}
		   else {System.out.println ("Niveau des adultes");
		   choisirMode();}
		   
		   
	}
//__________________________________________________________________________________________________________	
	public void choisirMode() { //methode permet de choisir le mode de saisie des donnees du jeux 
			
		    System.out.println("le Mode du jeux (fichier/clavier/aleatoire)");
		    Scanner put = new Scanner(System.in);
			   String choix1=put.nextLine();
			   while (!choix1.contentEquals("fichier") && !choix1.contentEquals("clavier") && !choix1.contentEquals("aleatoire"))
			   {
				   choix1=put.nextLine();
			   }
			   if(choix1.contentEquals("fichier")) {this.initialiseFichier();}
			   else if(choix1.contentEquals("clavier")) {this.initialisation();}
			   else if(choix1.contentEquals("aleatoire")) {this.initialiseRandom();}
	   }

//__________________________________________________________________________________________________________
	public static void main(String args[]) {

		Labyrinthe maze=new Labyrinthe();
		Timing objet=new Timing();
		maze.choisirgenre();
		
		//maze.choisirMode();
		//maze.initialisation();
		//maze.initialiseRandom();
		NosObjets Homme;
		maze.affiche();
		Homme=maze.Homme_dep();
		String coup;
		while (Homme.est_exist() && !maze.test_termine(Homme) ) {
			coup=maze.lire_coup();
			maze.mise_jour(coup,objet);
			maze.affiche();
		}
		if (!Homme.est_exist())
			{
				System.out.println("  \u001b[1;31mPERDUE!!!!\u001b[0m");
				objet.set_gel_intime(true);
			}
		else if (maze.test_termine(Homme))
			System.out.println("  \u001b[1;32mGAGNANT\u001b[0m");
		return;
		
		
	}
};

	
