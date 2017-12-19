import boggle.jeu.*;
import boggle.mots.*;
import clavier.Clavier;
import java.lang.*;
import java.util.*;

public class Run {
	public static int nbJoueurs, tailleGrille, nbManches, scoreMax;

	public static void main(String[] args) {
		// Joueurs
		System.out.println("A combien de joueurs allez vous jouer ?");
		nbJoueurs = Clavier.readInt();

		// manches
		System.out.println("Sur combien de manches ? (0 pour atteindre un score)");
		nbManches = Clavier.readInt();

		// si pas de manche, score max
		if(nbManches == 0) {
			System.out.println("Quel score voulez vous atteindre ?");
			scoreMax = Clavier.readInt();
		}

		// taille grille
		System.out.println("Sur une grille de quelle taille (4 minimum) ?");
		tailleGrille = Clavier.readInt();

		// récap
		Config config = new Config(tailleGrille, nbJoueurs, nbManches, scoreMax);
		Jeu jeu = new Jeu(config);


	}
}