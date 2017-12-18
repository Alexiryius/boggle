package boggle.ui;

import java.util.LinkedList;
import java.util.Queue;

import boggle.mots.*;

public class Verifications<E> {
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

	public boolean estDansGrille(String mot) {
		boolean dansGrille = false;
		for (int x = 0; x < tailleGrille; x++) {
			for (int y = 0; y < tailleGrille; y++) {
				if (grille[x][y].equals(mot.charAt(0) + "")) {
					dansGrille = motEntier(mot, 1, x, y);
				}
			}
			if (dansGrille) {
				break;
			}
		}
		return dansGrille;
	}

	public boolean motEntier(String mot, int pointeur, int valeurX, int valeurY) {
		affichageGrille();
		if (pointeur >= mot.length()) {
			return true;
		}
		String lettrePointe = mot.charAt(pointeur) + "";
		Queue<Integer> coord = estSurCaseAutour(lettrePointe, valeurX, valeurY);
		while (!coord.isEmpty()) {
			int x = coord.poll();
			int y = coord.poll();
			pointeur++;
			if (motEntier(mot, pointeur, x, y)) {
				return true;
			}
			pointeur--;
		}
		return false;

	}

	public Queue<Integer> estSurCaseAutour(String a, int valeurX, int valeurY) {
		Queue<Integer> coord = new LinkedList<Integer>();
		for (int x = valeurX - 1; x <= valeurX + 1; x++) {
			for (int y = valeurY - 1; y <= valeurY + 1; y++) {
				if (x == valeurX && y == valeurY)
					y++;
				if (x < 0)
					x++;
				if (y < 0)
					y++;
				if (y >= tailleGrille) {
					y = y - 2;
					x++;
				}
				if (x >= tailleGrille) {
					return coord;
				}

				if (grille[x][y].equals(a + "")) {
					coord.add(x);
					coord.add(y);
				}

			}
		}
		return coord;

	}

	public void affichageGrille() {
		for (int x = 0; x < tailleGrille; x++) {
			for (int y = 0; y < tailleGrille; y++) {
				if (grille[x][y].equals("M") || grille[x][y].equals("W")) {
					System.out.print("| " + grille[x][y] + " ");
				} else {
					System.out.print("| " + grille[x][y] + " ");
				}
			}
			System.out.println("|\n");
		}
	}

}
