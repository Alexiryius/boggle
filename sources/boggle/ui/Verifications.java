package boggle.ui;

import boggle.mots.*;

public class Verifications {
	GrilleLettres grilleLettres;
	String[][] grille;
	int tailleGrille;
	int positionCurseur = 0;

	public Verifications() {
		grilleLettres = new GrilleLettres();
		tailleGrille = grilleLettres.getTailleGrille();
		grille = grilleLettres.getTabGrille();
		grilleLettres.remplirGrille();

	}

	public int getPositionCurseur() {
		return positionCurseur;
	}

	public void setPositionCurseur(int positionCurseur) {
		this.positionCurseur = positionCurseur;
	}

	public void affichageGrille() {
		for (int x = 0; x < tailleGrille; x++) {
			for (int y = 0; y < tailleGrille; y++) {
				if (((5 * x) + y) == positionCurseur) {
					grille[x][y] = "[" + grille[x][y] + "]";
				} else if (grille[x][y].equals("M") || grille[x][y].equals("W")) {
					grille[x][y] = " " + grille[x][y];
				} else {
					grille[x][y] = " " + grille[x][y] + " ";
				}
				if (grille[x][y].equals("[M]") || grille[x][y].equals("[N]") || grille[x][y].equals("[W]")||grille[x][y].equals("[U]")||grille[x][y].equals("[O]")) {
					System.out.print("|" + grille[x][y] );
				} else {
					System.out.print("|" + grille[x][y] + "\t");
				}
			}
			System.out.println("|\n");
		}

	}
}
