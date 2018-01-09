package boggle.mots;

import java.util.LinkedList;
import java.util.Queue;

public class Verifications {
	GrilleLettres grilleLettres;
	char[][] grille;
	int tailleGrille;

	public Verifications(GrilleLettres maGrille) {
		grilleLettres = maGrille;
		tailleGrille = grilleLettres.getTailleGrille();
		grille = grilleLettres.getTabCharGrille();
	}

	/**
	 * méthode qui return true si la première lettre du mot passé en paramètre est dans la grille de lettre
	 * et si motEntier() return true
	 * @param mot
	 * @return
	 */
	public boolean estDansGrille(String mot) {
		boolean dansGrille = false;
		for (int x = 0; x < tailleGrille; x++) {
			for (int y = 0; y < tailleGrille; y++) {
				if (grille[x][y]==mot.charAt(0)) {
					dansGrille = motEntier(mot, 1, x, y);
				}
				if (dansGrille) {
					return dansGrille;
				}
			}
		}
		return dansGrille;
	}

	/**
	 * Methode récurssive qui return true si un mot passé en paramètre est 
	 * (une ou plusieurs fois) dans la grille de lettre. 
	 * Recherche si la lettre suivante se trouve autour de la lettre actuelle que l'on pointe
	 * (si la lettre à charAt(2) est autour de la lettre à charAt(1) du mot par exemple)
	 * @param mot entier
	 * @param pointeur sur la lettre suivante
	 * @param valeurX de la lettre actuelle
	 * @param valeurY de la lettre actuelle
	 * @return
	 */
	public boolean motEntier(String mot, int pointeur, int valeurX, int valeurY) {
		if (pointeur >= mot.length()) {
			return true;
		}
		char lettrePointe = mot.charAt(pointeur);
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

	/**
	 * return une Queue d'integer représentant le nombre de fois que la lettre 
	 * recherchée est autour de la lettre actuelle de valeur x et y
	 * @param a lettre a rechercher
	 * @param valeurX de la lettre actuelle
	 * @param valeurY de la lettre actuelle
	 * @return
	 */
	public Queue<Integer> estSurCaseAutour(char a, int valeurX, int valeurY) {
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

				if (grille[x][y]==a ) {
					coord.add(x);
					coord.add(y);
				}

			}
		}
		return coord;

	}
}
