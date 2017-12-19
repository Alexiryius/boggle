package boggle.mots;

/**
 * @author rogeza radixr
 */
public class GrilleLettres {
	
	private int tailleGrille ;
	private char [][] tabCharGrille;
	private De de;
	
	public GrilleLettres(){
		this(4);
	}
	
	public GrilleLettres(int tailleGrille){
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
		int compt =0;
		for(int x = 0; x < tailleGrille;x++ ) {
			for(int y = 0; y < tailleGrille;y++ ) {
				tabCharGrille[x][y]= lettres[compt];
				compt ++;
			}
		}
	}
	public String toString() {
		String affichage = "";
		for (int x = 0; x < tailleGrille; x++) {
			for (int y = 0; y < tailleGrille; y++) {
				affichage += "|" + tabCharGrille[x][y] + "";
			}
			affichage += "|\n";
		}
		return affichage;
	}

}
