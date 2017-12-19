
package boggle.jeu;

import boggle.ui.*;
import boggle.jeu.*;
import boggle.mots.*;
import clavier.Clavier;
import java.lang.*;
import java.util.*;
import java.io.*;

public class Jeu {

	private int tour = 0;
	private GrilleLettres grille;
	private ArrayList<String> motsDejaDonnes;
	private Joueur[] joueurs;
	private Config config;

	public Jeu(Config config) {
		// on récupère le tableau des joueurs
		this.config = config;
		this.joueurs = config.getJoueurs();

		this.startGame();		
	}

	public void startGame() {
		// début du jeu
		// on boucle tant que le joueur n'a pas atteint le score max ou le nombre de manche max
		while ((joueurs[tour%config.getNbJoueurs()].getScore() < config.getScoreMax()) ||(tour/config.getNbJoueurs() < config.getNbMancheMax())) {
			// vidage de la console
			cleanConsole();

			int manche = this.getTour()/config.getNbJoueurs() + 1;
			System.out.println("Manche " + manche);

			// affichage du score des joueurs
			afficherScore(joueurs);


		    // création et affichage de la grille
			grille = new GrilleLettres(config.getTailleGrille());
			System.out.println(grille);

			// message d'information qui précise a quel joueur c'est de jouer
			System.out.println("A " + joueurs[tour%config.getNbJoueurs()].getName() + " de jouer (/ok pour terminer) :\n");

			// on créé l'objet qui vérifie les mots
			Verifications verif = new Verifications(grille);

			// création de l'arraylist qui recense tous les mots entrés pour ne pas les compter en double
			motsDejaDonnes = new ArrayList<String>();

			// récupération de l'entrée standard
			String mot = "";
			while(!mot.equals("/OK")) {
				mot = Clavier.readString().toUpperCase();
				// vérification du mot dans la grille
				if(verif.estDansGrille(mot)) {
					// vérification du mot dans le dictionnaire
					
					// vérification dans les mots déjà dits
					if(motsDejaDonnes.contains(mot)) {
			      		System.out.println("Vous avez déjà donné ce mot."); 
					} else {
						joueurs[tour%config.getNbJoueurs()].setScore(5);
						motsDejaDonnes.add(mot);
			      		System.out.println(" + 5 points ! Total : " + joueurs[tour%config.getNbJoueurs()].getScore()); 
					}
					// calcul des points et ajout au score
				} else {
		      		System.out.println("Ce mot n'est pas dans la grille."); 
				}
			}

			// Incrémentation du tour
			this.setTour();
		}

		cleanConsole();

		// affichage final
		System.out.println("Score final : \n"); 
		afficherScore(joueurs);
	}

	public static void cleanConsole() {
		// for(int i=0;i<100;i++)
		// 	System.out.println();
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static void afficherScore(Joueur[] joueur) {
		for(Joueur joueurs : joueur){
	       System.out.println(joueurs); 
	    }
		System.out.println("\n");
	}

	public int getTour() {
		return tour;
	}
	public void setTour() {
		tour++;
	}
}