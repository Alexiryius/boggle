package boggle.mots;

/**
 * @author rogeza radixr
 */
public class GrilleLettres {
	
	private int tailleGrille ;
	private String [][] tabGrille ;
	private De de;
	
	public GrilleLettres(){
		this(4);
	}
	
	public GrilleLettres(int tailleGrille){
		this.tailleGrille = tailleGrille;
		tabGrille = new String[tailleGrille][tailleGrille];
		this.remplirGrille();
	}
	
	public int getTailleGrille() {
		return tailleGrille;
	}
	
	public String[][] getTabGrille() {
		return tabGrille;
	}
	
	public void setTabGrille(String[][] tabGrille) {
		this.tabGrille = tabGrille;
	}
	
	public void remplirGrille() {
		de = new De();
		String[] lettres = de.getRetour();
		int compt =0;
		for(int x = 0; x < tailleGrille;x++ ) {
			for(int y = 0; y < tailleGrille;y++ ) {
				tabGrille[x][y]= lettres[compt];
				compt ++;
			}
		}
	}
	public String toString() {
		String affichage = "";
		for (int x = 0; x < tailleGrille; x++) {
			for (int y = 0; y < tailleGrille; y++) {
				affichage += "| " + tabGrille[x][y] + " ";
			}
			affichage += "|\n";
		}
		return affichage;
	}

}
