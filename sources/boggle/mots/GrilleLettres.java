package boggle.mots;

/**
 * @author rogeza radixr
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

	public char[][] getTabCharGrille() {
		return tabCharGrille;
	}

	public void setTabCharGrille(char[][] tabCharGrille) {
		this.tabCharGrille = tabCharGrille;
	}

	public void remplirGrille() {
		de = new De();
		char[] lettres = de.getRetour();
		int compt = 0;
		for (int x = 0; x < tailleGrille; x++) {
			for (int y = 0; y < tailleGrille; y++) {
				tabCharGrille[x][y] = lettres[compt];
				compt++;
			}
		}
	}

	public String toString() {
		String affichage = "";
		String haut="\t\t         \\|||/\n"+"\t\t     	 (0 0)\n"+"\t\t,~ooO~~~~~( )~~~~~~~~~,\n"+"\t\t|                     |\n";
		String bas="\t\t|                     |\n"+"\t\t`~~~~~~~~~~~~~~~~~oo0~`\n"+"\t\t       |__| |__|\n"+"\t\t        ||   ||\n"+"\t\t       ooO   Ooo \n";
		
		for (int x = 0; x < tailleGrille; x++) {
			affichage += "\t\t|      ";
			for (int y = 0; y < tailleGrille; y++) {
				affichage += "|" + tabCharGrille[x][y] + "";
			}
			if (x < tailleGrille-1) {
				affichage += "|      |\n";
			}
			else{
				affichage += "|      |\n";
			}
		}
		affichage = haut + affichage + bas;
		return affichage;
	}

}
