package iu;

import boggle.jeu.Config;
import clavier.Clavier;

public class RunAff {
	public static int nbJoueurs, tailleGrille, nbManches, scoreMax;

	public static void main(String[] args) {
		
//		System.out.println("A combien de joueurs allez vous jouer ?");
//		nbJoueurs = Clavier.readInt();
//
//		// manches
//		System.out.println("Sur combien de manches ? (0 pour atteindre un score)");
//		nbManches = Clavier.readInt();
//
//		// si pas de manche, score max
//		if(nbManches == 0) {
//			System.out.println("Quel score voulez vous atteindre ?");
//			scoreMax = Clavier.readInt();
//		}

		// taille grille
		System.out.println("Sur une grille de quelle taille (4 pour l'instant) ?");
		tailleGrille = Clavier.readInt();

		// récap
		Config config = new Config(tailleGrille, 2, 3, 4);
		AffichageIU fenetre = new AffichageIU(config);

	}

}
