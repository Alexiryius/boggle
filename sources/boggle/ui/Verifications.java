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

			System.out.println("return true pointeur");
			return true;
		}
		String lettrePointe = mot.charAt(pointeur) + "";
		System.out.println(lettrePointe +" pointeur --------------> "+ pointeur);
		Queue<Integer> coord = estSurCaseAutour(lettrePointe, valeurX, valeurY);
		while (!coord.isEmpty()) {
			int x = coord.poll();
			int y = coord.poll();
			pointeur ++;
			System.out.println("1------:-:-:-:-:------------pointeur : " +pointeur + " ----[" +x+"] ["+y+"]");
			if(motEntier(mot, pointeur,x,y)) {
				System.out.println(" return true ");
				return true;
			}
			System.out.println("2------:-:-:-:-:-----------pointeur : " +pointeur + " ----[" +x+"] ["+y+"]");
			pointeur --;
		}
		System.out.println(" return false " +  valeurX + "|"+ valeurY);
		return false;

	}

	public Queue<Integer> estSurCaseAutour(String a, int valeurX, int valeurY) {
		System.out.println(a + "valeur de x et y au début de sur case autour " +  valeurX + " : "+ valeurY);
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
					System.out.println("grille[x][y] : " + grille[x][y] + "  -->["+x+"]["+y+"]");
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
					System.out.print("| " + grille[x][y] + " \t");
				}
			}
			System.out.println("|\n");
		}
	}

}
