package boggle.jeu;
import clavier.Clavier;
import boggle.mots.*;

public class Config {

	private int tailleGrille;
	private int nbJoueurs;
	private int nbManchesMax;
	private int scoreMax;

	// tableau des joueurs
	private Joueur[] joueurs;

	// constructeur
	public Config(int tailleGrille, int nbJoueurs, int nbManchesMax, int scoreMax) {
		this.tailleGrille = tailleGrille;
		this.nbJoueurs = nbJoueurs;
		this.nbManchesMax = nbManchesMax;
		this.scoreMax = scoreMax;
		init();
	}

	private void init() {
		// initialisation du tableau de joueurs
		joueurs = new Joueur[nbJoueurs];
		
		// pour chaque case du tableau on ajoute un joueur
		for(int i = 1; i <= nbJoueurs; i++){
			System.out.println("Nom du joueur numéro " + i + " ?");
			String nomJoueur = Clavier.readString();
			joueurs[i-1] = new Joueur(nomJoueur);
		}

	}

	public int getTailleGrille() {
		return tailleGrille;
	}
	public int getNbJoueurs() {
		return nbJoueurs;
	}
	public int getScoreMax() {
		return scoreMax;
	}
	public int getNbMancheMax() {
		return nbManchesMax;
	}

	public Joueur[] getJoueurs() {
		return joueurs;
	}
}