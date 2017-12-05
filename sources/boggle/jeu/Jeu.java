package boggle.jeu;
import clavier.Clavier;
//import boggle.mot;

public class Jeu {

	private int tailleGrille;
	private int nbJoueurs;
	private int nbManches;
	private int scoreMax;

	// tableau des joueurs
	public Joueur[] joueurs;

	// constructeur
	public Jeu(int tailleGrille, int nbJoueurs, int nbManches, int scoreMax) {
		this.tailleGrille = tailleGrille;
		this.nbJoueurs = nbJoueurs;
		this.nbManches = nbManches;
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

		// affichage des scores des joueurs
		for(Joueur joueur : joueurs){
           System.out.println(joueur); 
        }


		// GrilleLettres grille = new GrilleLettres(tailleGrille);
	}

	public int getNbManche() {
		return nbManches;
	}


}