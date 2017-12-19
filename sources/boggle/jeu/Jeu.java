package boggle.jeu;

import boggle.jeu.*;
import boggle.mots.*;
import clavier.Clavier;
import java.lang.*;
import java.util.*;
import java.io.*;
import java.text.Normalizer;

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

			// on affiche la manche
			int manche = this.getTour()/config.getNbJoueurs() + 1;
			System.out.println("Manche " + manche);

			// affichage du score des joueurs
			afficherScore(joueurs);

			// création et affichage de la grille
			grille = new GrilleLettres(config.getTailleGrille());
			System.out.println(grille);

			// message d'information qui précise a quel joueur c'est de jouer
			System.out.println("A " + joueurs[tour%config.getNbJoueurs()].getName() + " de jouer (/ok pour terminer) :\n");


			// on instancie l'arbre lexical et l'objet qui vérifie les mots
			Verifications verif = new Verifications(grille);
			//ArbreLexical arbre = new ArbreLexical();

			// création de l'arraylist qui recense tous les mots entrés pour ne pas les compter en double
			motsDejaDonnes = new ArrayList<String>();

			// récupération de l'entrée standard
			String mot = " ";
			while(!mot.equals("/OK")) {
				// on vérifie la longueur
				if(mot.length() > 2) {
					// on supprime les accents et on met en majuscule
					mot = enleverAccents(Clavier.readString()).toUpperCase();
					// vérification du mot dans la grille
					if(verif.estDansGrille(mot)) {
						// vérification du mot dans le dictionnaire
						// if(arbre.contient(mot)) {
							// vérification dans les mots déjà dits
							if(motsDejaDonnes.contains(mot)) {
								System.out.println("Vous avez déjà donné ce mot."); 
							} else {
								joueurs[tour%config.getNbJoueurs()].setScore(5);
								motsDejaDonnes.add(mot);
								System.out.println(" + 5 points ! Total : " + joueurs[tour%config.getNbJoueurs()].getScore()); 
							}
						// } else {
						// 	System.out.println("Ce mot n'existe pas dans le dictionnaire."); 
						// }

						// calcul des points et ajout au score
					} else {
						System.out.println("Ce mot n'est pas dans la grille."); 
					}
				} else {
					System.out.println("Les mots nécessitent au moins 3 caractères."); 
				}
			}

			// Incrémentation du tour
			this.setTour();
		}

		cleanConsole();

		// affichage final
		System.out.println("Score final : \n"); 
		afficherScore(joueurs);
		this.declarerGagnant(joueurs);
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

	public void declarerGagnant(Joueur[] joueur) {
		int bestScore = 0;
		boolean unique = true;
		String gagnant = "";

		// on récupère le score maximum
		for(int i = 0; i < config.getNbJoueurs(); i++){
			if(joueur[i].getScore() > bestScore) {
				bestScore = joueur[i].getScore();
			}
		}

		// pour ce score maximum on récupère le nom des joueurs correspondants
		for(int i = 0; i < config.getNbJoueurs(); i++){
			if(joueur[i].getScore() == bestScore) {
				if(gagnant.equals("")) {
					gagnant += joueur[i].getName();
				} else {
					// s'il y a plusieurs joueurs avec le même score on passe la variable unique a false
					unique = false;
					gagnant += ", " + joueur[i].getName();
				}
			}
		}

		// on affiche le ou les gagnants
		if(unique) {
			System.out.println("Gagnant :\n" + gagnant + "\n");
		} else {
			System.out.println("Gagnants ex-aequo :\n" + gagnant + "\n");
		}
	}

	public static String enleverAccents(String mot) {
		return Normalizer.normalize(mot, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	}

	public int getTour() {
		return tour;
	}
	public void setTour() {
		tour++;
	}
}