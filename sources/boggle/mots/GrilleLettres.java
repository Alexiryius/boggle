package boggle.mots;

import boggle.jeu.Joueur;

/**
 * @author rogeza radixr
 * Classe construisant une grille de lettre
 */
public class GrilleLettres {

	private int tailleGrille;
	private char[][] tabCharGrille;
	private De de;

	public GrilleLettres() {
		this(4);
	}

	public GrilleLettres(int tailleGrille) {
		this.tailleGrille = tailleGrille;
		tabCharGrille = new char[tailleGrille][tailleGrille];
		this.remplirGrille();
	}

	public int getTailleGrille() {
		return tailleGrille;
	}

	public void setTabCharGrille(char[][] tabCharGrille) {
		this.tabCharGrille = tabCharGrille;
	}

	/**
	 * méthode qui remplit la grille de lettre à partir du De
	 */
	public void remplirGrille() {
		de = new De(tailleGrille);
		char[] lettres = de.getRetour();
		int compt = 0;
		for (int x = 0; x < tailleGrille; x++) {
			for (int y = 0; y < tailleGrille; y++) {
				tabCharGrille[x][y] = lettres[compt];
				compt++;
			}
		}
	}

	/** 
	 * le toString va afficher sur la console d'affichage
	 * le dessin et un tableau de lettre
	 */
	public String toString() {
		String affichage = "";
		String espaceLibre ="";
		String haut="\t\t         \\|||/\n"+"\t\t     	 (0 0)\n"+"\t\t,~ooO~~~~~( )~~~~~~~~~,\n"+"\t\t|                     |\n";
		String bas="\t\t|                     |\n"+"\t\t`~~~~~~~~~~~~~~~~~oo0~`\n"+"\t\t       |__| |__|\n"+"\t\t        ||   ||\n"+"\t\t       ooO   Ooo \n";
		
		switch (tailleGrille) {
		 case 4:
			 espaceLibre = "      ";
			   break;
		 case 5:        
			 espaceLibre =  "     ";
			 break;
		 case 6:        
			 espaceLibre =  "    ";
			 break;
		}
		
		for (int x = 0; x < tailleGrille; x++) {
			
			affichage += "\t\t|"+espaceLibre;
			for (int y = 0; y < tailleGrille; y++) {
				affichage += "|" + tabCharGrille[x][y] + "";
			}
			if (x < tailleGrille-1) {
				affichage += "|"+espaceLibre+"|\n";
			}
			else{
				affichage += "|"+espaceLibre+"|\n";
			}
		}
		
		affichage = haut + affichage + bas;
		return affichage;
		
	}

	public char[][] getTabCharGrille() {
		return tabCharGrille;
	}

}
