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
}
