package boggle.mots;

/**
 * @author rogeza radixr
 *
 */
public class GrilleLettres {
	
	private int tailleGrille ;
	private int [][] tabGrille ;
	
	public GrilleLettres(){
		this(4);
	}
	
	public GrilleLettres(int tailleGrille){
		this.tailleGrille = tailleGrille;
		tabGrille = new int[tailleGrille][tailleGrille];
	}
	
	public int getTailleGrille() {
		return tailleGrille;
	}
	
	public int[][] getTabGrille() {
		return tabGrille;
	}
	
	public void setTabGrille(int[][] tabGrille) {
		this.tabGrille = tabGrille;
	}
	

}
