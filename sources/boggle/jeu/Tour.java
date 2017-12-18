
package boggle.jeu;

import boggle.ui.*;
import boggle.jeu.*;
import boggle.mots.*;
import clavier.Clavier;
import java.lang.*;
import java.util.*;

public class Tour {

	private int tour = 0;
	private GrilleLettres grille;

	public Tour(Jeu configJeu) {
		// on récupère le tableau de joueurs
		Joueur[] joueur = configJeu.getJoueurs();

		// début du jeu
		while ((joueur[tour%configJeu.getNbJoueurs()].getScore() < configJeu.getScoreMax()) ||(tour/configJeu.getNbJoueurs() < configJeu.getNbMancheMax())) {
			// vidage de la console
			// try {
			// 	if(System.getProperty("os.name" ).startsWith("Windows" ))
			// 		Runtime.getRuntime().exec("cls" );
			// 	else
			// 		Runtime.getRuntime().exec("clear" );
			// } catch(Exception excpt) {
			// 	for(int i=0;i<100;i++)
			// 		System.out.println();
			// }


		    // création et affichage de la grille
			int manche = this.getTour()/configJeu.getNbJoueurs() + 1;
			System.out.println("Manche " + manche + "\n");
			System.out.println("A " + joueur[tour%configJeu.getNbJoueurs()].getName() + " de jouer (/ok pour terminer) :\n");

			grille = new GrilleLettres(configJeu.getTailleGrille());
			System.out.println(grille);

			Verifications verif = new Verifications(grille);

			// affichage du score des joueurs
			for(Joueur joueurs : joueur){
		       System.out.println(joueurs); 
		    }

			// récupération de l'entrée standard
			String mot = "";
			while(!mot.equals("/ok")) {
				mot = Clavier.readString();
				// vérification du mot dans la grille
				if(verif.estDansGrille(mot)) {
					// vérification du mot dans le dictionnaire

					// calcul des points et ajout au score
					joueur[tour%configJeu.getNbJoueurs()].setScore(5);
				}
			}




			// Incrémentation du tour
			this.setTour();
		}


		// affichage final
		System.out.println("Score final : \n"); 
		for(Joueur joueurs : joueur){
	       System.out.println(joueurs); 
	    }
	}


	public int getTour() {
		return tour;
	}
	public void setTour() {
		tour++;
	}
}