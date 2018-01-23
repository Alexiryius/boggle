package boggle.jeu;

/**
 * @author radixr rogeza
 * Classe permettant de construire un joeur avec son score associé
 */
public class Joueur {
	// score et nom du joueur
	private int score;
	private String name;

	// constructeur, avec initialisation du score à 0 et du nom
	public Joueur(String name) {
		this.score = 0;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	// mise à jour du score
	public void setScore(int newPoints) {
		score = score + newPoints;
	}

	// affichage du joueur
	public String toString() {
		if(getScore() > 1) {
			return getName() + " : " + getScore() + " points.";
		} else {
			return getName() + " : " + getScore() + " point.";
		}
	}
}