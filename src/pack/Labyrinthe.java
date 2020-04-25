package pack;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Random;


public class Labyrinthe {
	private NosObjets[][] laby ;
	private int nb_ligne;
	private int nb_colone;
	private boolean termine;
	
	Labyrinthe(int lignes,int colonnes)
	{
		nb_ligne=lignes;
		nb_colone=colonnes;
		laby=new NosObjets[nb_ligne][nb_colone];
	}
	//___________________________________________________________________________________________________________________________________
	public void affiche()
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
	//___________________________________________________________________________________________________________________________________
	public boolean test_termine(NosObjets homme)
	{
		return laby[homme.positionX][homme.positionY].get_test();
	}
	//_______________________________________________________________________________________________________________________________________
	public void mise_jour(String coup,Timing objet)
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
	//__________________________________________________________________________________________________________________________________
	public String lire_coup()
	{
		   Scanner put = new Scanner(System.in);
		   String coup=put.nextLine();
		   while (!coup.contentEquals("haut") && !coup.contentEquals("bas") && !coup.contentEquals("gauche") && !coup.contentEquals("droite"))
		   {
			   coup=put.nextLine();
		   }
		   return coup;
		   
	}
//________________________________________________________________________________________________________________________	
	public boolean ligne_valide(int ligne ) 
	{
		if(ligne>=0 && ligne<nb_ligne) 
			return true;
        else 
        {
        	System.err.print("vous devez choisir un entier entre 0et"+nb_ligne);
        	return false; 
        }
	}
//________________________________________________________________________________________________________
	public boolean colone_valide(int colone ) 
	{
		if(colone>=0 && colone<nb_colone) 
			return true;
	    else 
	    {
	    	System.err.print("vous devez choisir un entier entre 0et"+nb_colone);
	    	return false; 
	    }
    }
//________________________________________________________________________________________________________
	public boolean nombre_valide(int nb ) 
	{
		if(nb<=(nb_ligne*nb_colone)) 
			return true;
	    else 
	    {
	    	System.err.print("vous devez choisir un entier entre 0 et"+(nb_ligne*nb_colone));
	    	return false;
	    }
	}
//________________________________________________________________________________________________________
	public boolean existeEspace(int nb) 
	{
		int r=0;                     //verifier s'il existe encore des cases vide dans la labyrinthe
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
//_____________________________________________________________________________________________________________________________________
	public void initialisation()
	{
		for (int i=0;i<nb_ligne;i++)
		{
			for (int j=0;j<nb_colone;j++)
			{
				laby[i][j]=new NosObjets(".",i,j);
			}
		}
		int ligne,colone,ligneA,coloneA,nbcovid,nbVfaible,nl,nc,nl1,nc1,nbgel,nl2,nc2,nbp,nl3,nc3;
	    Scanner put = new Scanner(System.in);
	    System.out.println("entrer les positions de votre case de depart");//entrer les positions du case de depart
	   do
	   {
		   System.out.println("donner le num ligne");
		   ligne=put.nextInt();
		}
	   while(this.ligne_valide(ligne)==false);

	   do
	   {
		   System.out.println("donner le num colone");
		   colone=put.nextInt();
		}
	   while(this.colone_valide(colone)==false);
	  // laby[ligne][colone].set_type("Homme"); // caractere 'D' signifie case de Depart // type Homme !
	   laby[ligne][colone]=new EspeceHumaine("Homme",ligne,colone);
	   System.out.println("entrer les positions de votre case d'arrivee");//entrer les positions du case d'arrivee
	   do
	   {
		   System.out.println("donner le num ligne");
		   ligneA=put.nextInt();
		}
	   while(this.ligne_valide(ligneA)==false);

	  do
	  {
		  System.out.println("donner le num colone");
		  coloneA=put.nextInt();
	  }
	  while(this.colone_valide(coloneA)==false);	
	   laby[ligneA][coloneA].set_type("Arrive");// caractere 'X' signifie case d'arrivÃ© // type "Arrivee" signifie case d'arrive

	  do
	  {
		  System.out.println("donner le nombre des virus de type covid19");//entrer le nb des virus covid
		  nbcovid=put.nextInt();
	  }
	  while((this.nombre_valide(nbcovid)==false)||(!this.existeEspace(nbcovid)));                                      
	 if(nbcovid!=0) { System.out.println("vous devez positioner les virus covid19");   }         //positionnement des virus covid19                         
	  for(int i=0;i<nbcovid;i++) 
	      {         
		     do
		      {
		    	 System.out.println("donner le num ligne de virus covid19"+(i+1));
			     nl=put.nextInt();
			   }
		     while(this.ligne_valide(nl)==false);      
		     do
		     {
		    	 System.out.println("donner le num colone de virus covid19"+(i+1));
			     nc=put.nextInt();
			 }
		     while(this.colone_valide(nc)==false);
		     laby[nl][nc]=new Covid19(nl,nc);
	      }

	  do
	  {
		  System.out.println("donner le nombre des virus faible ");//entrer le nb des virus faible
		  nbVfaible=put.nextInt();
	  }
	  while((this.nombre_valide(nbVfaible)==false)||(!this.existeEspace(nbVfaible)));                                      
	  if(nbVfaible!=0) {System.out.println("vous devez positioner les virus faible"); }                    //positionnement des virus faible                 
	  for(int i=0;i<nbVfaible;i++) 
	     {         
		     do
		       {
		    	 System.out.println("donner le num ligne de virus faible"+(i+1));
			     nl1=put.nextInt();
			    }
		     while(this.ligne_valide(nl1)==false);      
		     do
		       {
		    	 System.out.println("donner le num colone de virus faible"+(i+1));
			     nc1=put.nextInt();
			     }
		     while(this.colone_valide(nc1)==false);
		    laby[nl1][nc1]=new VirusFaible(nl1,nc1);
		  }

	do
	 {
		System.out.println("donner le nombre du gel desinfectant ");//entrer le nb du gel desinfectant
		nbgel=put.nextInt();
     }
	while((this.nombre_valide(nbgel)==false)||(!this.existeEspace(nbgel)));                                      
	if(nbgel!=0) {System.out.println("vous devez positioner le gel desinfectant");   }     //positionnement du gel desinfectant                
	for(int i=0;i<nbgel;i++) 
	{         
		do
		{
			System.out.println("donner le num ligne du gel desinfectant"+(i+1));
			nl2=put.nextInt();
		}
		while(this.ligne_valide(nl2)==false);      
		do
		{
			System.out.println("donner le num colone du gel desinfectant"+(i+1));
			nc2=put.nextInt();
		}
		while(this.colone_valide(nc2)==false);
		laby[nl2][nc2]=new GelDesinfectant(nl2,nc2);
	 }

	do
	 {
		System.out.println("donner le nombre des potions d'energie ");//entrer le nb des potions d'energie 
		nbp=put.nextInt();
	  }
	while((this.nombre_valide(nbp)==false)||(!this.existeEspace(nbp)));                                      
	if(nbp!=0) {System.out.println("vous devez positioner les potions d'energie");}        //positionnement des potions d'energie             
	for(int i=0;i<nbp;i++) 
	    {         
		  do
		    {
			  System.out.println("donner le num ligne du potion d'energie"+(i+1));
			  nl3=put.nextInt();
			 }
		  while(this.ligne_valide(nl3)==false);      
		  do
		    {
			  System.out.println("donner le num colone du potion d'energie"+(i+1));
			  nc3=put.nextInt();
			 }
		  while(this.colone_valide(nc3)==false);
		  laby[nl3][nc3]=new PotionEnergie(nl3,nc3);
		}

	}
	//_______________________________________________________________________________________________________________________________________
	public void initialiseRandom()//methode initialiser avec positionnement aléatoire des virus et anti virus
	{ 
		for (int i=0;i<nb_ligne;i++)
		{
			for (int j=0;j<nb_colone;j++)
			{
				laby[i][j]=new NosObjets(".",i,j);
			}
		}
		int ligne,colone,ligneA,coloneA,nbcovid,nbVfaible,nl,nc,nl1,nc1,nbgel,nl2,nc2,nbp,nl3,nc3;
	    Scanner put = new Scanner(System.in);
	    System.out.println("entrer les positions de votre case de depart");//entrer les positions du case de depart
	   do
	   {
		   System.out.println("donner le num ligne");
		   ligne=put.nextInt();
		}
	   while(this.ligne_valide(ligne)==false);

	   do
	   {
		   System.out.println("donner le num colone");
		   colone=put.nextInt();
		}
	   while(this.colone_valide(colone)==false);
	   //laby[ligne][colone].set_type('D'); // caractere 'D' signifie case de Depart
	   laby[ligne][colone]=new EspeceHumaine("Homme",ligne,colone);
	   System.out.println("entrer les positions de votre case d'arrivee");//entrer les positions du case d'arrivee
	   do
	   {
		   System.out.println("donner le num ligne");
		   ligneA=put.nextInt();
		}
	   while(this.ligne_valide(ligneA)==false);

	  do
	  {
		  System.out.println("donner le num colone");
		  coloneA=put.nextInt();
	  }
	  while(this.colone_valide(coloneA)==false);	
	   laby[ligneA][coloneA].set_type("Arrive");//String "Arrive" signifie case d'arrive
	   
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
		System.out.println("donner le nombre du gel desinfectant ");//entrer le nb du gel desinfectant
		nbgel=put.nextInt();
     }
	while((this.nombre_valide(nbgel)==false)||(!this.existeEspace(nbgel)));                                      
	 
	//positionnement du gel desinfectant                
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
		System.out.println("donner le nombre des potions d'energie ");//entrer le nb des potions d'energie 
		nbp=put.nextInt();
	  }
	while((this.nombre_valide(nbp)==false)||(!this.existeEspace(nbp)));                                      

