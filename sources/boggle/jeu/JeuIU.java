package boggle.jeu;

import boggle.jeu.*;
import boggle.mots.*;
import clavier.Clavier;
import java.lang.*;
import java.util.*;
import java.io.*;
import java.text.Normalizer;

public class JeuIU {

	private int tour = 0;
	private GrilleLettres grille;
	private ArrayList<String> motsDejaDonnes;
	private Joueur[] joueurs;
	private Config config;

	public JeuIU(Config config) {
		// on récupère le tableau des joueurs
		this.config = config;
		this.joueurs = config.getJoueurs();

		this.startGame();		
	}

	public void startGame() {
		// début du jeu

		// on instancie l'arbre lexical avec le dictionnaire
		ArbreLexical arbre = new ArbreLexical();
		System.out.println("Chargement du dictionnaire...");
		arbre.lireMots("config/dict-fr.txt");


		// on boucle tant que le joueur n'a pas atteint le score max ou le nombre de manche max
		while ((joueurs[tour%config.getNbJoueurs()].getScore() < config.getScoreMax()) || (tour/config.getNbJoueurs() < config.getNbMancheMax())) {
			// on créé une nouvelle grille à chaque tour
			grille = new GrilleLettres(config.getTailleGrille());			

			// on instancie l'objet qui vérifie les mots de cette grille
			Verifications verif = new Verifications(grille);

			// création de l'arraylist qui recense tous les mots entrés pour ne pas les compter en double
			motsDejaDonnes = new ArrayList<String>();


			String mot = "";
			while(!mot.equals("/OK")) {	
	
				// on affiche la manche
				int manche = this.getTour()/config.getNbJoueurs() + 1;
				System.out.println("Manche " + manche);

				// affichage du score des joueurs
				afficherScore(joueurs);

				System.out.println(grille);
				// message d'information qui précise a quel joueur c'est de jouer
				System.out.println("A " + joueurs[tour%config.getNbJoueurs()].getName() + " de jouer (/ok pour terminer) :\n");
				System.out.println("Mots déjà donnés : ");
				for(String motDonne: motsDejaDonnes){
					System.out.print(motDonne+" ; ");
				}
				System.out.println("\n");


				// récupération de l'entrée standard
				// on supprime les accents et on met en majuscule
				mot = enleverAccents(Clavier.readString()).toUpperCase();

				if(!mot.equals("/OK")) {
					// on vérifie la longueur
					if(mot.length() > 2) {
						// vérification du mot dans la grille
						if(verif.estDansGrille(mot)) {
							// vérification du mot dans le dictionnaire
							if(arbre.contient(mot.toLowerCase())) {
								// vérification dans les mots déjà dits
								if(motsDejaDonnes.contains(mot)) {
									System.out.println("Vous avez déjà donné ce mot.\n"); 
								} else {
									// on ajoute le mot dans l'historique
									motsDejaDonnes.add(mot);
									// on calcule les points apportés par le mot
									int points = calculerPoints(mot);
									// on ajoute au score
									joueurs[tour%config.getNbJoueurs()].setScore(points);
									System.out.println(" + " + points + " points ! Total : " + joueurs[tour%config.getNbJoueurs()].getScore() + "\n"); 
								}
							} else {
								System.out.println("Ce mot n'existe pas dans le dictionnaire.\n"); 
							}
						} else {
							System.out.println("Ce mot n'est pas dans la grille.\n"); 
						}
					} else {
						System.out.println("Les mots nécessitent au moins 3 caractères.\n"); 
					}

					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				cleanConsole();
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

	public int calculerPoints(String mot) {
		int points;
		switch (mot.length()) {
			case 3: 
			case 4:
				points = 1;
				break;
			case 5:
				points = 2;
				break;
			case 6:
				points = 3;
				break;
			case 7:
				points = 5;
				break;
			default:
				points = 11;
				break;
		}
		return points;
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