	//positionnement des potions d'energie             
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
	//________________________________________________________________________________________________________________________________
	public NosObjets Homme_dep() {
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
		return laby[0][0];}
	//________________________________________________________________________________________________________________________________	
		
	public void initialiseFichier() 
	   {String nomfich;
		 for (int i=0;i<nb_ligne;i++)
			{
				for (int j=0;j<nb_colone;j++)
				{
					laby[i][j]=new NosObjets(".",i,j);
				}}
		// int ligne,colone,ligneA,coloneA,nbcovid,nbVfaible,nl,nc,nl1,nc1,nbgel,nl2,nc2,nbp,nl3,nc3;
				
		 Scanner sc= new Scanner(System.in);
			 System.out.print("donnez le nom du fichier a lister : ") ; 
			nomfich= sc.next() ;
			 nomfich+=".txt";
			 
		 try{     
			      FileInputStream flux=new FileInputStream(nomfich);
					//InputStream flux=new FileInputStream("test.txt"); 
			
		            InputStreamReader lecture=new InputStreamReader(flux);
					BufferedReader buff=new BufferedReader(lecture);
					String l;
					
					l=buff.readLine(); 
					int ligne= Integer.parseInt(l);
				    l=buff.readLine();
					int colone= Integer.parseInt(l);
					if(this.ligne_valide(ligne)&&this.ligne_valide(colone))
					{laby[ligne][colone]=new EspeceHumaine("Homme",ligne,colone);}//case de depart
					
					l=buff.readLine();
					int ligneA= Integer.parseInt(l);
					l=buff.readLine();
					int coloneA= Integer.parseInt(l);
					if(this.ligne_valide(ligneA)&&this.ligne_valide(coloneA))
					{laby[ligneA][coloneA].set_type("Arrive");}// case d'arrive
					
					l=buff.readLine();
					int nbcovid= Integer.parseInt(l);
					int i=0;
					while(i<nbcovid)
					{
						l=buff.readLine();
						int nl= Integer.parseInt(l);
						l=buff.readLine();
						int nc= Integer.parseInt(l);
						laby[nl][nc]=new Covid19(nl,nc);
						i++;
					}
					
					l=buff.readLine();
					int nbVfaible= Integer.parseInt(l);
					int j=0;
					while(j<nbVfaible)
					{
						l=buff.readLine();
						int nl1= Integer.parseInt(l);
						l=buff.readLine();
						int nc1= Integer.parseInt(l);
						laby[nl1][nc1]=new VirusFaible(nl1,nc1);
						j++;
					}
					
					l=buff.readLine();
					int nbgel= Integer.parseInt(l);
					int a=0;
					while(a<nbgel)
					{
						l=buff.readLine();
						int nl2= Integer.parseInt(l);
						l=buff.readLine();
						int nc2= Integer.parseInt(l);
						laby[nl2][nc2]=new GelDesinfectant(nl2,nc2);
						a++;
					}
					
					l=buff.readLine();
					int nbp= Integer.parseInt(l);
					int b=0;
					while(b<nbp)
					{
						l=buff.readLine();
						int nl3= Integer.parseInt(l);
						l=buff.readLine();
						int nc3= Integer.parseInt(l);
						laby[nl3][nc3]=new PotionEnergie(nl3,nc3);
						b++;
					}
					
					
					
					buff.close(); 
					}		
					catch (Exception e){
					System.out.println(e.toString());
					}
		 
	   }
	   
//_____________________________________________________________________________________________________________________________________	   
	   public void choisirMode() {
		
		    System.out.println("le Mode du jeux (fichier/clavier/aleatoire)");
		    Scanner put = new Scanner(System.in);
			   String choix=put.nextLine();
			   while (!choix.contentEquals("fichier") && !choix.contentEquals("clavier") && !choix.contentEquals("aleatoire"))
			   {
				   choix=put.nextLine();
			   }
			   if(choix.contentEquals("fichier")) {this.initialiseFichier();}
			   else if(choix.contentEquals("clavier")) {this.initialisation();}
			   else if(choix.contentEquals("aleatoire")) {this.initialiseRandom();}
	   }
		
//-----------------------------------------------------------------------------		
	public static void main(String args[]) {

		/*int nbligne, nbcolone;
	    Scanner put = new Scanner(System.in);
	    System.out.println("choisir les dimesiions de votre espace du jeux");//entrer les positions du case de depart
	   do
	   {
		   System.out.println("donner le nombre de  ligne");
		   nbligne=put.nextInt();
		}
	   while(nbligne >50);

	   do
	   {
		   System.out.println("donner le nombre de  colone");
		   nbcolone=put.nextInt();
		}
	   while(nbcolone >50);
	   Labyrinthe maze=new Labyrinthe(nbligne,nbcolone);*/
		 
		Labyrinthe maze=new Labyrinthe(4,4);
		System.out.println("Labyrinthe pour le moment a 4 lignes et 4 colonnes just pour tester");
	
		Timing objet=new Timing();
		maze.choisirMode();
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

	
